; SICP 2.17, 2.18, 2.20, 2.21, 2.22, 2.23, 2.24, 2.25, 2.26, 2.27; Extra credit: 2.19
; Completed by Aaron Schultz and Gautam Mittal

; Exercise 2.17
(define (last-pair lyst)
  (if (eqv? (cddr lyst) '())
      (cdr lyst)
      (last-pair (cdr lyst))))

; Exercise 2.18
(define (remove-last lyst)
    (if (eqv? (cdr lyst) '())
        '()
        (cons (car lyst) (remove-last (cdr lyst)))))

(define (reverse lyst)
  (if (eqv? (cdr lyst) '())
      lyst
      (append (last-pair lyst) (reverse (remove-last lyst)))))
  
; Exercise 2.20
(define (same-parity x . z)
  (if (odd? x)
      (same-helper odd? (list x) z)
      (same-helper even? (list x) z)))

(define (same-helper pred? cleanlyst lyst)
  (if (null? lyst)
      cleanlyst
      (if (pred? (car lyst))
          (same-helper pred? (list (append cleanlyst (car lyst))) (cdr lyst))
          (same-helper pred? cleanlyst (cdr lyst)))))

; Exercise 2.21
(define (square-list items)
  (if (null? items)
      '()
      (cons (* (car items) (car items)) (square-list (cdr items)))))

(define (square-list items)
  (map (lambda (x) (* x x) items)))

; Exercise 2.22 - 'Is it "Lu-ee" or "Lu-is"?' -- Frederick Wang, 2016
In Louis' second example, the cons statement is cons'ing a list to a number, when cons needs a number as the first argument and a list as the second in order to return a clean list. The cdr has become a number, rather than a pointer to another list.

; Exercise 2.23
(define (for-each proc lyst)
  (proc (car lyst))
  (if (null? (cddr lyst))
      (proc (cadr lyst))
      (for-each proc (cdr lyst))))
  
; Exercise 2.24
Interpreter: (list 1 (list 2 (list 3 4)))
We wrote the boxes on the board.

; Exercise 2.25
(car (cdr (car (cdr (cdr '(1 3 (5 7) 9))))))
(car (car '((7))))
(car (cdr (car (cdr (car (cdr (car (cdr (car (cdr (car (cdr '(1 (2 (3 (4 (5 (6 7))))))))))))))))))

; Exercise 2.26
(1 2 3 4 5 6)
((1 2 3) 4 5 6)
((1 2 3) (4 5 6))

; Exercise 2.27
(define (deep-reverse lyst)
  (cond ((null? lyst) lyst)
        ((list? (car lyst)) (reverse (cons (deep-reverse (car lyst)) (reverse (deep-reverse (cdr lyst))))))
        (else (reverse (cons (car lyst) (reverse (deep-reverse (cdr lyst))))))))

; Old SCVAL Problem
(define (triangle n)
  (* n (+ n 1) (/ 1 2)))

(define (p-of-three? n)
  (let ((x (ceiling (expt n (/ 1 3)))))
  (= (* (- x 1) x (+ x 1)) n)
  ))

(define (find-special-triangles)
  (fst-iter 1 '()))

(define (fst-iter n lyst)
  (cond ((> n 22736)
         lyst)
        ((p-of-three? (triangle n)) 
         (fst-iter (+ n 1) (append lyst (list (list n (floor (expt n (/ 1 3))) (triangle n))))))
        (else (fst-iter (+ n 1) lyst))))
               
  
