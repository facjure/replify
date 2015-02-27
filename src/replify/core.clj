(ns replify.core
  (:require [cljs.closure :as cljsc]
            [cljs.repl :as repl]
            [cljs.repl.node :as node]
            [cljs.repl.browser :as browser])
  (:gen-class))

(defn compile-cljs []
  (do
    (compile 'cljs.repl.node)
    (compile 'cljs.repl.browser)
    (compile 'cljs.core)))

(defn watch [& options]
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
   "Build with options"
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
   "Default build = build with watch"
   (build {:watch? true})))

(defn release [& options]
  (println "Building 'Release' ...")
  (let [start (System/nanoTime)]
    (cljsc/build "src"
                 {:output-to "out/app.min.js"
                  :output-dir "release"
                  :optimizations :advanced
                  :verbose true})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn start-repl [& options]
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

(defn start [& options]
  (println "Starting repl")
  (build)
  (start-repl))
