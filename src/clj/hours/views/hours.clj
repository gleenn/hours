(ns hours.views.hours
  (:require [closp.views.base :as v]))

(defn layout [req body]
  (v/render
    "" (merge req {:css "/css/home.css"})
    [:div
     [:div {:class "jumbotron"}
      [:h1 "Timesheets"]
      body]]))

(defn index-page [req timesheets]
  (layout req
          [:div
           [:table
            [:tbody
             (for [timesheet timesheets]
               [:tr [:th (:start_date timesheet)]])]]
           [:p
            [:form {:action "/timesheets" :method "POST"}
             (v/af-token)
             [:input {:value "Create Timesheet"
                      :type  "submit"
                      :class "btn btn-primary btn-lg"
                      :role  "button"}]]]]))

(defn show-page [req timesheet]
  (layout req
    [:div
     [:div (str timesheet)]
     [:div {:class "time"} (:start_date timesheet)]
     [:div {:class "time"} (v/format-time (:start_date timesheet))]]))
