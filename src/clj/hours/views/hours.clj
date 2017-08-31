(ns hours.views.hours
  (:require [closp.views.base :as v]))

(defn layout [req body]
  (v/render
    "" (merge req {:css "/css/home.css"})
    [:div
     [:div {:class "jumbotron"}
      [:h1 "Timesheets"]
      body
      [:p
       [:form {:action "/timesheets" :method "POST"}
        (v/af-token)
        [:input {:value "Create Timesheet"
                 :type  "submit"
                 :class "btn btn-primary btn-lg"
                 :role  "button"}]]]]]))

(defn index-page [req timesheets]
  (layout req
          [:table
           [:tbody
            (for [timesheet timesheets]
              [:tr [:th (:start_date timesheet)]])]]))

(defn show-page [req timesheet]
  (layout req
    [:div
     (str timesheet)
     [:div {:class "time"} (:start_date timesheet)]]))
