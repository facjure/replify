Replify
=======

A minimalist Clojurescript repl with build system. No dependencies, no config.

## Rationale

According to
[2014 State of Clojurescript](https://cognitect.wufoo.com/reports/state-of-clojurescript-2014-results/)
survey, 97% of developers are targeting browser environment, yet 64% report
difficulty in setting up a repl/brepl.  32% use a repl through a combination of
outdated nrepl middlewares, stacked together with nested leiningen maps.  None
of them use the
[new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing
[fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/),
repls built into Clojurescript since v0.2650.

Replify bypasses nrepl, piggieback, weasel, and other combinations in exchange
for a repl that works out of the box in Browser, Rhino, Nashorn, and Node with
Clojurescript versions > 0.2750.

## Quickstart

Create a barebones leiningen project:

	lein new hello

Update `project.clj`:

```clojure
(defproject FIXME "0.1.0"
	:description "FIXME"
	:url "https://github.com/FIXME"
	:dependencies [[org.clojure/clojure "1.7.0"]
	               [org.clojure/clojurescript "0.0-3367"]
	               [priyatam/replify "0.2.0"]]
	:jvm-opts ^:replace ["-Xms512m" "-server"]
	:node-dependencies [[source-map-support "0.3.1"]]
  	:plugins [[lein-npm "0.5.0"]]
	:source-paths ["src" "target/classes"]
	:clean-targets ["out" "release"]
	:profiles {:dev {:dependencies [[priyatam/replify "0.2.0"]]}}
	:main replify.core
	:target-path "target")
```

Open `localhost:9000` in your favorite browser and start the repl, passing the
main namespace (say, `hello.core`)

	rlwrap lein trampoline run hello.core
	ClojureScript:cljs.user>
	
What just happened?

We first compiled cljs compiler to
[improve build times by 2-5x](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/),
then fired an autobuild for cljs src, followed by a node repl.

Compare this to [that](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj).

See `example` project for more info.

## Tasks

Tasks are functions you can invoke on repl. Available Tasks:

	(compile-cljs)
	(build 'hello.core)
	(start-node-repl)
	(start-brepl)
	(release 'hello.core)

Note that `(start-brepl)` should always be run after opening the browser at `localhost:9000`. Read [evaluation environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment) for info on how brepl works.

Add source map support in Node REPL:

	lein npm install

## Credits

Initial code was taken from David Nolen’s
[mies](https://github.com/swannodette/mies/tree/master/src/leiningen/new/mies).
Since scripts can go out of version (most lein templates are never updated once
developers generate them), I combined the sources into one, with an eye on
integrating future tasks.

## Status

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
