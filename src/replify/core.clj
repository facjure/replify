(ns replify.core
  (:require [cljs.build.api :as b]
            [cljs.repl :as repl]
            [cljs.repl.node :as node]
            [cljs.repl.browser :as browser]
            [cljs.repl.rhino :as rhino]
            [alembic.still :as still]
            [dynapath.util :as dp])
  (:import clojure.lang.DynamicClassLoader
           (java.net URL URLClassLoader))
  (:gen-class))

(defn compile-cljs-src
  "Compile Cljs compiler for faster src compilations"
  []
  (do
    (compile 'cljs.core)
    (compile 'cljs.repl.node)
    (compile 'cljs.repl.browser)))

(defn watch
  "Watch for Cljs src changes"
  [main & options]
  (println "Watching for changes ...")
  (b/watch "src"
           {:main main
            :output-to "out/app.js"
            :output-dir "out"
            :optimizations :none
            :cache-analysis true
            :source-map true}))

(defn build
  ([main & options]
   "Build Cljs src with options"
   (println "Building Cljs and watching for changes ...")
   (let [start (System/nanoTime)]
     (println "setting main as: " main)
     (b/build "src"
              {:main main
               :output-to "out/app.js"
               :output-dir "out"
               :optimizations :none
               :source-map true
               :verbose true})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")
     (future
       (watch main)))))

(defn build-for-node
  ([main & options]
   "Build Cljs src for Nodejs with options"
   (println "Building Cljs for Node and watching for changes ...")
   (let [start (System/nanoTime)]
     (println "setting main as: " main)
     (b/build "src"
              {:main main
               :output-to "main.js"
               :target :nodejs})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))))

(defn release
  "Release Cljs src for production (advanced compilation)"
  [& options]
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (b/build "src"
             {:output-to "dist/app.min.js"
              :output-dir "out"
              :optimizations :advanced
              :verbose true})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn start-node-repl
  "Start a Node Repl"
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

(defn add-deps [deps]
  (alembic.still/distill deps))

(defn add-path [src]
  (let [cl (clojure.lang.DynamicClassLoader.)
        url (java.net.URL. (str "file://" src))]
    (dp/add-classpath-url cl url)))

(defn show-classpath []
  (dp/classpath-urls (clojure.lang.DynamicClassLoader.)))

(defn -main
  "If invoked on the CLI, compile cljs and start a build and Clojure repl"
  [args]
  (compile-cljs-src)
  (build args))
