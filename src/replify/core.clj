(ns replify.core
  (:require [cljs.build.api :as b]
            [cljs.repl :as repl]
            [cljs.repl.node :as node]
            [cljs.repl.browser :as browser]
            [cljs.repl.rhino :as rhino]
            [alembic.still :as still])
  (:gen-class))

(defn compile-cljs
  "Compile Cljs compiler for faster src compilations"
  []
  (do
    (compile 'cljs.repl.node)
    (compile 'cljs.repl.browser)
    (compile 'cljs.core)))

(defn watch
  "Watch for Cljs src changes"
  [main & options]
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

(defn release
  "Release Cljs src for production (advanced compilation)"
  [& options]
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (b/build "src"
             {:output-to "dist/app.min.js"
              :output-dir "target"
              :optimizations :advanced
              :verbose true})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn start-node-repl
  "Start a Node Repl"
  [& options]
  (println "Starting Node REPL ...")
  (repl/repl* (node/repl-env)
              {:output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn start-rhino-repl
  "Start a Rhino Repl"
  [& options]
  (println "Starting Rhino REPL ...")
  (repl/repl* (rhino/repl-env)
              {:output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn start-brepl [& options]
  (println "Starting Browser REPL ...")
  (repl/repl* (browser/repl-env)
              {:output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn add-deps [deps]
  (alembic.still/distill deps))

(defn -main
  "If invoked on the CLI, compile cljs and start a node repl"
  [args]
  (compile-cljs)
  (build args)
  (start-node-repl))
