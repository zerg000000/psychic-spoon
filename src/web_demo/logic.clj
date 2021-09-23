(ns web-demo.logic
  (:require [next.jdbc :as jdbc]))

(defn get-all-address [ds]
  (str (jdbc/execute! ds ["select * from address"])))