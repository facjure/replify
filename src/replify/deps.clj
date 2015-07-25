(ns replify.deps
  (:require
   [alembic.still :as still]
   [clojure.java.io :as io]
   [clojure.edn :as edn]))

(defn load-edn [f]
  (edn/read-string (slurp (io/resource f))))

(defn load-cljsjs-deps []
  ()
  (let [deps (:cljsjs (load-edn "deps.edn"))]
    (for [[k dep] deps]
      (map (alembic.still/distill dep)))))
