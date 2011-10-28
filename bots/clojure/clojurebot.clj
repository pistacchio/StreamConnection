(ns org.github.pistacchio.streamcommunication.bots.clojure
  (:gen-class))
  
(defn split5
  ([s acc]
    (if (empty? s)
         acc
         (split5 (drop 5 s) (concat acc [(take 5 s)]))))
  ([s] (split5 s [])))

(defn read5 []
  (loop [code "" n 5]
    (if (zero? n)
      code
      (recur (str code (read-line)) (dec n)))))

(defn run
  []
  (loop [input (read-line)]
    (do
      (cond
        (= input "ready?") (println "ready!")
        (= input "generate code") (let [sharp (rand-int 25)
                                        code (assoc (zipmap (range 25) (repeat \.)) sharp \#)
                                        split-code (split5 (vals code))]
                                    (doseq [c split-code]
                                      (println (reduce str c))))
        (= input "find code") (let [code (read5)
                                    sharp (.indexOf code "#")
                                    y (int (/ sharp 5))
                                    x (- sharp (* y 5))]
                                   (println (str x " " y)))
        (= input "bye!") (do (println "bye!") (System/exit 0)))
      (recur (read-line)))))

(defn -main [& args]
  (run)) ; *flush-on-newline* is defaulted to true
