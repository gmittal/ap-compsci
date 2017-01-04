; SICP 3.15, 3.17, 3.22, delete!, 3.25, 3.27
; Gautam Mittal and Aaron Schultz

; Exercise 3.15
; We drew a box and pointer diagram

; Exercise 3.17
(define (count-pairs pair)
  (let ((visited '()))
    (define (visited? x lyst)
      (cond ((null? lyst) #f)
            ((eq? x (car lyst)) #t)
            (else (visited? x (cdr lyst)))))
    (define (cph pair)
      (if (and (pair? pair) (not (visited? pair visited)))
          (begin 
            (set! visited (cons pair visited))
            (+ 1 (cph (car pair)) (cph (cdr pair)))) 0))
    (begin (cph pair))))

; Exercise 3.22
(define (make-queue)
  (let ((front-ptr 'emptyqueue)
        (rear-ptr 'emptyqueue))
    (define (add element)
      (let ((pair (cons element '())))
        (if (equal? front-ptr 'emptyqueue)
            (begin (set! front-ptr pair)
                   (set! rear-ptr pair))
            (begin (set-cdr! rear-ptr pair)
                   (set! rear-ptr pair)))))
      
    (define (shift)
      (let ((element (car front-ptr)))
        (set! front-ptr (cdr front-ptr))
        element))
    
    (define (dispatch . m) 
      (cond ((null? m) front-ptr)
            ((equal? (car m) 'add) add)
            ((equal? (car m) 'shift) shift)))
    dispatch))

; The infamous delete! problem
(define (make-table)
  (cons '* ()))
(define (empty-table? t) (null? (cdr t)))

(define (insert! key value table)
  (let ((record (assoc key (cdr table))))
    (if record
        (set-cdr! record value)
        (set-cdr! table
                  (cons (cons key value) (cdr table)))))
  'ok)

; Give key and table and will return value
(define (lookup k t)
  (let ((record (assoc k (cdr t))))
    (cond (record (cdr record))
          (else #f))))

; Goes through the entire table (records) and finds if there is a key already assosciated with a value. It will either return the pair that has that key or #f.
(define (assoc key records)
  (cond ((null? records) #f)
        ((equal? key (caar records)) (car records))
        (else (assoc key (cdr records)))))

; Give a value and table and it will return key
(define (rlookup k t)
  (let ((record (rassoc k (cdr t))))
    (cond (record (car record))
          (else #f))))
(define (rassoc value records)
  (cond ((null? records) #f)
        ((equal? value (cdar records)) (car records))
        (else (rassoc value (cdr records)))))

(define (delete! k t)
  (cond ((null? (cdr t)) #f)
        ((equal? (caadr t) k)
         (set-cdr! t (cddr t)))
        (else (delete! k (cdr t)))))
      

; Exercise 3.25
(define (nd-insert! lyst-keys value table)
  (if (null? (cdr lyst-keys))
      (insert! (car lyst-keys) value table)
      (let ((record (assoc (car lyst-keys) (cdr table))))
        (if record
            (nd-insert! (cdr lyst-keys) value (cdr record)) 
            (let ((newsubtable (make-table)))
              (set-cdr! table (cons (cons (car lyst-keys) newsubtable) (cdr table)))
              (nd-insert! (cdr lyst-keys) value newsubtable)
              )     
            )))
  'ok)

(define (nd-lookup lyst-keys table)
 (if (null? (cdr lyst-keys))
     (lookup (car lyst-keys) table)
     (nd-lookup (cdr lyst-keys) (lookup (car lyst-keys) table))))

; Exercise 3.27
(define (memoize f)
  (let ((table (make-table)))
    (lambda (x)
      (let ((previously-computed-result (lookup x table)))
        (or previously-computed-result
            (let ((result (f x)))
              (insert! x result table)
              result))))))

(define memo-fib
  (memoize (lambda (n)
             (cond ((= n 0) 0)
                   ((= n 1) 1)
                   (else (+ (memo-fib (- n 1))
                            (memo-fib (- n 2))))))))
