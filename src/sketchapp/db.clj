(ns sketchapp.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(def +db-host+ "db")
(def +db-name+ "sketchapp")

(defn get
  "Return database object."
  [& {:keys [name] :or {name +db-name+}}]
  (let [conn (mg/connect {:host +db-host+})
        db (mg/get-db conn name)]
    db))
