(ns guestbook.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))

; Definition for database connection.
(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "db.sq3"})

(defn create-guestbook-table
  "Creates table for storing guest messages."
  []
  ; with-connection ensures that the connection is correctly cleaned up
  ; after use.
  (sql/with-connection
    db
    (sql/create-table
       :guestbook ; This key represents the table name to create.
       [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
       [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
       [:name "TEXT"]
       [:message "TEXT"])
    (sql/do-commands "CREATE INDEX timestamp_index ON guestbook (timestamp)")))

(defn read-guests
  "Reads all gueset messages from the db."
  []
  (sql/with-connection
    db
    ; Runs an arbitrary SQL statement and stores the result in `res`
    (sql/with-query-results
      res
      ["SELECT * FROM guestbook ORDER BY timestamp DESC"]
      ; Since `res` is lazy, we perform doall to realize the value of `res`.
      (doall res))))

(defn save-message
  "Save a user and message pair in the db."
  [name message]
  (sql/with-connection
    db
    (sql/insert-values
     :guestbook
     [:name :message :timestamp]
     [name message (new java.util.Date)])))

