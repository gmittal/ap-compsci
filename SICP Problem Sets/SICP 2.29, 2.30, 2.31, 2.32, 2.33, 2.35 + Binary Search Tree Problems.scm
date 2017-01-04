; SICP 2.29, 2.30, 2.31, 2.32, 2.33, 2.35
; Aaron Schultz and Gautam Mittal

; Exercise 2.29
(define (make-mobile left right)
  (list left right))

(define (make-branch length structure)
  (list length structure))

(define (left-branch mobile)
  (car mobile))

(define (right-branch mobile)
  (cadr mobile))

(define (branch-length branch)
  (car branch))

(define (branch-structure branch)
  (cadr branch))

(define (mobile? mobile)
  
  (if (list? mobile)
      (list? (car mobile))
      #f))  

(define (total-weight mobile)
  (cond ((mobile? mobile)
      (+ (total-weight (left-branch mobile)) (total-weight (right-branch mobile))))
      (else 
       (if (mobile? (branch-structure mobile))
           (total-weight (branch-structure mobile))
           (branch-structure mobile)))))

(define (balanced? mobile)
  (if (mobile? mobile)
      (cond ((equal? (* (total-weight (left-branch mobile)) (branch-length (left-branch mobile)))
                  (* (total-weight (right-branch mobile)) (branch-length (right-branch mobile))))
            (and (balanced? (branch-structure (left-branch mobile)))
            (balanced? (branch-structure (right-branch mobile))))
             )
            (else #f))
      #t))

; Answer to 2.29d: You simply need to change all occurrences of cadr to cdr.


; Exercise 2.30 and 2.31 (we abstracted tree-map to start with unknowingly)
(define (tree-map proc lyst)
  (cond ((null? lyst) lyst)
        ((list? (car lyst))
         (cons (tree-map proc (car lyst)) (tree-map proc (cdr lyst))))
        (else 
         (cons (proc (car lyst)) (tree-map proc (cdr lyst))))))

(define (square-tree tree)
  (tree-map (lambda (x) (* x x)) tree))


; Exercise 2.32 (this was difficult)
(define (subsets s)
  (if (null? s)
      (list '())
      (let ((rest (subsets (cdr s))))
        (append rest (map (lambda (x) (cons (car s) x)) rest)))))

; Exercise 2.33
(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (map p sequence)
  (accumulate (lambda (x y) (cons (p x) y)) '() sequence))
(define (append seq1 seq2)
  (accumulate cons seq2 seq1))
(define (length sequence)
  (accumulate (lambda (x y) (+ 1 y)) 0 sequence))

; Exercise 2.35
(define (count-leaves t)
  (accumulate (lambda (x y) (+ x y)) 0 (map (lambda (x) (if (list? x) (count-leaves x) 1)) t)))









; Binary Search Tree Practice
; Gautam Mittal & Aaron Schultz
; October 10, 2016

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
           
           
; This is all for inorder (Problem #1)
(define (inorder bintree)
  (bubble-sort (get-nodes bintree)))

(define (get-nodes bintree)
  (cond ((eqv? bintree 'toasterhead) '())
        (else 
         (cons (datum bintree) (append (get-nodes (left bintree)) (get-nodes (right bintree)))))))

(define (bubble-once lyst) ; We appreciate this code from the last midterm
  (cond ((null? lyst) lyst)
        ((= (length lyst) 1) lyst)
        ((<= (car lyst) (cadr lyst)) (cons (car lyst) (bubble-once (cdr lyst))))
        (else (cons (cadr lyst) (bubble-once (cons (car lyst) (cddr lyst)))))))

(define (ordered? lyst)
  (cond ((null? (cdr lyst)) #t)
        ((<= (car lyst) (cadr lyst)) (ordered? (cdr lyst)))
        (else #f)))

(define (bubble-sort lyst)
  (if (ordered? lyst) 
      lyst
      (bubble-sort (bubble-once lyst))))

; Count nodes (Problem #2)
(define (count-nodes bst)
  (length (get-nodes bst)))

; square-tree (Problem #3)
(define (tree-list bintree)
  (cond ((eqv? bintree 'toasterhead) '())
        (else 
         (cons (datum bintree) (list (tree-list (left bintree)) (tree-list (right bintree)))))))

(define (list-tree lyst)
  (if (null? lyst) 
      tet
      (make-bintree (car lyst) (list-tree (cadr lyst)) (list-tree (caddr lyst)))))

(define (tree-map proc lyst) ; From SICP 2.31
  (cond ((null? lyst) lyst)
        ((list? (car lyst))
         (cons (tree-map proc (car lyst)) (tree-map proc (cdr lyst))))
        (else
         (cons (proc (car lyst)) (tree-map proc (cdr lyst))))))

(define (bintree-map proc bintree)
  (list-tree (tree-map proc (tree-list bintree))))

(define (square-tree bintree)
  (bintree-map (lambda (x) (* x x)) bintree))

