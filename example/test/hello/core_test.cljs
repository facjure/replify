(ns hello.core-test
  (:require [hello.core :as h]
            [cemerick.cljs.test :as t]))

(deftest test-greet
     (is (= "Hello, Foo Bar"
            (h/greet "Foo Bar"))))
