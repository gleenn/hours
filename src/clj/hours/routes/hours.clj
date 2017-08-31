(ns hours.routes.hours
  (:require [compojure.core :refer [routes GET POST]]
            [hours.views.hours :as vh]
            [ring.util.response :refer [response]]
            [ring.util.response :as response]
            [clojure.java.jdbc :as j]))


(defn index-page [req db]
  (vh/index-page req))

(defn create-timesheet-page [req db]
  #_(closp.db.user/get-user-by-id db (-> req :params))
  #_(j/insert! db "timesheets" {})
  #_(response/r (str req))
  (response/redirect "/hours"))

(defn hours-routes [db]
  (routes
    (GET "/hours" req (index-page req db))
    (POST "/hours" req (create-timesheet-page req db))
    ;(GET "/contact" req (contact-page req))
    ;(GET "/tos" req (tos-page req))
    ;(GET "/cookies" req (cookies-page req))
    ;(GET "/" req (home-page req))
    ;(GET "/reagent-example" req (reagent-example req))
    ;(GET "/ajax/page/init" [] (ajax-initial-data))
    ))
