(ns hello.macros)

(defmacro dbg [body]
  "Cheap inline debugging"
  `(let [x# ~body]
     (println x#)
     x#))
