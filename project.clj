(defproject priyatam/replify "0.2.0"
  :description "A minimalist Clojurescript repl and build system"
  :url "https://github.com/priyatam/replify"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3367"]]
  :jvm-opts ^:replace ["-Xms512m" "-server"]
  :node-dependencies [[source-map-support "0.3.1"]]
  :plugins [[lein-npm "0.5.0"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :target-path "target")
