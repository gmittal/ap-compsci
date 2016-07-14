## AP Computer Science Entrance Exam

(Really, it's just summer homework.)

__Please note that I do not grade the summer homework. The problems are designed such that you are very likely to know if you have solved them--or at least have come close. A lot of students attempt the jump straight into the advanced CS class and I just don't have time to grade it all before the school year starts. Bottom line is that if you do not do the summer homework (or skip some of it), you will likely be very, very confused during lecture. The most common outcome for a student who does not do the summer homework is dropping the class out of frustration. Please do not let that be you!__

Solve all four problems with Java and all five problems in Scheme. We use both languages in the AP CS class, and it is important to have some mastery of both. The AP class will start with an assumption that you can do all of this.

You may seek the help of others in writing the code, but understand that you are responsible for being able to do the content in problem #4. The AP class will be very hard if you cannot handle multidimensional arrays, class construction, conditionals, loops, recursion, etc. NOTE: Programmers who have used other languages prior to Scheme may tend to try to use assignment statements (set! in Scheme, the = operator in Java) to solve problems. Your solutions to the Scheme problems must not use any assignment statements.)

If you have questions, please email me at [jpaley@pausd.org](mailto:jpaley@pausd.org). I generally check my email several times per week over the summer.

----

1. (Easy) Write the method ```sumSquares``` which takes two integers as inputs. The first integer is guaranteed to be less than the second integer. ```sumSquares``` does not print anything. It returns the sum of the squares of the integers between the two inputs. Here are sample usages.

  ```java
  System.out.println(sumSquares(1,5));   // prints 55 (1 + 4 + 9 + 16 + 25)
  System.out.println(sumSquares(-2,2));  // prints 10 (4 + 1 + 0 + 1 + 4)
  ```
2. (Medium) Write the method ```sumList``` which takes an ```ArrayList<Double>``` as its input. sumList should not print anything, but it should return the sum of the elements in the list. Example:
  ```java
  ArrayList<Double> theList = new ArrayList<Double>();
  theList.add(1.5);
  theList.add(2.0);
  theList.add(3.2);
  theList.add(4.8);
  System.out.println(sumList(theList));   // prints 11.5 (1.5 + 2.0 + 3.2 + 4.8)
  ```
3. (Medium) Write the method ```initArray``` which takes a two-dimensional array of ints as its input. ```initArray``` returns nothing; the result of the call to initArray is that all of the elements are set to the products of their indices. Example:
```java
  int[][] arr = new int[20][10];
  initArray(arr); // initialize the array
  System.out.println(arr[3][5]);   // prints 15 (3 * 5)
  System.out.println(arr[17][4]);  // prints 68 (17 * 4)
  ```
4. (What you need to show you can do before you take AP Computer Science) Write Conway's Game of Life in Java. (This is the key project in the Intro to Java class.)

__NOTE: If you want, you can write Conway's Game of Life in Processing (see http://processing.org) instead of Java.__



5. (Easy) Write the function ```factorial``` in Scheme that takes an integer, n, as its input and returns n!. (n! is defined as n * (n-1) * (n-2) * ... * 1. 0! is defined to be 1 as well.)
```scheme
  (factorial 5)     // returns 120 (5 * 4 * 3 * 2 * 1)
  (factorial 0)     // returns 1
  ```
6. (Medium) Write the function ```odds``` which takes a list of integers as its lone input and returns the number of odd numbers in the list. (The predicate odd? will be helpful.)
```scheme
  (odds '(5 12 31 7 98 13)      // returns 4 [5, 31, 7, 13]
  (odds '())                    // returns 0
  ```
7. (Medium) Write the function ```filter``` that takes a predicate and a list as its inputs and returns a list containing those elements that were satisfied by the predicate.
```scheme
  (filter odd? '(5 12 31 7 98 13)                // returns (5 31 7 13)
  (filter (lambda (x) (> x 10)) '(3 47 18 2 20)) // returns (47 18 20)
  (filter list? '(() 2 ((6) 4)))                 // returns (() ((6) 4))
  ```
8. (Medium Hard) Write the function ```reverse``` that takes a list as its input and returns a list with the order of those elements reversed. Note that any sublists are not reversed.
```scheme
  (reverse '(5 12 31 7 98 13)   // returns (13 98 7 31 12 5)
  (reverse '((1 2) 3)           // returns (3 (1 2))
  (reverse '(() 2 ((6) 4)))     // returns (((6) 4) 2 ())
  (reverse '())                 // returns ()
  ```
9. (Medium) Write the procedure ```safe-divider```, which takes a numeric input that will be used as a numerator in a division expression. ```safe-divider``` returns a __procedure__ that takes another numeric inputer that will be used as the denominator. When that procedure is called, if the denominator is zero, the word "cow" is returned (without the quotes). Otherwise, the numerator divided by the denomator is returned.
```scheme
  (define divide-by-12 (safe-divider 12))
  (divide-by-12 4)    // returns 3
  (divide-by-12 -2)   // returns -6
  (divide-by-12 0)    // returns 'cow
  (define divide-by-20 (safe-divider 20))
  (divide-by-20 0)    // returns 'cow
  (divide-by-20 4)    // returns 5
  ```
Note that ```divide-by-12``` is a procedure. ```safe-divider``` must return a lambda.
