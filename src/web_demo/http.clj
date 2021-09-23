(ns web-demo.http
  (:require [org.httpkit.server :refer [run-server]]))

(defn start [{:keys [port handler] :as config}]
  (assoc config :http (run-server handler {:port port})))

(defn stop [{:keys [http] :as config}]
  (http)
  (dissoc config :http))