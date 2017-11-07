(ns shouter.models.migration
  (:require [clojure.java.jdbc :as sql]
            [shouter.models.shout :as shout]))

(defn migrated? []
  (-> (sql/query shout/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='shouts'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands shout/spec
                        (sql/create-table-ddl
                         :shouts
<<<<<<< HEAD
                         [[:id :serial "PRIMARY KEY"]
                          [:body :varchar "NOT NULL"]
                          [:created_at :timestamp
                           "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
=======
                         [:id :serial "PRIMARY KEY"]
                         [:body :varchar "NOT NULL"]
                         [:created_at :timestamp
                          "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
>>>>>>> solution
    (println " done")))
