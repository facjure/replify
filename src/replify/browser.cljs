(ns replify.browser
  (:require [clojure.browser.repl :as repl]))

;; Automatically start brepl after compilation of cljs
(defonce conn
  (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(println "Started browser Repl")
