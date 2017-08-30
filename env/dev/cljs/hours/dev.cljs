(ns hours.dev
  (:require [schema.core :as s]
            [closp.core :as core]))

(s/set-fn-validation! true)

(enable-console-print!)

(defn main [] (core/main))
