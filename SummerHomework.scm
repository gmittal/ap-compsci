; AP Computer Science Summer Homework (the Scheme part)
; Written by Gautam Mittal

; Problem 1 (Easy)
(define (factorial n)
(if (= n 1) 1 (* (factorial (- n 1)) n)))

(define (remove-elem xs elem)
    (cond
      ((null? ll) '())
      ((eq? (car ll) elem) (cdr ll))
       (else 
        (cons (car ll) (remove-elem (cdr ll) elem))))
    )

; Problem 2 (Medium)
(define (odds lst)
  (length (apply append (map (lambda(n)
         (if (odd? n)
             (list n)
             '())) lst)))
  )

; Problem 3 (Medium)
(define (filter p lst)
  (apply append (map (lambda(n)
         (if (p n)
             (list n)
             '())) lst))
  )

; Problem 4 (Medium Hard)
(define (reverse lst)
  (map (lambda(n)
         (append (cdr lst) (list n))
         ) lst)
  )
       