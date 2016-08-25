Aaron Schultz and Gautam Mittal
Problem Set 1.1-1.6

Exercise 1.1:
10
12
8
3
6
19
#f
4
16
6
16

Exercise 1.2:
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5))))) (* 3 (- 6 2) (- 2 7)))

Exercise 1.3:
(define (threeSquare x y z)
  (cond ((and (> x z) (> y z)) (+ (* x x) (* y y)))
        ((and (> x y) (> z y)) (+ (* x x) (* z z)))
        (else (+ (* z z) (* y y)))))

Exercise 1.4:
If b is greater than 0, then the procedure returns a+b, while if b is less than 0, the procedure returns a-b, effectively returning a+|b|.

Exercise 1.5:
An applicative-order evaluator evaluates (p) before test is called. This causes the program to enter an infinite loop that never evaluates anything because it is trying to evaluate p which evaluates to p and so on. If it were a normal-order evaluator, (p) would be evaluated only if x does not equal 0.

Exercise 1.6:
The program falls into an infinite loop that never returns. This is because the new-if is applicative-order, and needs to evaluate all parameters in new-if. This results in a recursive call of sqrt-iter which sends the program into an infinite loop.
