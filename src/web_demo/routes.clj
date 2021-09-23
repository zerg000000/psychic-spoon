(ns web-demo.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [web-demo.logic :as logic]))

(defn create-routes [ds]
  (defroutes app
    (GET "/" [] (logic/get-all-address ds))
    (route/not-found "Page Not Found")))



(defn start [{:keys [ds] :as config}]
  (assoc config :handler (create-routes ds)))

(defn stop [{:keys [ds] :as config}]
  (dissoc config :handler))