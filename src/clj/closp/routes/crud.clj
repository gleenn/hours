(ns closp.routes.crud
  (:require [compojure.core :refer [routes ANY]]
            [closp.db.entities :as db-e]
            [closp.crudify.crudify :as crud]))






(defn crud [req entity action db]
  (crud/crudify req action db db-e/entities (keyword entity)))


(defn crud-routes [db]
  (routes
    (ANY "/crud/:entity/:action" [entity action :as req] (crud req entity action db))))
