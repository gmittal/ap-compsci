(define (make-bintree datum left right)
  (lambda (msg)
    (cond ((= msg 0) datum)
          ((= msg 1) left)
          ((= msg 2) right))))

(define (datum bintree) (bintree 0))
(define (left bintree) (bintree 1))
(define (right bintree) (bintree 2))

(define the-empty-tree 'cow)
(define tet the-empty-tree)
(define (empty-tree? tree) 
  (eq? tree tet))
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


; List to Tree Problems
(define (first-n lyst n)
  (define (helper new lyst n c)
    (cond ((< c n) (helper (append new (list (car lyst))) (cdr lyst) n (+ c 1)))
          (else new)))
  (helper '() lyst n 0))

(define (part1 lyst)
  (define size (floor (/ (length lyst) 2)))
  (first-n lyst size))

(define (rest lyst)
  (define size (floor (/ (length lyst) 2)))
  (define (helper new c)
    (cond ((< c size)
           (helper (cdr new) (+ c 1)))
           (else new)))
  (helper lyst 0))

(define (middle-datum lyst)
  (car (rest lyst)))

(define (part2 lyst)
  (cdr (rest lyst)))

(define (list->tree lyst)
  (cond ((null? lyst) tet)
        (else (make-bintree (middle-datum lyst) (list->tree (part1 lyst)) (list->tree (part2 lyst)))))) 
