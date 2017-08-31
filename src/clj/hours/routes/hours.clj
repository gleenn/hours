(ns hours.routes.hours
  (:require [compojure.core :refer [routes GET POST]]
            [hours.views.hours :as vh]
            [clojure.java.jdbc :as j]
            [clojure.walk :refer [keywordize-keys]]
            [noir.session :as session]
            [ring.util.json-response :refer [json-response]]
            [ring.util.response :as response]
            [clj-time.core :as time]
            [clj-time.coerce :as tc]
            [closp.views.base :as v]))

(defn index-page [req db]
  (vh/index-page req))

(defn create-timesheet-page [req db]
  (if-let [user-id (session/get :user-id)]
    #_(closp.db.user/get-user-by-id db (-> req :params))
    (response/response (j/insert! db "timesheets" {:user_id user-id :start_date (tc/to-sql-time (time/now))}))
    (do (session/flash-put! :flash "can't do that hal, gotta be logged in")
        (response/redirect "/hours"))))

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
