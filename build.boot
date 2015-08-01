(def +version+ "0.2.3")

(task-options!
  pom {:project     'replify
       :version     +version+
       :description "A fast Clojurescript REPL and minimalist build tool "
       :url         "https://github.com/priyatam/replify"
       :scm         {:url "https://github.com/priyatam/replify"}
       :license     {"Eclipse Public License"
                     "http://www.eclipse.org/legal/epl-v10.html"}})

(set-env!
  :source-paths #{"src"}
  :resource-paths #{"src"}
  :dependencies '[[org.clojure/clojure       "1.7.0"          :scope "provided"]
                  [org.clojure/clojurescript "1.7.28"         :scope "provided"]
                  [org.clojure/core.async    "0.1.346.0-17112a-alpha" :scope "provided"]
                  [org.clojure/tools.cli     "0.3.1"]
                  [me.raynes/fs              "1.4.6"]
                  [alembic                   "0.3.2"]
                  [adzerk/boot-cljs          "0.0-3308-0"     :scope "test"]
                  [adzerk/boot-reload        "0.3.1"          :scope "test"]
                  [adzerk/boot-cljs-repl     "0.1.9"          :scope "test"]
                  [pandeiro/boot-http        "0.6.3-SNAPSHOT" :scope "test"]
                  [adzerk/bootlaces          "0.1.11"         :scope "test"]])

(require '[adzerk.boot-cljs      :refer [cljs]]
         '[adzerk.boot-reload    :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[pandeiro.boot-http    :refer [serve]]
         '[adzerk.bootlaces      :refer :all])

(bootlaces! +version+)

(deftask build []
  (comp
   (pom :project 'replify
        :version +version+)
   (uber)
   (jar)))

(defn docs-env! []
  (merge-env!
    :source-paths #{"docs"}
    :resource-paths #{"docs"}
    :dependencies '[]))

(deftask docs []
  (docs-env!)
  (comp (serve :dir ".")
        (watch)
        (reload :on-jsload 'combo-demo.core/main)
        (cljs-repl)
        (cljs :optimizations :none
              :source-map    true
              :unified-mode  true)))

(deftask clojars-release []
  (comp (build-jar) (push-release)))

(deftask gh-pages []
  (docs-env!)
  (cljs :optimizations :advanced))
