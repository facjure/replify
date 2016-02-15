(ns replify.lein
  (:require
   [alembic.still :as still]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [dynapath.util :as dp])
  (:import
   clojure.lang.DynamicClassLoader
   (java.net URL URLClassLoader)))

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

(defn add-deps [deps]
  (alembic.still/distill deps))

(defn add-path [src]
  (let [cl (clojure.lang.DynamicClassLoader.)
        url (java.net.URL. (str "file://" src))]
    (dp/add-classpath-url cl url)))

(defn show-classpath []
  (dp/classpath-urls (clojure.lang.DynamicClassLoader.)))

(defn deps-tree []
  (still/lein deps :tree))

(defn run [task]
  (still/lein run -m task))
