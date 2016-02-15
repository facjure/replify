(ns replify
  (:require
   [clojure.main :as main]
   [clojure.java.io :as io]))

(defn compile-cljs-src
  "Compile Cljs compiler for faster source compilations"
  []
  (compile 'cljs.core)
  (compile 'cljs.repl.node)
  (compile 'cljs.repl.browser))
