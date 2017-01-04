; SICP Problems 1.30, 1.31 part (a) only, 1.32 (iterative process only), 1.33 (use odd numbers instead of prime numbers for part (a))
; Gautam Mittal & Aaron Schultz

; Exercise 1.30:
(define (sum term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (+ (term a) result))))
  (iter a 0))


; Exercise 1.31a:
(define (product term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (* (term a) result))))
  (iter a 1))

(define (factorial x)
  (product (lambda (x) x) 1 (lambda (x) (+ 1 x)) x))

(define (pi-approximate x)
  (* (/ (product (lambda (x) (* x x)) 2 (lambda (x) (+ 2 x)) x) (product (lambda (x) (* x x)) 3 (lambda (x) (+ 2 x)) (+ x 1))) (+ x 1) 2.0))


; Exercise 1.32:
(define (accumulate combiner null-value term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (combiner (term a) result))))
  (iter a null-value))

; Exercise 1.33a:
; Our beautiful filter procedure
(define (filter-accumulate combiner null-value term a next b filter)
  (define (iter a result)
    (if (> a b)
        result
        (if (filter a)
            (iter (next a) (combiner (term a) result))    
            (iter (next a) result)
        )
     ))
  (iter a null-value))

; Find sum of squares of odd numbers in range a through b
(define (sum-of-squared-odds a b)
  (filter-accumulate + 0 (lambda(x) (* x x)) a (lambda (x) (+ 1 x)) b odd?))
