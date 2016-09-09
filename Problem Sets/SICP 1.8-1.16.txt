SICP Problems 1.8, 1.9, 1.10, 1.14 (by hand), 1.16
Gautam Mittal & Aaron Schultz

Exercise 1.8:
(define (cube x) (* x x x))

(define (good-enough? guess x)
  (< (abs (- (cube guess) x)) 0.00001))

(define (improve guess x)
   (/ (+ (/ x (* guess guess)) (* 2 guess)) 3))
  
(define (cbrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (cbrt-iter (improve guess x)
                 x)))

(define (cbrt x)
  (cbrt-iter 1.0 x))
      
 
Exercise 1.9:
The procedures are both recursive because the procedures call themselves. In both procedures, a is decremented by 1 while b is incremented by 1. When a hits 0, the sum is equal to b. In the first procedure, inc calls the procedure to through applicative-order evaluation, while in the second procedure, the procedure directly calls itself.


Exercise 1.10:
1024
65536
65536
f computes 2n
g computes 2^n
h computes 2^(h n-1)
k computes 5n^2

Exercise 1.16:
(require (lib "trace.ss"))

(define (fast-expt b n)
  (fast-iter 1 b n))
 
(define (fast-iter a b n)
  (cond ((= n 0) a)
        ((even? n) (fast-iter a (* b b) (/ n 2)))
        (else (fast-iter (* a b) b (- n 1))))
  )

; Proving that my function is in fact iterative
(trace fast-iter)
