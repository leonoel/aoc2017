(ns aoc2017.day3)

(defn edge [n]
  (let [e (long (Math/sqrt n))]
    (if (and (== (* e e) n) (odd? e))
      e (let [e (inc e)]
          (if (odd? e) e (inc e))))))

(defn coords [n]
  (if (== 1 n)
    [0 0]
    (let [e (dec (edge n))
          d (long (/ e 2))
          nd (+ n d)
          [tr tl bl br]
          (->> (* (dec e) (dec e))
               (iterate (partial + e))
               (next))]
      (condp >= n
        tr [d (- nd tr)]
        tl [(- tl nd) d]
        bl [(- d) (- bl nd)]
        br [(- nd br) (- d)]))))

(defn part1 [n]
  (transduce (map #(if (neg? %) (- %) %)) + (coords n)))

(assert (== (part1 1) 0))
(assert (== (part1 12) 3))
(assert (== (part1 23) 2))
(assert (== (part1 1024) 31))

(defn adj [[x y]]
  (->> (for [xx [(dec x) x (inc x)] yy [(dec y) y (inc y)]] [xx yy])
       (remove #{[x y]})))

(def adj-sums
  (iterate
    (fn [m]
      (let [c (coords (inc (count m)))]
        (assoc m c (transduce (map #(get m % 0)) + (adj c)))))
    {[0 0] 1}))

(defn part2 [n]
  (loop [m {[0 0] 1}]
    (let [c (coords (inc (count m)))
          x (transduce (map #(get m % 0)) + (adj c))]
      (if (< n x) x (recur (assoc m c x))))))