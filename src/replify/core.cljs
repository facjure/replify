(ns replify.core
  (:require [clojure.browser.repl :as repl]))

;; Automatically start brepl after compilation of cljs

(repl/connect (str "http://localhost:9000/repl"))
(.log js/console "Started browser Repl")
