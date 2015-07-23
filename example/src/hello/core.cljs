(ns hello.core
  (:require [replify.core :as repl]))

(defn greet [name]
  (println (str "Hello again" name)))
