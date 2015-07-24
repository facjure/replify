(ns hello.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defn greet [name]
  (println (str "Hello, " name)))

(defn -main [& args]
  (greet "NodeJs REPl!"))

(set! *main-cli-fn* -main)

