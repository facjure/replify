Replify
=======

A _fast_ Clojure/Clojurescript REPL and a minimalist build tool in a jar.

## Rationale

> Why learn another build tool when you can code on the REPL?

The [new](http://swannodette.github.io/2014/12/29/nodejs-of-my-dreams/), blazing
[fast](http://swannodette.github.io/2015/01/02/the-essence-of-clojurescript-redux/)
(~10x) **Browser**, **Rhino**, **Nashorn**, and **Node** repls are bundled in
Clojurescript. This library lets you run and write build tasks as plain old
functions on the repl. In addition it also provides utilties to manage runtime
classpath and dynamic [dependencies](https://github.com/pallet/alembic) and classpath.

## Quickstart

Install
[Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). Download
[cljs](https://github.com/clojure/clojurescript/releases/download/r1.7.28/cljs.jar).

Start a REPL

	rlwrap java -cp cljs.jar:src clojure.main
	user=> (add-dep [priyatam/replify "0.2.4"])
	user=> (use 'replify.core)

Install sourcemaps for nodejs

	npm install source-map-support

## Tasks

All tasks assume source files are under `src`.

```clojure
user=> (build 'foobar.core)
user=> (build-for-node 'foobar.core)
user=> (start-node-repl)
user=> (start-rhino-repl)
user=> (start-brepl)
user=> (add-deps '[org.omcljs/om "0.9.0"])
user=> (add-deps '[[org.omcljs/om "0.9.0"] [sablono "0.3.4"] [facjure/mesh "0.3.0"]])
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

Refresh browser at `localhost:9000` for brepl to connect to it. 

For more info, read [evaluation environment](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).

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
[stacked together](https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/project.clj)
in slow, nested maps.

For more info read Clojurescript [wiki](https://github.com/clojure/clojurescript/wiki/Running-REPLs).

## Status

[![Clojars Project](http://clojars.org/priyatam/replify/latest-version.svg)](http://clojars.org/priyatam/replify)

## License

Copyright © 2015, Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
	
