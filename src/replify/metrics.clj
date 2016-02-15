(ns replify.metrics
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [me.raynes.fs :as fs])
  (:import
   clojure.lang.DynamicClassLoader
   (java.net URL URLClassLoader)))

(defn ls [dir]
  (filter #(.isFile %) (fs/list-dir dir)))

(defn lc [fname]
  (with-open [rdr (io/reader fname)]
    [(fs/name fname)
     (count (line-seq rdr))]))

(defn stats []
  (let [lines (map #(lc %) (list-files "."))
        total-lines (reduce + (map second lines))
        pages (let [max (quot total-lines 54)
                    min (quot total-lines 62)]
                [min max])]
    (println "Line Count")
    (println lines)
    (println "Total Lines")
    (println total-lines)
    (println "Pages (min, max)")
    (println pages)))
