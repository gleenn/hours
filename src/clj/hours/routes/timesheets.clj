(ns hours.routes.timesheets
  (:require [compojure.core :refer [routes GET POST]]
            [hours.views.timesheets :as vh]
            [clojure.java.jdbc :as j]
            [clojure.walk :refer [keywordize-keys]]
            [noir.session :as session]
            [ring.util.json-response :refer [json-response]]
            [ring.util.response :as response]
            [clj-time.core :as time]
            [clj-time.coerce :as tc]
            [closp.views.base :as v]
            [hours.db.timesheet :as timesheet]
            [clojure.tools.logging :as log]
            [closp.service.auth :as auth]))

(defn redirect-unless-logged-in [req fn]
  (if (not (auth/logged-in? req))
    (response/redirect "/")
    (fn)))

(defn index [req db]
  (redirect-unless-logged-in
    req
    #(let [user-id (session/get :user-id)
           timesheets (timesheet/all db user-id)]
       (vh/index-page req timesheets))))

(defn show [{:keys [route-params] :as req} db]
  (log/info req)
  (redirect-unless-logged-in
    req
    #(let [user-id (session/get :user-id)
           timesheet-id (Integer/parseInt (:id route-params))
           timesheet (timesheet/find-by-user-id-and-id db user-id timesheet-id)]
       (log/info timesheet)
       (vh/show-page req timesheet))))

(defn create [req db]
  (log/info "create")
  (redirect-unless-logged-in
    req
    #(do
       (log/info "logged in!")
       (let [now (time/now)
               user-id (session/get :user-id)]
           (if-let [timesheet (timesheet/create db user-id now)]
             (response/redirect (str "/timesheets/" (:id timesheet)))
             (response/redirect "/timesheets")))
         #_(do (session/flash-put! :flash "can't do that hal, gotta be logged in")
             (response/redirect "/timesheets")))))

(defn timesheets-routes [db]
  (routes
    (POST "/timesheets" req (create req db))
    (GET "/timesheets" req (index req db))
    (GET "/timesheets/:id" req (show req db))
    ;(GET "/contact" req (contact-page req))
    ;(GET "/tos" req (tos-page req))
    ;(GET "/cookies" req (cookies-page req))
    ;(GET "/" req (home-page req))
    ;(GET "/reagent-example" req (reagent-example req))
    ;(GET "/ajax/page/init" [] (ajax-initial-data))
    ))
