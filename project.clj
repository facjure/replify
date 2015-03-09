(defproject priyatam/replify "0.1.0"
  :description "A simple Clojurescript Browser and Node Repl"
  :url "https://github.com/priyatam/replify"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2913"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :node-dependencies [[source-map-support "0.2.9"]]
  :plugins [[lein-npm "0.5.0"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :main replify.core
  :target-path "target")
