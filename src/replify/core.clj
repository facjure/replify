(ns replify.core
  (:require [cljs.build.api :as b]
            [cljs.repl :as repl]
            [cljs.repl.node :as node]
            [cljs.repl.browser :as browser])
  (:gen-class))

(defn compile-cljs []
  "Compile Cljs compiler for faster src compilations"
  (do
    (compile 'cljs.repl.node)
    (compile 'cljs.repl.browser)
    (compile 'cljs.core)))

(defn watch [{:keys [main] :as options}]
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
  ([{:keys [main] :as options}]
   "Build Cljs src with options"
   (println "Building 'Dev' and watching for changes ...")
   (let [start (System/nanoTime)]
     (b/build "src"
              {:main main
               :output-to "target/app.js"
               :output-dir "target"
               :optimizations :none
               :source-map true
               :verbose true})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")
     (future
       (watch {:main main})))))

(defn release [& options]
  "Release Cljs src for production (advanced compilation)"
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (b/build "src"
             {:output-to "out/app.min.js"
              :output-dir "release"
              :optimizations :advanced
              :verbose true})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn start-node-repl [& options]
  "Start a Node Repl"
  (println "Starting Node REPL ...")
  (repl/repl* (node/repl-env)
              {:watch "src"
               :output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn start-brepl [& options]
  (println "Starting Browser REPL ...")
  (repl/repl* (browser/repl-env)
              {:watch "src"
               :output-dir "target"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn -main [args]
  "If invoked on a command line, compile cljs and start a brepl"
  (compile-cljs)
  (build args)
  (start-node-repl))
