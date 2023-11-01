(ns user)

(def ordinal (atom 0))
(def store (atom {}))
(def log-store (atom {}))

(defn debug [tag val]
  (swap! log-store update-in [tag] #(conj (or % []) val))
  val)

(defn tags []
  (->> @log-store
       (reduce-kv #(assoc %1 [(count %3) %2] (first %3)) {})
       (map-indexed hash-map)
       (into [])))

(defn debug-data-reader [form]
  `(debug (quote ~form) ~form))

(defn lazy-fn [symbol]
  (fn [& args] (apply (requiring-resolve symbol) args)))

(def go (lazy-fn 'integrant.repl/go))
(def halt (lazy-fn 'integrant.repl/halt))
(def clear (lazy-fn 'integrant.repl/clear))
(def reset (lazy-fn 'integrant.repl/reset))
(def reset-all (lazy-fn 'integrant.repl/reset-all))
