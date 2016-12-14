; SICP 3.1, 3.2, 3.3, 3.7, 3.8
; Aaron Schultz and Gautam Mittal

; Exercise 3.1
(define (make-accumulator x)
  (define (add n)
    (begin (set! x (+ x n)) x))
  add)

; Exercise 3.2
(define (make-monitored mf)
  (define count 0)
  (define (use input)
    (set! count (+ count 1))
    (mf input))
  (define (dispatch args)
    (cond ((equal? args 'how-many-calls?) count)
          (else (use args))))
  dispatch)

; Exercise 3.3 and 3.4
(define (make-account balance psw)
  (define error-count 0)
  (define (withdraw amount)
    (if (>= balance amount)
        (begin (set! balance (- balance amount))
               balance)
        "Insufficient funds"))
  (define (deposit amount)
    (set! balance (+ balance amount))
    balance)
  
  (define (call-the-cops)
    "The cops of Narnia have been summoned.")
  
  (define (incorrect val)
    (set! error-count (+ error-count 1))
    (if (>= error-count 7)
        (call-the-cops)
        "Incorrect password"))
  (define (dispatch p m)
    (if (equal? p psw)
        (begin (set! error-count 0)
               (cond ((eq? m 'withdraw) withdraw)
                     ((eq? m 'deposit) deposit)
                     (else peter(error "Unknown request -- MAKE-ACCOUNT"
                                  m))))
        incorrect))
  dispatch)

; Exercise 3.7
(define (make-joint acc acc-psw joint-psw)
         (define (withdraw amount)
           ((acc acc-psw 'withdraw) amount))
         (define (deposit amount)
           ((acc acc-psw 'deposit) amount))
         (define (call-the-cops)
           "The cops of Narnia have been summoned.")
         (define (incorrect val)
           ((acc (lambda (x) (sqrt (+ (random 2147483647) x))) 'withdraw) 0)) ; Making security great again
         (define (dispatch p m)
           (if (equal? p joint-psw)
               (begin
                 (cond ((eq? m 'withdraw) withdraw)
                       ((eq? m 'deposit) deposit)
                       (else (error "Unknown request -- MAKE-ACCOUNT"
                                    m))))
               incorrect))
         dispatch)

; Exercise 3.8
(define last-val 1)
(define (f n)
  (begin (set! last-val (* last-val n))
         last-val))


