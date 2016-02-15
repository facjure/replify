(ns replify.figwheel
  [:require
   [figwheel-sidecar.repl-api :as repl]])

(defn start [main & options]
  "Start figwheel and autocompile the builds specified in `:builds-ids`"
  (repl/start-figwheel!
   {:figwheel-options {}
    :build-ids ["dev"]
    :all-builds [{:id "dev"
                  :figwheel true
                  :source-paths ["src"]
                  :compiler {:main main
                             :output-to "out/app.js"
                             :output-dir "out"
                             :asset-path "out"
                             :verbose true}}]}))

(defn repl [build-id]
  (repl/cljs-repl build-id))

(defn stop []
  (repl/stop-figwheel!))
