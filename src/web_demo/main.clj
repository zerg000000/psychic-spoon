(ns web-demo.main
  (:require [web-demo.ds :as db]
            [web-demo.routes :as routes]
            [web-demo.http :as http]
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


(def config {:db {:dbtype "h2" :dbname "example"}
             :port 3000})

(def system (atom nil))

(defn start []
  (reset! system
          (-> config
              (db/start)
              (routes/start)
              (http/start))))

(defn stop []
  (-> @system
      (http/stop)
      (routes/stop)
      (db/stop)))

(defn add-shutdown-hook [f]
  (-> (Runtime/getRuntime)
      (.addShutdownHook (Thread. f))))

(defn -main [& args]
  (add-shutdown-hook (fn []
                       (stop)
                       (println "app-stopped")))
  (start)
  (println "app-started"))
