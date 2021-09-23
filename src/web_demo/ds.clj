(ns web-demo.ds
  (:require [next.jdbc :as jdbc]))

(def db {:dbtype "h2" :dbname "example"})


(defn start [{:keys [db] :as config}]
  (assoc config :ds (jdbc/get-datasource db)))

(defn stop [{:keys [ds] :as config}]
  (.close ds)
  (dissoc config :ds))