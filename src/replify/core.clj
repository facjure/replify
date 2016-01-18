(ns replify.core
  (:require
   [clojure.main :as main]
   [clojure.java.io :as io]
   [clojure.tools.cli :refer [parse-opts]]
   [cljs.compiler :as compiler]
   [cljs.closure :as closure]
   [cljs.build.api :as api]
   [cljs.repl :as repl]
   [cljs.repl.node :as node]
   [cljs.repl.browser :as browser]
   [cljs.repl.rhino :as rhino]
   [alembic.still :as still]
   [dynapath.util :as dp]
   [replify.deps :refer [load-cljsjs-deps]])
  (:import
   clojure.lang.DynamicClassLoader
   (java.net URL URLClassLoader)))

(defn compile-cljs-src
  "Compile Cljs compiler for faster source compilations"
  []
  (compile 'cljs.core)
  (compile 'cljs.repl.node)
  (compile 'cljs.repl.browser))

(defn build
  ([main & options]
   "Build Cljs src with options"
   (println "Building Cljs")
   (let [start (System/nanoTime)]
     (println "setting main as: " main)
     (api/build "src"
              {:main main
               :output-to "out/app.js"
               :output-dir "out"
               :optimizations :none
               :source-map true
               :verbose true})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))))

(defn watch
  "Watch for Cljs src changes"
  [main & options]
  (println "Watching for changes ...")
  (api/watch "src"
             {:main main
            :output-to "out/app.js"
            :output-dir "out"
            :optimizations :none
            :cache-analysis true
            :source-map true}))

(defn build-on-node
  ([main & options]
   "Build Cljs src for Nodejs with options"
   (println "Building Cljs for Node and watching for changes ...")
   (let [start (System/nanoTime)]
     (println "setting main as: " main)
     (api/build "src"
              {:main main
               :output-to "main.js"
               :target :nodejs})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))))

(defn release
  "Release Cljs src for production (advanced compilation)"
  [& options]
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (api/build "src"
             {:output-to "dist/app.min.js"
              :output-dir "out"
              :optimizations :advanced
              :verbose true})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn start-node-repl
  [& options]
  (println "Starting Node REPL ...")
  (repl/repl* (node/repl-env)
              {:watch "src"
               :output-dir "out"}))

(defn start-rhino-repl
  "Start a Rhino Repl"
  [& options]
  (println "Starting Rhino REPL ...")
  (repl/repl* (rhino/repl-env)
             {:watch "src"
              :output-dir "out"
              :optimizations :none
              :cache-analysis true
              :source-map true}))

(defn start-brepl [& options]
  (println "Starting Browser REPL ...")
  (repl/repl* (browser/repl-env)
              {:output-dir "out"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn- run [task]
  (still/lein run -m task))

(defn node-repl [main]
  (run (replify.core/start-node-repl main)))

(defn add-deps [deps]
  (alembic.still/distill deps))

(defn add-path [src]
  (let [cl (clojure.lang.DynamicClassLoader.)
        url (java.net.URL. (str "file://" src))]
    (dp/add-classpath-url cl url)))

(defn show-classpath []
  (dp/classpath-urls (clojure.lang.DynamicClassLoader.)))
