(ns aoc2017.day4
  (:require [aoc2017.utils :as u]))

(def words (partial re-seq #"[a-z]+"))

(defn valid1? [sentence]
  (let [words (words sentence)]
    (= words (distinct words))))

(assert (valid1? "aa bb cc dd ee"))
(assert (not (valid1? "aa bb cc dd aa")))
(assert (valid1? "aa bb cc dd aaa"))

(defn part1 [sentences]
  (->> sentences u/read-lines (filter valid1?) count))

(defn valid2? [sentence]
  (let [words (map frequencies (words sentence))]
    (= words (distinct words))))

(assert (valid2? "abcde fghij"))
(assert (not (valid2? "abcde xyz ecdab")))
(assert (valid2? "a ab abc abd abf abj"))
(assert (valid2? "iiii oiii ooii oooi oooo"))
(assert (not (valid2? "oiii ioii iioi iiio")))

(defn part2 [sentences]
  (->> sentences u/read-lines (filter valid2?) count))