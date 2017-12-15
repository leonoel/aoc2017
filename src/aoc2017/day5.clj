(ns aoc2017.day5
  (:require [aoc2017.utils :as u]))

(defn part1 [input]
  (loop [program (vec (u/read-numbers input))
         counter 0
         steps 1]
    (let [n (+ counter (get program counter))]
      (if (or (neg? n) (<= (count program) n))
        steps (recur (update program counter inc) n (inc steps))))))

(assert (= 5 (part1 "0\n3\n0\n1\n-3\n")))

(defn part2 [input]
  (loop [program (vec (u/read-numbers input))
         counter 0
         steps 1]
    (let [o (get program counter)
          n (+ counter o)]
      (if (or (neg? n) (<= (count program) n))
        steps (recur (update program counter (if (<= 3 o) dec inc)) n (inc steps))))))

(assert (= 10 (part2 "0\n3\n0\n1\n-3\n")))