(defproject priyatam/replify "0.2.3"
  :description "A minimalist Clojurescript repl and build system"
  :url "https://github.com/priyatam/replify"
  :scm {:name "git" :url "https://github.com/priyatam/replify"}
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0" :scope "provided"]
                 [org.clojure/clojurescript "0.0-3367" :scope "provided"]
                 [org.clojure/tools.cli "0.3.1"]
                 [alembic "0.3.2"]]
  :jvm-opts ^:replace ["-Xms512m" "-XX:+UseConcMarkSweepGC" "-XX:+CMSParallelRemarkEnabled" "-server"]
  :node-dependencies [[source-map-support "0.3.1"]]
  :plugins [[lein-npm "0.5.0"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["target" "dist"]
  :target-path "target"
  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.10"]]}}
  :uberjar-name "replify.jar")
