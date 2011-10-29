(defun lp ()
  (let ((s (read-line)))
    (cond
      ((string= s "ready?") (format t "ready!~%") (finish-output))
      ((string= s "generate code")
        (let ((x (random 4))
              (y (random 4)))
              (loop for i from 0 to 24 do
                (let ((sharp (format nil "~A~A"
                                (if (and
                                      (= y (round (/ i 5)))
                                      (= x (- i (* y 5))))
                                    "#" ".")
                                (if (or
                                      (= i 24)
                                      (= 0 (rem (+ 1 i) 5)))
                                    #\Newline ""))))
                  (format t sharp)))))
      ((string= s "find code")
        (let* ((code (format nil "~{~A~}"
                 (loop for i from 0 to 4 collect (read-line))))
               (sharp (position #\# code))
               (y (floor (/ sharp 5)))
               (x (- sharp (* y 5))))
               (format t "~d ~d~%" x y)))                      
      ((string= s "bye!") (format t "bye!~%") (finish-output) (exit)))
    (lp)))

(lp)