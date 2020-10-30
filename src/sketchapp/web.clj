(ns sketchapp.web
  (:require [aleph.http :as http]
            [compojure.core :as compojure :refer [GET]]
            [compojure.response :refer [Renderable]]
            [compojure.route :as route]
            [ring.middleware.params :as params]
            [manifold.deferred :as d]
            [manifold.stream :as s]
            [clojure.core.async :as a]))

(defn hello-world-handler [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello!"})

(defn delayed-hello-world-handler [req]
  (d/timeout!
   (d/deferred)
   1000
   (hello-world-handler req)))

(extend-protocol Renderable
  clojure.lang.IDeref
  (render [d _] (d/deferred d)))

(defn delayed2-hello-world-handler [req]
  (s/take!
   (s/->source
    (a/go
      (a/let [_ (a/<! (a/timeout 1000))]
        (hello-world-handler req))))))

(def handler
  (params/wrap-params
   (compojure/routes
    (GET "/" [] hello-world-handler)
    (GET "/delayed" [] delayed-hello-world-handler)
    (GET "/delayed2" [] delayed2-hello-world-handler)
    (route/not-found "No such page."))))

(defn start-server []
  (http/start-server handler {:port 10000}))
