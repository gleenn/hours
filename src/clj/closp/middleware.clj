(ns closp.middleware
  (:require [clojure.tools.logging :as log]
            [prone.middleware :as prone]
            [taoensso.tempura :refer [tr] :as tempura]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [buddy.auth.accessrules :refer [wrap-access-rules]]
            [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
            [noir.session :as sess]
            [de.sveri.clojure.commons.middleware.util :refer [wrap-trimmings]]
            [ring.middleware.transit :refer [wrap-transit-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [closp.locale :as loc]
            [closp.service.auth :refer [auth-backend]]
            [closp.service.auth :as auth]))

(defn add-locale [handler]
  (fn [req]
    (let [accept-language (get-in req [:headers "accept-language"])
          short-languages (or (tempura/parse-http-accept-header accept-language) ["en"])]
      (handler (assoc req :localize (partial tr
                                             {:default-locale :en
                                              :dict           loc/local-dict}
                                             short-languages))))))

(defn add-req-properties [handler config]
  (fn [req] (handler (assoc req :config config))))

(def development-middleware
  [#(prone/wrap-exceptions % {:app-namespaces ['closp]})
   wrap-reload])

(defn production-middleware [config]
  [#(add-req-properties % config)
   add-locale
   #(wrap-access-rules % {:rules auth/rules})
   #(wrap-authorization % auth/auth-backend)
   #(wrap-transit-response % {:encoding :json :opts {}})
   wrap-anti-forgery
   wrap-trimmings])

(defn load-middleware [config]
  (concat (production-middleware config)
          (when (= (:env config) :dev) development-middleware)))
