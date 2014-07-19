(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [guestbook.views.layout :as layout]
            [hiccup.form :refer :all]
            [guestbook.models.db :as db]))

(defn format-time
  "Convert millis to SimpleDateFormat."
  [timestamp]
  (-> "dd/MM/yyyy"
      (java.text.SimpleDateFormat.)
      (.format timestamp)))

(defn show-guests
  "Show existing messages from previous guests."
  []
  [:ul.guests (for [{:keys [message name timestamp]} (db/read-guests)]
              [:li
               [:blockquote message]
               [:p "-" [:cite name]]
               [:time (format-time timestamp)]])])

(defn home
  [& [name message error]]
  (layout/common
   [:h1 "Hello World!"]
   [:p "Welcome to my guestbook"]
   [:p error]
   ; here we call our show-guests function
   ; to generate the list of existing comments
   (show-guests)
   [:hr]
   (form-to [:post "/"]
            [:p "Name:"]
            (text-field "name" name)
            [:p "Message"]
            (text-area {:rows 10 :cols 40} "message" message)
            [:br]
            (submit-button "comment"))))

(defn save-message
  "Save a message from a form."
  [name message]
  (cond
   (empty? name)
     (home name message "Some dummy forgot to leave his name")
   (empty? message)
     (home name message "Don't you have something to say?")
   :else
     (do
       (db/save-message name message)
       (home))))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
