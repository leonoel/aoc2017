(ns aoc2017.day1)

(defn char->number [c]
  (- (byte c) (byte \0)))

(defn part1 [input]
  (let [digits (map char->number input)]
    (reduce + (map (fn [i j] (if (== i j) i 0))
                   digits (next (cycle digits))))))

(defn part2 [input]
  (let [digits (map char->number input)]
    (reduce + (map (fn [i j] (if (== i j) i 0))
                   digits (drop (/ (count digits) 2) (cycle digits))))))