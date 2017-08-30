(ns hours.views.hours
  (:require [closp.views.base :as v]))

(defn index-page [req]
  (v/render
    "" (merge req {:css "/css/home.css"})
    [:div
     [:div #_{:class "jumbotron"}
      [:h1 "Hours"]
      [:p
       [:a {:href "/hours/create?_method=POST", :class "btn btn-primary btn-lg", :role "button"} "Create Timesheet"]]]]))
