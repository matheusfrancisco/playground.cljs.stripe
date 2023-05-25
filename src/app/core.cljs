(ns app.core
  (:require
   ["react-dom/client" :as rdom]
   [app.lib :as lib :refer [defnc]]
   [helix.core :refer [$]]
   [helix.dom :as d]
   [lambdaisland.fetch :as fetch]
   [promesa.core :as p]))

(defnc main-view []
  (d/section
   (d/div {:class "product"}
          (d/img {:src "https://i.imgur.com/EHyR2nP.png"})
          (d/div {:class "description"}
                 (d/h3 "Stubborn Attachments")
                 (d/h5 "$20.00")))
   (d/form
    (d/button {:type "submit"
               :onClick  (fn [e]
                           (.preventDefault e)
                           (p/let [resp (fetch/get
                                         "https://api.github.com/users/seisvelas/gists"
                                         {:accept :json
                                          :content-type :json})]
                             (prn (:body resp))))}
              "Checkout"))))

;; startup
(defonce root
  (rdom/createRoot (js/document.getElementById "app")))

(defn render []
  (.render root ($ main-view)))

(defn ^:export init []
  (render))

