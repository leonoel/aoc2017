(ns aoc2017.utils
  (:require [clojure.tools.reader.reader-types :as rdr]))

(defn read-lines [s]
  (take-while seq (repeatedly (partial rdr/read-line (rdr/push-back-reader s)))))
