Replify
=======

A minimalist Clojurescript repl with build system. No dependencies.

## Rationale

According to
[2014 State of Clojurescript](https://cognitect.wufoo.com/reports/state-of-clojurescript-2014-results/)
survey, 97% of developers are targeting browser environment, yet 64% report
difficulty in setting up a repl/brepl.  32% use a repl through a combination of
outdated nrepl middlewares,
[stacked together](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).
with nested leiningen maps.  None of them use the
[new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing
[fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/),
repls built into Clojurescript since v0.2650.

Replify bypasses nrepl, piggieback, weasel, and other combinations in exchange
for a repl that works out of the box in **Browser**, **Rhino**, **Nashorn**, and **Node**.

## Quickstart

Create a barebones [leiningen](http://leiningen.org) project:

	lein new hello

Update clj, cljs versions and add node and replify deps in `project.clj`:

```clojure
(defproject FIXME "0.1.0"
	:description "FIXME"
	:url "https://github.com/FIXME"
	:dependencies [[org.clojure/clojure "1.7.0"]
	               [org.clojure/clojurescript "0.0-3308"]]
	:jvm-opts ^:replace ["-Xms512m" "-server"]
	:node-dependencies [[source-map-support "0.3.1"]]
  	:plugins [[lein-npm "0.5.0"]]
	:source-paths ["src" "target/classes"]
	:clean-targets ["out" "release"]
	:profiles {:dev {:dependencies [[priyatam/replify "0.2.1"]]}}
	:target-path "target")
```

Start a repl, build, release, and fire other repls.

	rlwrap lein trampoline repl
	cljs.user> (use 'replify.core)
	replify.core> (build 'hello.core)
	
	replify.core> (start-node-repl)
	Starting Node REPL ...
	ClojureScript Node.js REPL server listening on 54240
	To quit, type: :cljs/quit
    cljs.user=> :cljs/quit
	
	user=> (start-rhino-repl)
	Starting Rhino REPL ...
	ClojureScript Node.js REPL server listening on 56767
	To quit, type: :cljs/quit
	cljs.user=> :cljs/quit

	user=> (start-brepl)
	(start-brepl)
	Starting Browser REPL ...
	Compiling client js ...
	Waiting for browser to connect ...
    ...
	
	user=> (release 'hello.core)
	
Note that `(start-brepl)` should always be run after opening the browser at
`localhost:9000`. Read
[evaluation environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment) for more info.

[Improve build times by 2-5x](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/).

	(compile-cljsc)

Add source map support in Node REPL

	lein npm install

See `example` project to see how `cljs` and `cljc` files are loaded and evaluated in repl.

## Credits

Initial code was taken from David Nolen’s
[mies](https://github.com/swannodette/mies/tree/master/src/leiningen/new/mies).
Since scripts and [wikis](https://github.com/clojure/clojurescript/wiki/Running-REPLs) can go out of date, this library might help.

## Status

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
