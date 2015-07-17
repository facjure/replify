(ns replify.core
  (:require [cljs.build.api :as b]
            [cljs.repl :as repl]
            [cljs.repl.node :as node]
            [cljs.repl.browser :as browser]
            [cljs.repl.rhino :as rhino])
  (:gen-class))

(defonce compile-cljs
  ^{:doc "Compile Cljs compiler for faster src compilations"}
  (do
    (compile 'cljs.repl.node)
    (compile 'cljs.repl.browser)
    (compile 'cljs.core)))

(defn watch [main & options]
  "Watch for Cljs src changes"
  (println "Watching for changes ...")
  (b/watch "src"
           {:main main
            :output-to "target/app.js"
            :output-dir "target"
            :optimizations :none
            :cache-analysis true
            :source-map true}))

(defn build
  ([main & options]
   "Build Cljs src with options"
   (println "Building 'Dev' and watching for changes ...")
   (let [start (System/nanoTime)]
     (println "setting main as: " main)
     (b/build "src"
              {:main main
               :output-to "target/app.js"
               :output-dir "target"
               :optimizations :none
               :source-map true
               :verbose true})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")
     (future
       (watch main)))))

(defn release [& options]
  "Release Cljs src for production (advanced compilation)"
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (b/build "src"
             {:output-to "dist/app.min.js"
              :output-dir "target"
              :optimizations :advanced
              :verbose true})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn start-node-repl [& options]
  "Start a Node Repl"
  (println "Starting Node REPL ...")
  (repl/repl* (node/repl-env)
              {:output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn start-rhino-repl [& options]
  "Start a Rhino Repl"
  (println "Starting Rhino REPL ...")
  (repl/repl* (node/repl-env)
              {:output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn start-brepl [& options]
  (println "Starting Browser REPL ...")
  (repl/repl* (rhino/repl-env)
              {:output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn -main [args]
  "If invoked on the CLI, compile cljs and start a node repl"
  (compile-cljs)
  (build args)
  (start-node-repl))
