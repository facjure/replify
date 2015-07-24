Replify
=======

A _fast_, standalone Clojurescript REPL with a minimalist build tool. Aimed at
rapid prototyping without leaving the REPL.

## Rationale

According to 2014
[state of Clojurescript](https://cognitect.wufoo.com/reports/state-of-clojurescript-2014-results/)
survey, 97% of developers are targeting browser environment, yet 64% report
difficulty in setting up a repl/brepl/nrepl. Some use a combination of
outdated nrepl middlewares,
[stacked together](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).
in nested maps, and they're painfully slow. None of them use the
[new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing
[fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/) (~10x)
repls built into Clojurescript, since v0.2650.

Replify bypasses nrepl, piggieback, weasel, lein-cljsbuild, and other complex
build tools and instead exposes built-in Cljs repls (**Browser**, **Rhino**,
**Nashorn**, and **Node**) with a minimal workflow. It also includes runtime classpath
and dependency management via [https://github.com/pallet/alembic].

## Quickstart

Install [Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

Clone this repo, and start a standalone repl that bundles clj, cljs, and replify

	lein uberjar
	rlwrap java -cp src -jar target/replify.jar
	user=>
	
However, most times you want to manage dependencies and reuse plugins from [leiningen](http://leiningen.org).

Create a default lein template:

	lein new hello

Update clj, cljs versions and add node and deps in project.clj:

	(defproject hello "0.1.0"
	 :description "FIXME: write description"
	 :url "http://example.com/FIXME"
	 :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
	 :jvm-opts ^:replace ["-Xms512m" "-server"]
	 :source-paths ["src" "target/classes"]
	 :clean-targets ["out" "release"]
	 :target-path "target"
	 :dependencies [[org.clojure/clojure "1.7.0"]
                    [org.clojure/clojurescript "0.0-3308"]]
	 :node-dependencies [[source-map-support "0.3.1"]]
	 :profiles {:dev {:dependencies [[priyatam/replify "0.2.2"]]}}
	 :plugins [[lein-npm "0.5.0"]])

Add source map support for Node REPL

	lein npm install

Start a Clojure REPL (to run replify tasks)

	rlwrap lein trampoline repl
	user=>

## Tasks

Build and Watch the 'src' directory

	(use 'replify.core)
	user=> (build 'foobar.core)
	user=> (build-for-node 'foobar.core)

Choose a target environment and fire a Cljs REPL

	user=> (start-node-repl)
	Starting Node REPL ...
	ClojureScript Node.js REPL server listening on 54240
	
	user=> (start-rhino-repl)
	Starting Rhino REPL ...
	ClojureScript Node.js REPL server listening on 56767
	
	user=> (start-brepl)
	Starting Browser REPL ...
	Compiling client js ...
	Waiting for browser to connect ...
    ...

Create an index.html at the root, and include `(:require [replify.core :as repl])` in your main source file. Refresh browser for brepl to connect to browser

	<html>
    <body>
      <script src="target/app.js" type="text/javascript"></script>
      <div id="app"> </div>
    </body>
	</html>
	
Add a dependency(ies)

	user=>(add-deps '[org.omcljs/om "0.9.0"])
	Loaded dependencies:
	...
	
	user=>(add-deps '[[org.omcljs/om "0.9.0"] [sablono "0.3.4"] [facjure/mesh "0.3.0"]])
	Loaded dependencies:
    ...
	
Release (with advanced optimizations)

	user=> (release 'hello.core)
	
Note that `(start-brepl)` should always be run after opening the browser at
`localhost:9000`. Read
[evaluation environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment) for more info.

## Next Steps

Replify is aimed at prototyping and ad-hoc tasks on the CLI. For larger projects
use [Figwheel](https://github.com/bhauman/lein-figwheel) or
[Boot](https://github.com/adzerk-oss/boot-cljs).

## Credits

- Clojurescript [wiki](https://github.com/clojure/clojurescript/wiki/Running-REPLs)

## Status

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
