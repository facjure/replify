(ns replify.core
  (:require [cljs.closure :as cljsc]
            [cljs.repl :as repl]
            [cljs.repl.node :as node]
            [cljs.repl.browser :as browser])
  (:gen-class))

(defn compile-cljs []
  "Compile Clojurescript compiler for fast src compilations"
  (do
    (compile 'cljs.repl.node)
    (compile 'cljs.repl.browser)
    (compile 'cljs.core)))

(defn watch [& options]
  "Watch for Src Cljs changes"
  (println "Watching for changes ...")
  (cljsc/watch "src"
               {:main 'replify.core
                :output-to "out/app.js"
                :output-dir "out"
                :optimizations :none
                :cache-analysis true
                :source-map true}))

(defn build
  ([{:keys [watch?] :as options}]
   "Build Cljs src with options"
   (println "Building 'Dev' and watching for changes ...")
   (let [start (System/nanoTime)]
     (cljsc/build "src"
                  {:main 'replify.core
                   :output-to "out/app.js"
                   :output-dir "out"
                   :optimizations :none
                   :source-map true
                   :verbose true})
     (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")
     (if watch?
       (future
         (watch)))))
  ([]
   "Build Cljs source and watch by default"
   (build {:watch? true})))

(defn release [& options]
  "Release Cljs src for production (advanced compilation)"
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (cljsc/build "src"
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
               :output-dir "out"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn start-brepl [& options]
  (println "Starting Browser REPL ...")
  (repl/repl* (browser/repl-env)
              {:watch "src"
               :output-dir "out"
               :optimizations :none
               :cache-analysis true
               :source-map true}))

(defn -main [& options]
  "If invoked on a command line, compile cljs and start a brepl"
  (println "Starting repl")
  (compile-cljs)
  (build)
  (start-brepl))
