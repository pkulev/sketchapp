(ns sketchapp.core
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :refer :all])
  (:use clojure.pprint))

(defn check-db
  "Write something."
  []
  (let [conn (mg/connect {:host "db"})
        db (mg/get-db conn "test-db")]
    (mc/insert db "keks" {:kek_1 10 :kek_2 20})
    (pprint (mc/find-one-as-map db "keks" {:kek_1 10}))))

(defn main
  "Entry point."
  []
  (check-db)
  (pprint
   (with-collection (mg/get-db (mg/connect {:host "db"}) "test-db") "keks"
     (find {})
     (fields [:kek_1])
     (limit 10))))
