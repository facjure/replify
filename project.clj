(defproject priyatam/replify "0.2.3"
  :description "A minimalist Clojurescript repl and build system"
  :url "https://github.com/priyatam/replify"
  :scm {:name "git" :url "https://github.com/priyatam/replify"}
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ^:replace ["-Xms512m" "-XX:+UseConcMarkSweepGC" "-XX:+CMSParallelRemarkEnabled" "-server"]
  :dependencies [[org.clojure/clojure "1.7.0" :scope "provided"]
                 [org.clojure/clojurescript "1.7.28" :scope "provided"]
                 [org.clojure/tools.cli "0.3.1"]
                 [me.raynes/fs "1.4.6"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [alembic "0.3.2"]
                 [garden "1.2.5"]]
  :npm {:dependencies [[source-map-support "0.3.1"]]}
  :plugins [[lein-npm "0.6.1"]
            [lein-garden "0.2.5"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["target" "dist"]
  :target-path "target"
  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.10"]
                                  [leiningen "2.5.0"]]}}
  :uberjar-name "replify.jar")
