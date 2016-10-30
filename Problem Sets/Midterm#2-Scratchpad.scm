(define (deepmap proc lyst)
  (cond ((null? lyst) '())
        ((not (pair? (car lyst))) (cons (proc (car lyst)) (deepmap proc (cdr lyst))))
        (else (cons (deepmap proc (car lyst)) (deepmap proc (cdr lyst))))))

(define (deep-reverse lyst)
  (cond ((null? lyst) '())
        ((not (pair? (car lyst))) (append (deep-reverse (cdr lyst)) (list (car lyst))))
        (else (append (deep-reverse (cdr lyst)) (list (deep-reverse (car lyst)))))))


(define (make-bintree datum left right)
  (lambda (msg)
    (cond ((= msg 0) datum)
          ((= msg 1) left)
          ((= msg 2) right))))

(define (datum bintree) (bintree 0))
(define (left bintree) (bintree 1))
(define (right bintree) (bintree 2))

(define tet 'toasterhead)
(define (empty-tree? tree) 
  (eq? tree 'toasterhead))
(define (leaf? bintree)
  (and (not (empty-tree? bintree))
       (empty-tree? (left bintree))
       (empty-tree? (right bintree))))


(define bst
  (make-bintree 15
     (make-bintree 6
        (make-bintree 2 tet tet)
        tet)
     (make-bintree 22
        (make-bintree 17
           (make-bintree 16 tet tet)
           (make-bintree 19 tet tet))
        (make-bintree 24 tet tet))))
           

(define (contains? bst n)
  (cond ((equal? bst tet) #f)
        ((= n (datum bst)) #t)
        ((> n (datum bst)) (contains? (right bst) n))
        (else (contains? (left bst) n))))

(define (inorder bst)
  (cond ((equal? bst tet) '())
        (else (append (inorder (left bst)) (list (datum bst)) (inorder (right bst))))))

(define (count-nodes bst)
  (length (inorder bst)))

(define (square-tree bst)
  (define (square x) (* x x))
  (cond ((equal? bst tet) tet)
        (else (make-bintree (square (datum bst)) (square-tree (left bst)) (square-tree (right bst))))))


(define (pathway n bst)
  (cond ((equal? bst tet) '(#f))
        ((= n (datum bst)) '(done))
        ((< n (datum bst)) (cons 'left (pathway n (left bst))))
        (else (cons 'right (pathway n (right bst))))))
