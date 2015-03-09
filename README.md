Replify
=======

A simple Clojurescript Node/Browser repl and build system (wip), based on the new & improved Cljs > 0.2665.

No other dependencies.

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## Setup

Add a minimal `project.clj`:

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

Compare this to [that](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).

Quickstart:

	open `localhost:9000` in your favorite browser
	rlwrap lein trampoline run
	
You should now see:	`ClojureScript:cljs.user>`

Congrats! You have now compiled cljs compiler source, started an autobuild for your cljs, and started a browser repl.
For manually controlling each step, see tasks below.

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

## Editor Support

### Emacs/Cider

Cider has an outstanding [issue](https://github.com/clojure-emacs/cider/issues/939) and hangs intermittently. Here is how it works:

After `cider-jack-in`, run `(build)` and `(start-repl)` or `(start-brepl)`.

You should see:

	Starting Node REPL ... OR Started browser Repl (in the browser console)
	To quit, type: :cljs/quit

Open any cljs source in your project and evaluate: `C-c C-k`. You should see this in the repl:

	ClojureScript:cljs.user>

### Lighttable

_TODO_

## Credits

Initial code was taken from David Nolen’s announcement on [Clojurescript Redux](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/) and [mies](https://github.com/swannodette/mies/tree/master/src/leiningen/new/mies). Since scripts can go out of version (most lein templates are never updated once developers generate them), I combined the sources into one, with an eye on integrating future tasks.

Thanks to Ryan Mcg's [lein-repl](https://github.com/RyanMcG/lein-npm).

## Status & Roadmap

Early development. v0.1.0.

TODO:
- integrate figwheel
- add transpilers for cljx, garden, etc.,

## References

- [NodeJs Repl of my dreams](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/)
- [Browser as Evaluation Environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).
- [Google Closure Modules](http://swannodette.github.io/2015/02/23/hello-google-closure-modules/)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
