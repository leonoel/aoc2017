(ns aoc2017.day2
  (:require [aoc2017.utils :as u]))

(defn max-minus-min [numbers]
  (- (apply max numbers) (apply min numbers)))

(defn part1 [input]
  (transduce (map max-minus-min) + (u/read-lines-of-numbers input)))

(defn divisions [numbers]
  (when-some [[q & ps] (seq numbers)]
    (concat (map #(/ % q) ps) (divisions ps))))

(defn find-division [numbers]
  (->> (sort numbers)
       (divisions)
       (filter int?)
       (first)))

(defn part2 [input]
  (transduce (map find-division) + (u/read-lines-of-numbers input)))