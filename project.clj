(defproject priyatam/replify "0.2.2"
  :description "A minimalist Clojurescript repl and build system"
  :url "https://github.com/priyatam/replify"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3367"]
                 [alembic "0.3.2"]]
  :jvm-opts ^:replace ["-Xms512m" "-XX:+UseConcMarkSweepGC" "-XX:+CMSParallelRemarkEnabled" "-server"]
  :node-dependencies [[source-map-support "0.3.1"]]
  :plugins [[lein-npm "0.5.0"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["target" "dist"]
  :target-path "target"
  :uberjar-name "replify.jar")
