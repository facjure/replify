Replify
=======

A _fast_ Clojure/Clojurescript REPL and a minimalist build tool, as a standalone jar.

## Rationale

> Why learn another build tool when you can code on the REPL?

The [new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing
[fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/)
(~10x) **Browser**, **Rhino**, **Nashorn**, and **Node** repls are bundled in
Clojurescript. This library exposes these build tasks as plain old functions on
the repl. In addition it maintains compatibility with leiningen project maps, and
provides utilties to manage runtime classpath and dynamic
dependencies using [alembic](https://github.com/pallet/alembic).

## Quickstart

Install
[Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). Download
[replify.jar](https://github.com/priyatam/replify/releases/download/v0.2.4/replify.jar).

Start a REPL

	rlwrap java -cp replify.jar:src clojure.main
	user=> (use 'replify.core)
	       (start-node-repl)
	
## Tasks

All tasks assume source files are under `src`.

```clojure
user=> (build 'foobar.core)
user=> (build-for-node 'foobar.core)
... node main.js
user=> (start-node-repl)
user=> (start-rhino-repl)
user=> (start-brepl)
user=> (release 'hello.core)
```

For browser REPLs: Create an index.html at the project root and include `(:require [replify.core :as repl])` in your main source file.

```html
<html>
	<body>
      <script src="target/app.js" type="text/javascript"></script>
      <div id="app"> </div>
    </body>
</html>
```

Refresh browser at `localhost:9000` for brepl to connect. 

To manage dependencies, create a `project.clj` in the current directory. Replify wraps [leiningen](http://leiningen.org), but you don't have to install it. 

```clojure
(defproject FIXME "0.1.0"
    :description "FIXME"
    :url "https://github.com/FIXME"
    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/clojurescript "1.7.28"]]
    :jvm-opts ^:replace ["-Xms512m" "-server"]
    :node-dependencies [[source-map-support "0.3.1"]]
    :plugins [[lein-npm "0.5.0"]]
    :source-paths ["src" "target/classes"]
    :clean-targets ["out" "release"]
    :profiles {:dev {:dependencies [[priyatam/replify "0.2.4"]]}}
    :target-path "target")
```

Add dynamic dependencies

```clojure
=> (add-deps '[org.omcljs/om "0.9.0"])
=> (add-deps '[[org.omcljs/om "0.9.0"] [sablono "0.3.4"] [facjure/mesh "0.3.0"]])
=> (load-cljsjs-deps) ;; load all available cljsjs deps into classpath
```

## With Figwheel/Boot

Replify is aimed at prototyping on the CLI. For projects with complex build tasks, use
[Figwheel](https://github.com/bhauman/lein-figwheel) or
[Boot](https://github.com/adzerk-oss/boot-cljs).

## History & Credits

According to 2014
[state of Clojurescript](https://cognitect.wufoo.com/reports/state-of-clojurescript-2014-results/)
survey, 97% of developers are targeting browser environment, yet 64% report
difficulty in setting up a repl/brepl/nrepl. Some use a combination of
outdated nrepl middlewares,
[stacked together](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).

For more info read Clojurescript [wiki](https://github.com/clojure/clojurescript/wiki/Running-REPLs).

## Status

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
	
