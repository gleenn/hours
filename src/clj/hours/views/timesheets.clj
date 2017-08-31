(ns hours.views.timesheets
  (:require [closp.views.base :as v]))

(defn layout [title req body]
  (v/render
    title (merge req {:css "/css/home.css"})
    [:div
     [:div {:class "jumbotron"}
      [:h1 title]
      body]]))

(defn index-page [req timesheets]
  (layout "Timesheets" req
          [:div
           [:ul
            (for [{:keys [start_date id]} timesheets]
              (let [formatted-timesheet-date (v/format-time start_date)]
                [:li [:a {:href (str "/timesheets/" id)} formatted-timesheet-date]]))]
           [:p
            [:form {:action "/timesheets" :method "POST"}
             (v/af-token)
             [:input {:value "Create Timesheet"
                      :type  "submit"
                      :class "btn btn-primary btn-lg"
                      :role  "button"}]]]]))

(defn show-page [req timesheet]
  (let [formatted-timesheet-date (v/format-time (:start_date timesheet))
        id (:id timesheet)]
    (layout (str "Timesheet - " formatted-timesheet-date) req
            [:div
             [:div {:class "time"} formatted-timesheet-date]
             [:form {:action (str "/timesheets/" id "/entry") :method "POST"}
              (v/af-token)
              [:input {:name "description"
                       :type "text"}]
              [:input {:value "Create Entry"
                       :type  "submit"
                       :class "btn btn-primary btn-lg"
                       :role  "button"}]]])))
