Replify
=======

A simple Clojurescript Node/Browser repl and build system. No dependencies.

## Rationale

According to [2014 State of Clojurescript](https://cognitect.wufoo.com/reports/state-of-clojurescript-2014-results/)
survey, 97% of developers are targeting browser environment, yet _64%_ report difficulty in setting up a repl/brepl.
_32% use a repl_ through a combination of outdated nrepl middlewares, stacked on top of each other, with nested leiningen maps.
None of them use the [new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing [fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/), repls built into Clojurescript > v0.2650.

Replify exposes these repls as plain functions.

## Quickstart

Create a barebones leiningen project and add `project.clj`:

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

Open `localhost:9000` in your favorite browser, and start the repl

	rlwrap lein trampoline run
	ClojureScript:cljs.user>
	
What just happened? We compiled cljs compiler itself, fired an autobuild for your cljs src, and started a browser repl as [Evaluation Environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).

Compare this to [that](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).

## Tasks

Tasks are just functions you invoke on repl. Open another console and run tasks like so:

	rlwrap lein trampoline repl
	replify.core=>

Compile Clojurescript directly in repl to [improve build times by 2-5x](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/):

	(compile-cljs)

Build Cljs source and _watch_ for changes:

	(build)

Build once:

	(build {:watch? false})

Start a node repl:

	(start-repl)

Start a browser repl:

	open `localhost:9000` in your favorite browser
	(start-brepl)
	ClojureScript:cljs.user>

Release Cljs for production using advanced mode (`out/app.min.js`):

	(release)

Clean:

	lein clean

Add source map support in Node REPL:

	lein npm install

## Editors

### Emacs/Cider

After `cider-jack-in`, run `(build)` and `(start-repl)` or `(start-brepl)`.

You should see:

	Starting Node REPL ...
	OR Started browser Repl (in the browser console)
	To quit, type: :cljs/quit

Open any cljs source in your project and evaluate: `C-c C-k`. You should see this in the repl:

	ClojureScript:cljs.user>

NOTE: Cider has an outstanding [issue](https://github.com/clojure-emacs/cider/issues/939) and hangs intermittently.

### Lighttable

_TODO_

## Credits

Initial code was taken from David Nolen’s [mies](https://github.com/swannodette/mies/tree/master/src/leiningen/new/mies).
Since scripts can go out of version (most lein templates are never updated once developers generate them), I combined the
sources into one, with an eye on integrating future tasks.

## Status & Roadmap

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

TODO:

- Integrate figwheel
- Add transpilers for cljx, garden, etc.,

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
