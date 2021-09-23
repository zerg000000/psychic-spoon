(ns web-demo.main
  (:require [org.httpkit.server :refer [run-server]]
            [web-demo.ds :as db]
            [web-demo.routes :as routes]
            [next.jdbc :as jdbc]))

(comment
  (jdbc/execute! ds ["
create table address (
  id int auto_increment primary key,
  name varchar(32),
  email varchar(255)
)"])

  (jdbc/execute! ds ["
insert into address(name,email)
  values('Sean Corfield','sean@corfield.org')"]))


(defn start [{:keys [port handler] :as config}]
  (assoc config :http (run-server handler {:port port})))

(defn stop [{:keys [http] :as config}]
  (http)
  (dissoc config :http))

(def system
  (-> {:db {:dbtype "h2" :dbname "example"}
       :port 3000}
      (db/start)
      (routes/start)
      (start)))

(-> system
    (stop)
    (routes/stop)
    (db/stop))

(jdbc/execute! (:ds system) ["select 1"])



