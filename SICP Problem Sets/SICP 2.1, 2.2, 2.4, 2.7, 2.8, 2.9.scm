; SICP 2.1, 2.2, 2.4, 2.7, 2.8, 2.9
; Gautam Mittal & Aaron Schultz

; Exercise 2.1
(define (make-rat x y)
  (let ((g (gcd x y)))
	(cond ((eqv? (positive? x) (positive? y)) (cons (abs (/ x g)) (abs (/ y g))))
		(else (cons (* -1 (abs (/ x g))) (abs (/ y g)))))))

; Exercise 2.2
; Stuff that concerns points
(define (point x y)
  (cons x y))

(define (x-point point)
  (car point))

(define (y-point point)
  (cdr point))

; Stuff that concerns segments
(define (start-segment seg)
  (car seg))

(define (end-segment seg)
  (cdr seg))

(define (make-segment p1 p2)
  (cons p1 p2))
  

; Pretty printing points
(define (print-point p)
  (display "(")
  (display (x-point p))
  (display ",")
  (display (y-point p))
  (display ")"))

; The midpoint procedure
(define (midpoint-segment seg)
  (print-point 
	(point 
		(/ (+ (x-point (start-segment seg)) (x-point (end-segment seg))) 2) 
		(/ (+ (y-point (start-segment seg)) (y-point (end-segment seg))) 2))))

; Exercise 2.4
(define (cdr z)
  (z (lambda (p q) q)))

; Exercise 2.7
(define (make-interval a b) (cons a b))

; We assume Alyssa doesn't care about interval notation and that (UPPER, LOWER) and (LOWER, UPPER) are both valid.
(define (upper-bound interval) 
  (if (> (car interval) (cdr interval))
      (car interval)
      (cdr interval)))
  
(define (lower-bound interval) 
  (if (< (car interval) (cdr interval))
      (car interval)
      (cdr interval)))


; Exercise 2.8
(define (sub-interval interval1 interval2)
  (make-interval (- (upper-bound interval1) (upper-bound interval2)) (- (lower-bound interval1) (lower-bound interval2))))

; Exercise 2.9 (NOT COMPLETE)
(define (width-interval i)
  (/ (- (upper-bound i) (lower-bound i)) 2))
