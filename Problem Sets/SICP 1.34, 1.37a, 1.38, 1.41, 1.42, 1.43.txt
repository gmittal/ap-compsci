; Simply Scheme List Fundamentals 17.1, 17.2, 17.3, 17.8--17.12, 17.14
; Gautam Mittal & Aaron Schultz

; Exercise 17.1
> Rod
> Chris
> Chris Colin Hugh Paul
> Err: It's not a pair
> ((Rod Argent) . (Chris White))
> (Rod Argent Chris White)
> ((Rod Argent) (Chris White))
> Chris
> (Colin Bluntstone)
> #f ; Because you can only search by the car of each sublist

; Exercise 17.2
(define (f1 list1 list2)
  (list (append (cdr list1) (cons (car list2) '()))))

(define (f2 list1 list2)
  (cons (cdr list1) (cons (cadr list2) '())))

(define (f3 list1 list2)
  (append list1 list1))

(define (f4 list1 list2)
  (list (list (car list1) (car list2)) (append (cdr list1) (cdr list2))))

; Exercise 17.3
The invocation of map returns a list of procedures, which when called add an input to the corresponding element of the list described in the invocation.
For example, (car (map (lambda (x) (lambda (y) (+ x y))) '(1 2 3 4))) returns:
(lambda (y) (+ 1 y)).

; Exercise 17.8
(define (member? x list)
  (if (not (null? list))
      #f
      (if (eqv? (car list) x)
          #t
          (member? x (cdr list)))))

; Exercise 17.9
(define (list-ref list i) 
  (list-ref-iter 0 list i))

(define (list-ref-iter a list i)
  (if (= a i)
      (car list)
      (list-ref-iter (+ a 1) (cdr list) i)))
      
; Exercise 17.10
(define (length list)
  (length-iter 0 list))

(define (length-iter a list)
  (if (not (pair? list))
      a
      (length-iter (+ a 1) (cdr list))))

; Exercise 17.11
(define (position-from-end x list)
  (if (not (pair? list))
      #f
      (if (eqv? (car list) x)
          (length list)
          (position-from-end x (cdr list)))))

(define (before-in-list? list x y)
  (> (position-from-end x list) (position-from-end y list)))

; Exercise 17.12
(define (flatten mlist)
  (if (list-in-list? mlist)
      (flatten-iter mlist '())
      mlist))

(define (list-in-list? list)
  (if (null? list)
      #f
      (if (list? (car list))
          #t
          (list-in-list? (cdr list)))))
  
(define (flatten-iter mlist clean)
  (if (null? mlist)
      (flatten clean)
      (if (list? (car mlist))
          (flatten-iter (cdr mlist) (append clean (car mlist)))
          (flatten-iter (cdr mlist) (append clean (list (car mlist)))))))


; Exercise 17.14
(define (branch ref list)
  (if (null? (cdr ref))
      (list-ref list (- (car ref) 1))
      (branch (cdr ref) (list-ref list (- (car ref) 1)))))





; SICP 1.34, 1.37a, 1.38, 1.41, 1.42, 1.43
; Gautam Mittal & Aaron Schultz

; Exercise 1.34
There will be an error, because you're recursively handing in an undefined procedure.

; Exercise 1.37a
(define (cont-frac n d k)
  (cf-iter n d k 1))

(define (cf-iter n d k a)
  (if (= a k)
      (n a)
      (/ (n a) (+ (d a) (cf-iter n d k (+ 1 a))))))
        
; Exercise 1.41

; Exercise 1.42

; Exercise 1.43
