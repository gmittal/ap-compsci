; AP Computer Science Summer Homework (the Scheme part)
; Written by Gautam Mittal

; Problem 1 (Easy)
(define (factorial n)
(if (= n 1) 1 (* (factorial (- n 1)) n)))

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

; Problem 5 (Medium)
(define (safe-divider n)
  (lambda(x)
    (if (= x 0)
        "cow"
        (/ n x))
    )
  )
       