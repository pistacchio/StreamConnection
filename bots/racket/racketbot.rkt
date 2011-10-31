#lang racket

(define (do-loop)
  (let ([line (regexp-replace #rx"\n|\r" (read-line) "")])
    (cond
      ((equal? line "ready?")
       (printf "ready!\n")
       (flush-output))
      ((equal? line "generate code")
       (let ([sharp (random 25)])
         (for ([i (in-range 25)])
           (printf (if (= i sharp) "#" "."))
           (when (= (modulo (+ i 1) 5) 0) (printf "\n") (flush-output)))))
      ((equal? line "find code")
       (define (read5 acc num)
         (if (= num 0)
            acc
            (read5 (string-append acc (read-line)) (- num 1))))
       (let* ([code (read5 "" 5)]
              [sharp (car (first (regexp-match-positions #rx"#" code)))]
              [y (floor (/ sharp 5))]
              [x (- sharp (* y 5))])
         (printf "~a ~a~n" x y) (flush-output)))
      ((equal? line "bye!")
       (printf "bye!\n")
       (flush-output)
       (exit))))
    (do-loop))

(do-loop)