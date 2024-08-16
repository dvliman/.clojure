(ns user)

(def ordinal (atom 0))
(def store (atom {}))
(def log-store (atom {}))

(defn debug [tag val]
  (swap! log-store update-in [tag] #(conj (or % []) val))
  val)

(defn logs
  [tag & functions]
  (let [tag (if (number? tag)
              (nth (keys @log-store) tag)
              tag)]
    (loop [values    (@log-store tag)
           functions functions]
      (if (seq functions)
        (recur ((first functions) values)
               (rest functions))
        values))))

#_(defn tags []
  (->> @log-store
       (reduce-kv #(assoc %1 [(count %3) %2] (last %3)) {})
       (map-indexed hash-map)
       (into [])))

(defn same-values? [x]
  (if (= 1 (count (set x)))
    :same-values
    :different-values))

(defn tags []
  (->> @log-store
       (reduce-kv #(assoc %1 [(count %3) (same-values? %3) %2] (last %3)) {})
       (map-indexed hash-map)
       (into [])))

(defn debug-data-reader [form]
  `(debug (quote ~form) ~form))

(defn lazy-fn [symbol]
  (fn [& args] (apply (requiring-resolve symbol) args)))

(defn app []
  ((lazy-fn 'shadow.cljs.devtools.api/watch) :app))

(defn x [x y] (* 15 (- x y)))
(defn t [x y] (* 20 (- x y)))
(defn f [x y] (* 40 (- x y)))

;; june 18
(+
 (* 10 (- 3.27 4.96))
 3
 )
;; => -13.899999999999999
;; => -16.9
