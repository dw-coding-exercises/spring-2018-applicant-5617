; i had hope to get more done in the time, like organizing this file and the project into different files
; specifically i would have one containing my views and one containing a service that made my request
(ns my-exercise.search_results
  (:require [hiccup.page :refer [html5]]
            [clj-http.client :as client]))

; here i would like to make additional requests with varying ocd-ids
(defn get-ocd-ids [ocd-id-url]
      (def response (client/get ocd-id-url {:accept :json}))
      (let [{body :body} response
            description (get-in body "description")]
        body))

; if i had more time i would make a view in a separate file to print out each part of the body in a nicely formatted way
; i also would have liked to defined multiple options for the ocd-ids, like one with just state, or one with county
; county would be gotten based on the city information passed in
(defn display-results [req]
  (let [{:keys [params]} req
        street (get params :street)
        street-2 (get params :street-2)
        city (get params :city)
        state (get params :state)
        zip (get params :zip)]
        (def city_lower (clojure.string/lower-case city))
        (def state_lower (clojure.string/lower-case state))
        (def city_hyphen (clojure.string/replace city_lower #"(\s)" "_"))
        (def ocd-id-url (str "https://api.turbovote.org/elections/upcoming?district-divisions=ocd-division/country:us/state:" state_lower "/place:" city_hyphen))
        (html5 (get-ocd-ids ocd-id-url))))
