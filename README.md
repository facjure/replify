Replify
=======

A simple Clojurescript Node/Browser repl and build system. No dependencies, no config.

## Rationale

According to [2014 State of Clojurescript](https://cognitect.wufoo.com/reports/state-of-clojurescript-2014-results/)
survey, 97% of developers are targeting browser environment, yet 64% report difficulty in setting up a repl/brepl.
32% use a repl through a combination of outdated nrepl middlewares, stacked together with nested leiningen maps.
None of them use the [new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing [fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/), repls built into Clojurescript since v0.2650.

Replify exposes these repls as plain functions.

## Quickstart

Create a barebones lein project:

	lein new hello

Update `project.clj`:

```clojure
(defproject FIXME "0.1.0"
	:description "FIXME"
	:url "https://github.com/FIXME"
	:dependencies [[org.clojure/clojure "1.6.0"]
	               [org.clojure/clojurescript "0.0-2913"]
	               [priyatam/replify "0.1.0"]]
	:jvm-opts ^:replace ["-Xmx1g" "-server"]
	:node-dependencies [[source-map-support "0.2.9"]]
  	:plugins [[lein-npm "0.5.0"]]
	:source-paths ["src" "target/classes"]
	:clean-targets ["out" "release"]
	:main replify.core
	:target-path "target")
```

Open `localhost:9000` in your favorite browser and start the repl

	rlwrap lein trampoline run
	ClojureScript:cljs.user>
	
What just happened? We first compiled cljs compiler to [improve build times by 2-5x](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), then fired an autobuild for cljs src, followed by a browser repl as [Evaluation Environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).

Compare this to [that](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).

See `example` project for more info.

## Tasks

Tasks are functions you can invoke on repl. Open another console and run tasks like so:

	rlwrap lein trampoline repl
	replify.core=>

Available Tasks:

	(compile-cljs)
	(build)
	(build {:watch? false})
	(start-repl)
	(start-brepl)
	(release)

Note that `(start-brepl)` should always be run after opening the browser at  `localhost:9000`.

Add source map support in Node REPL:

	lein npm install

## Credits

Initial code was taken from David Nolen’s [mies](https://github.com/swannodette/mies/tree/master/src/leiningen/new/mies).
Since scripts can go out of version (most lein templates are never updated once developers generate them), I combined the
sources into one, with an eye on integrating future tasks.

## Status

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
