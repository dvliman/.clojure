# dvliman/.clojure

My personal .clojure setup for clojure projects

## Installation 
* Git clone this to `~/.clojure` or put these 2 files into: `~/.clojure/user.clj` and `~/.clojure/data_readers.clj`
* Add an alias `{:aliases {:dev {:extra-paths ["/Users/david/.clojure"]}}}`
* Start the REPL with `clj -A:dev ....`
* If you use emacs, `.dir-locals.el` with `((nil . ((cider-clojure-cli-aliases . "-A:dev"))))` is useful

## Emacs functions

``` emacs-lisp
(defun user-reset-log-store ()
  (interactive)
  (cider--pprint-eval-form
   "(require 'user) (reset! user/log-store {})"))
```

``` emacs-lisp
(defun user-tags ()
  (interactive)
  (cider--pprint-eval-form "(user/tags)"))
```

``` emacs-lisp
(defun user-logs (&optional index-or-name)
  (interactive)
  (let* ((form (read-from-minibuffer "index-or-name: " index-or-name))
         (command (if (string-match form "")
                      "(user/tags)"
                    (format "(user/logs %s first)" form))))
    (cider--pprint-eval-form command)))
```

## Emacs keybindings

``` emacs-lisp

(global-set-key (kbd "s-5") 'user-reset-log-store)
(global-set-key (kbd "s-6") 'user-tags)
(global-set-key (kbd "s-7") 'user-logs)
```

## License

Copyright © 2023 dvliman.com 

Distributed under the Eclipse Public License version 1.0.
