(ns hello.core
  (:require [replify.browser :as browser]
            [replify.node :as node]))

(defn greet [name]
  (println (str "Hello, " name)))

(defn -main [& args]
  (greet "NodeJs REPl!"))

(node-init)

(set! *main-cli-fn* -main)
