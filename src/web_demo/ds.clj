(ns web-demo.ds
  (:require [next.jdbc :as jdbc]))


(defn start [{:keys [db] :as config}]
  (assoc config :ds (jdbc/get-datasource db)))

(defn stop [{:keys [ds] :as config}]
  (dissoc config :ds))