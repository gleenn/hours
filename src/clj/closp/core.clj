(ns closp.core
  (:require [clojure.tools.logging :as log]
            [system.repl :refer [set-init! start]]
            [closp.components.components :refer [prod-system]])
  (:gen-class))

(defn -main [& args]
  (set-init! #'prod-system)
  (start)
  (log/info "server started."))
