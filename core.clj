(ns csv-reader.core
  [:require [clojure.string :as str]]
  (:gen-class))


(def name-of-file "text.csv")

;;Define function that reads an input file 
(defn csv-read
  [name-of-file]
  (slurp (str/join "/home/marc/csv-reader/src/csv_reader/" name-of-file)))
;;Read text file 
(csv-read text.csv)

;;debug
(print name-of-file)
(str/join "/home/marc/csv-reader/src/csv_reader/text.csv" name-of-file)

;;workaround
(def csv-string
  (slurp "/home/marc/csv-reader/src/csv_reader/text.csv"))


;;header
(map keyword (str/split (-> csv-string (str/split-lines) (first)) #",")) ;=>"a,b,c"

;;lines
(map
 #(str/split % #",")
 (vec (-> csv-string (str/split-lines) (rest))))

;;map zipmap over each keyword and line
(map
 #(zipmap
   ;;map over each header and convert to keyword
   (map keyword
        (str/split
         (-> csv-string
             (str/split-lines)
             (first))
         #","))
   %)
 ;;map over each line and split via commas
 (map
 #(str/split % #",")
 (vec (-> csv-string (str/split-lines) (rest)))))

 

