(ns replify.deps
  (:require
   [alembic.still :as still]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clostache.parser :as tmpl]))

(defn load-edn [f io-type]
  (case io-type
    :res (edn/read-string (slurp (io/resource f)))
    :file (edn/read-string (slurp (io/file f)))))

(defn load-cljsjs-deps []
  (let [deps (:cljsjs (load-edn "deps.edn" :res))]
    (for [[k dep] deps]
      (map (alembic.still/distill dep)))))

(defn load-package-edn [fname]
  (let [conf (load-edn fname :file)]
    (do
      (map #(still/distill %) (:dependencies conf))
      (still/lein npm install))))


