; AP Computer Science Summer Homework (the Scheme part)
; Written by Gautam Mittal

; Problem 1 (Easy)
(define (factorial n)
(if (= n 1) 1 (* (factorial (- n 1)) n)))
