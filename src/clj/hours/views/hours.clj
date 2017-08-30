(ns hours.views.hours
  (:require [closp.views.base :as v]))

(defn index-page [req]
  (v/render
    "" (merge req {:css "/css/home.css"})
    [:div
     [:div {:class "jumbotron"}
      [:h1 "Hours"]
      [:p
       [:form {:action "/hours" :method "POST"}
        (v/af-token)
        [:input {:value "Create Timesheet"
                 :type "submit"
                 :class "btn btn-primary btn-lg"
                 :role "button"}]]]]]))
