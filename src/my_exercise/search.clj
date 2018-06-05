(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes search
  (POST "/search" request
    (str (:form-params: request))))

(def handler
  (-> search
      (wrap-defaults site-defaults)
      wrap-reload))
