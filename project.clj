(defproject sketchapp "0.1.0-SNAPSHOT"
  :description "Client-server collaboration platform."
  :url "https://github.com/pkulev/sketchapp"
  :license {:name "MIT"
            :url "https://mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.novemberain/monger "3.1.0"]]
  :plugins [[lein-cljfmt "0.7.0"]
            [lein-codox "0.10.7"]]
  :repl-options {:init-ns sketchapp.core}
  :main sketchapp.core/main
  :source-paths ["src"])
