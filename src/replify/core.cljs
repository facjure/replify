(ns replify.core
  (:require [clojure.browser.repl :as repl]))

(repl/connect (str "http://localhost:9000/repl"))

(.log js/console "Started browser Repl")

; test fn
(defn greet [name]
  (println (str "Hello " name)))
