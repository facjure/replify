(ns replify.lein
  (:require
   [alembic.still :as still]
   [clojure.java.io :as io]))

(defn deps-tree []
  (still/lein deps :tree))

(defn run [task]
  (still/lein run -m task))
