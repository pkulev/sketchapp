(ns sketchapp.web
  (:require [aleph.http :as http]))

(defn handler [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello!"})

(defn start-server []
  (http/start-server handler {:port 10000}))
