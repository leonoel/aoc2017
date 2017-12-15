(ns aoc2017.utils
  (:require [clojure.tools.reader.reader-types :as rdr]
            [clojure.tools.reader.edn :as edn]))

(defn read-lines [s]
  (take-while seq (repeatedly (partial rdr/read-line (rdr/push-back-reader s)))))

(defn read-numbers [row]
  (take-while some? (repeatedly (partial edn/read (rdr/push-back-reader row) false nil nil))))

(defn read-lines-of-numbers [s]
  (map read-numbers (read-lines s)))
