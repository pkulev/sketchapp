(ns sketchapp.core
  (:require [monger.collection :as mc]
            [monger.query :refer :all]
            [sketchapp.db :as db]
            [sketchapp.web])
  (:use clojure.pprint))

(defn check-db
  "Write something."
  []
  (let [db (db/get)]
    (mc/insert db "keks" {:kek_1 10 :kek_2 20})
    (pprint (mc/find-one-as-map db "keks" {:kek_1 10}))))

(defn main
  "Entry point."
  []
  (check-db)
  (pprint
   (with-collection (db/get) "keks"
     (find {})
     (fields [:kek_1])
     (limit 10)))
  (sketchapp.web/start-server))
