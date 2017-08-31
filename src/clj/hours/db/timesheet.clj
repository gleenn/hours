(ns hours.db.timesheet
  (:require [clojure.java.jdbc :as j]
            [clj-time.coerce :as tc]
            [clj-time.jdbc]
            [clojure.java.jdbc :as jdbc]
            [clojure.tools.logging :as log]))

(defn all [db user-id]
  (j/query db ["select * from timesheets where user_id = ? limit 1" user-id]))

(defn find-by-user-id-and-id [db user-id id]
  (first (j/query db ["select * from timesheets where user_id = ? and id = ? limit 1" user-id id])))

(defn- get-timesheet-by-id [db id]
  (first (j/query db ["select * from timesheets where id = ? limit 1" id])))

(defn timesheetname-exists? [db id] (some? (get-timesheet-by-id db id)))

(defn create [db user-id start-date]
  (first (j/insert! db :timesheets {:user_id user-id :start_date (jdbc/sql-value start-date)})))

(defn update-timesheet [db id fields]
  (j/update! db :timesheets fields ["id = ?" id]))

(defn delete-timesheet [db id] (j/delete! db :timesheets ["id = ?" id]))
