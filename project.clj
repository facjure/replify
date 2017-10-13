 (defproject facjure/replify "0.4.0-SNAPSHOT"
  :description "A minimalist Clojurescript repl with embedded build tools"
  :url "https://github.com/facjure/replify"
  :scm {:name "git" :url "https://github.com/facjure/replify"}
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ^:replace ["-Xmx256m" "-server"]
  :source-paths ["src" "target/classes"]
  :clean-targets ["target" "dist"]
  :target-path "target"
  :dependencies [[org.clojure/clojure "1.9.0-beta2"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/core.async "0.3.443"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [me.raynes/fs "1.4.6"]
                 [alembic "0.3.2"]
                 [figwheel "0.5.14"]
                 [figwheel-sidecar "0.5.14"]
                 [garden "1.3.3"]
                 [leiningen "2.8.0-RC1"]
                 [lein-figwheel "0.5.14"]
                 [lein-garden "0.2.8"]]
  :npm {:dependencies [[source-map-support "0.5.0"]]}
  :plugins [[lein-doo "0.1.8"]
            [lein-npm "0.6.2"]]
  :uberjar-name "replify.jar")
