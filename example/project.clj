(defproject hello "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]]
  :jvm-opts ^:replace ["-Xms512m" "-server"]
  :node-dependencies [[source-map-support "0.3.1"]]
  :profiles {:dev {:dependencies [[priyatam/replify "0.2.0"]]}}
  :plugins [[lein-npm "0.5.0"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :main replify.core
  :target-path "target")
