; Problem X (Aaron Schultz and Gautam Mittal). make-grid takes a height and width and a following set of numbers to create a grid of those values.(define (make-grid width height . lyst)  (define (make-row width x y row lyst)  (cond ((< (length row) width)          (make-row width (+ x 1) y (append row (list (list-ref lyst (+ x (* y width))))) lyst))        (else row)))    (define (iter width height count grid lyst)    (cond ((< count height)           (iter width height (+ count 1) (append grid (list (make-row width 0 count '() lyst))) lyst))          (else grid)))  (iter width height 0 '() lyst)); Write get a function that takes a grid and x and y indices and returns that position in the grid.(define (get grid x y) ???); Write get-row that takes a grid and a row index and returns a list of the values in that row.(define (get-row grid n) ???); Write get-column that takes a grid and a column index and returns a list of the values in that column.(define (get-column grid n) ???); SOLUTIONS:; Takes an x and y index and returns that position in the grid given(define (get grid x y)  (list-ref (list-ref grid y) x)); Get a row of a grid(define (get-row grid n)  (list-ref grid n)); Get a column of a grid(define (get-column grid n)  (cond ((null? grid) '())        (else (cons (list-ref (car grid) n) (get-column (cdr grid) n)))))