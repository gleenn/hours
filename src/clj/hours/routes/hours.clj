(ns hours.routes.hours
  (:require [compojure.core :refer [defroutes GET POST]]
            [hours.views.hours :as vh]
            [ring.util.response :refer [response]]
            [ring.util.response :as response]))


(defn index-page [req]
  (vh/index-page req))

(defn create-timesheet-page [req]
  (response/redirect "/hours"))

(defroutes hours-routes
           (GET "/hours" req (index-page req))
           (POST "/hours" req (create-timesheet-page req))
           ;(GET "/contact" req (contact-page req))
           ;(GET "/tos" req (tos-page req))
           ;(GET "/cookies" req (cookies-page req))
           ;(GET "/" req (home-page req))
           ;(GET "/reagent-example" req (reagent-example req))
           ;(GET "/ajax/page/init" [] (ajax-initial-data))
           )
