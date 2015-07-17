(ns hello.utils
  (:require [clojure.string :as str]))

#? (:cljs (enable-console-print!))

(defn hello [msg]
 (str/join "hello " msg))

#? (:cljs (print (hello "world")))

