(ns aoc2017.day2
  (:require [clojure.tools.reader.edn :as edn]
            [clojure.tools.reader.reader-types :as rdr]))

(defn read-lines [s]
  (take-while seq (repeatedly (partial rdr/read-line (rdr/push-back-reader s)))))

(defn read-numbers [row]
  (take-while some? (repeatedly (partial edn/read (rdr/push-back-reader row) false nil nil))))

(defn read-lines-of-numbers [s]
  (map read-numbers (read-lines s)))

(defn max-minus-min [numbers]
  (- (apply max numbers) (apply min numbers)))

(defn part1 [input]
  (transduce (map max-minus-min) + (read-lines-of-numbers input)))

(defn divisions [numbers]
  (when-some [[q & ps] (seq numbers)]
    (concat (map #(/ % q) ps) (divisions ps))))

(defn find-division [numbers]
  (->> (sort numbers)
       (divisions)
       (filter int?)
       (first)))

(defn part2 [input]
  (transduce (map find-division) + (read-lines-of-numbers input)))