(ns user
  (:require [ragtime.jdbc :as jdbc]))

(def config
  {:datastore  (jdbc/sql-database {:connection-uri "jdbc:postgresql://localhost:5432/hours?user=glenn"})
   :migrations (jdbc/load-resources "migrations")})
