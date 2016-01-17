(defproject facjure/replify "0.3.0"
  :description "An minimalist Clojurescript repl and build tool"
  :url "https://github.com/facjure/replify"
  :scm {:name "git" :url "https://github.com/facjure/replify"}
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ^:replace ["-Xmx128m" "-server"]
  :dependencies [[org.clojure/clojure "1.7.0" :scope "provided"]
                 [org.clojure/clojurescript "1.7.228" :scope "provided"]
                 [org.clojure/tools.cli "0.3.2"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [me.raynes/fs "1.4.6"]
                 [leiningen "2.5.3"]
                 [alembic "0.3.2"]
                 [garden "1.3.0"]]
  :npm {:dependencies [[source-map-support "0.4.0"]]}
  :plugins [[lein-npm "0.6.1"]
            [lein-garden "0.2.6"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["target" "dist"]
  :target-path "target"
  :uberjar-name "replify.jar")
