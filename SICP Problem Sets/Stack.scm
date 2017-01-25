(define (make-stack)
  (let ((stack '()))
    (define (push element)
         (set! stack (cons element stack))      
      )
         
    (define (pop)
      (if (null? stack)
          "stack is empty"
          (let ((element (car stack)))
            (set! stack (cdr stack))
            element
            )
      ))
    
    (define (dispatch . msg)
      (cond ((null? msg) stack)
            ((equal? (car msg) 'push) push)
            ((equal? (car msg) 'pop) pop)
      ))
      
   dispatch 
  ))

(define s1 (make-stack))