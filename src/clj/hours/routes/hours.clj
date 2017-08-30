(ns hours.routes.hours
  (:require [compojure.core :refer [defroutes GET]]
            [hours.views.hours :as vh]
            [ring.util.response :refer [response]]))


(defn index-page [req]
  (vh/index-page req))

(defroutes hours-routes
           (GET "/hours" req (index-page req))
           ;(GET "/contact" req (contact-page req))
           ;(GET "/tos" req (tos-page req))
           ;(GET "/cookies" req (cookies-page req))
           ;(GET "/" req (home-page req))
           ;(GET "/reagent-example" req (reagent-example req))
           ;(GET "/ajax/page/init" [] (ajax-initial-data))
           )
