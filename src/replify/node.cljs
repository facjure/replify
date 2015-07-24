(ns replify.node
  (:require [cljs.nodejs :as nodejs]))

(defn init
  "Initialize NodeJs Environment"
  []
  (nodejs/enable-util-print!))
