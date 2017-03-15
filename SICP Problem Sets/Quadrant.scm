; Gautam's weird Scheme implementation of the Quadrant Problem

(define pi 3.1415926535897932384626434)
(define (quadrant x y)
  (+ (modulo (floor (/ (+ (atan y x) (* 2 pi)) (/ pi 2))) 4) 1))
