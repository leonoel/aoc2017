(ns aoc2017.day6
  (:require [aoc2017.utils :as u]))

(defn step [banks]
  (let [[im nm] (reduce-kv (fn [[im nm] i n] (if (< nm n) [i n] [im nm])) [-1 -1] banks)
        c (count banks)
        q (quot nm c)
        r (rem nm c)]
    (->> (assoc banks im 0)
         (into [] (map-indexed (fn [i n] (+ n q (if (< (mod (- i im 1) c) r) 1 0))))))))

(defn find-cycle [xs]
  (loop [seen {}
         xs (seq xs)
         i 0]
    (when-some [[x & xs] xs]
      (if-some [j (seen x)] [j i] (recur (assoc seen x i) xs (inc i))))))

(defn part1 [input]
  (second (find-cycle (iterate step (vec (u/read-numbers input))))))

(assert (= 5 (part1 "0 2 7 0")))

(defn part2 [input]
  (let [[s e] (find-cycle (iterate step (vec (u/read-numbers input))))] (- e s)))

(assert (= 4 (part2 "0 2 7 0")))