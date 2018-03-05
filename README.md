Replify
=======

A _fast_ Clojure/Cljs REPL and a minimalist build tool with zero configuration, as a standalone uberjar.

## Rationale

> Why learn another build tool when you can code on the REPL with zero configuration?

The [node-based](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing
[fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/)
(~10x) **Browser**, **Rhino**, **Nashorn**, and **Node** repls are bundled in
Clojurescript. This library exposes these build tasks as plain old functions on
the repl. In addition it maintains compatibility with leiningen project maps, and
provides utilties to manage runtime classpath and dynamic
dependencies using [alembic](https://github.com/pallet/alembic).

## Quickstart

Assuming you installed
[Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), rlwrap (optional), and downloaded
[replify.jar](https://github.com/priyatam/replify/releases/download/v0.4.0/replify.jar), start a REPL:
	
	rlwrap java -jar replify.jar
	user=> (use 'replify.core)

On an Intel i7-7500U CPU @ 2.70GHz and 32GB Ram, with Ubuntu 17, this
repl launches instantly. From here you can perform common Cljs tasks.

For lightweight scipts under 'src' add the classpath, like this:

	rlwrap java -cp 'replify.jar:src' clojure.main

## Tasks

All tasks assume your current cljs/cljc source files are under `src`.

```clojure
user=> (use 'replify.core)
user=> (build 'foobar.core)
user=> (build-on-node 'foobar.core)
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

To manage dependencies, create a `project.clj` in the current directory. Replify
wraps [leiningen](http://leiningen.org) but you don't have to install it.

```clojure
(defproject FIXME "0.1.0"
    :description "FIXME"
    :url "https://github.com/FIXME"
    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/clojurescript "1.7.28"]]
    :jvm-opts ^:replace ["-Xmx512m" "-server"]
    :node-dependencies [[source-map-support "0.3.1"]]
    :plugins [[lein-npm "0.5.0"]]
    :source-paths ["src" "target/classes"]
    :clean-targets ["out" "release"]
    :profiles {:dev {:dependencies [[facjure/replify "0.3.0"]]}}
    :target-path "target")
```

Add dynamic dependencies

```clojure
=> (add-deps '[org.omcljs/om "0.9.0"])
=> (add-deps '[[org.omcljs/om "0.9.0"] [sablono "0.3.4"] [facjure/mesh "0.3.0"]])
=> (load-cljsjs-deps) ;; load all available cljsjs deps into classpath
```

## With Figwheel/Boot?

Replify is aimed at prototyping on the CLI and your favorite editor, with zero dependencies and configuration.

For projects with 'complex' build tasks and configuration use
[Figwheel](https://github.com/bhauman/lein-figwheel), [Boot](https://github.com/adzerk-oss/boot-cljs), or
even [Lumo](https://github.com/anmonteiro/lumo) on Node.

## Credits

[Clojurescript Wiki](https://github.com/clojure/clojurescript/wiki/Quick-Start).

## Status

[![Clojars Project](http://clojars.org/facjure/replify/latest-version.svg)](http://clojars.org/facjure/replify)

## License

Copyright © 2015 - 2017 Facjure, LLC.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
