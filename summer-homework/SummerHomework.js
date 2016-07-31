// Sum of Squares method
function sumSquares(squareOne, squareTwo) {
  var squareArray = [];
  var squareCount = squareOne;
  
  for (var i = 0; i < squareTwo - squareOne + 1; i++) {
    squareArray[i] = squareCount;
    squareCount++;
  }
  
  var result = 0;
  
  for (var j = 0; j < squareArray.length; j++) {
    result += squareArray[j] * squareArray[j];
  }
  
  return result;
  
}



// Number of Objects in a List method
function sumList(listName) {
  var listSum = 0;
  for (var i = 0; i < listName.length; i++) {
    listSum += listName[i];
  }
  
  return listSum;
}

// 2D array problem solution
function initArray(array) {
  for (var i = 0; i < 1000; i++) {
    
    var subArr = [];
    
    for (var j = 0; j < 1000; j++) {
     
      subArr[j] = i * j;
    }
    
    array[i] = subArr;
    
  }
}




// Factorial Method
function factorial(n) {
  var factorialResult = 1;
  for (var i = 0; i < n; i++) {
    factorialResult *= n - i;
  }
  
  return factorialResult;
}

// Odd Number List Method
function odds(oddsList) {
  var oddListOutputList = [];
  for (var i = 0; i < oddsList.length; i++) {
    if ((oddsList[i] % 2) !== 0) {
      oddListOutputList.push(oddsList[i]);
    }
  }
  
  var numOdds = 0;
  
  for (var j = 0; j < oddListOutputList.length; j++) {
    numOdds++;
  }
  
  return numOdds;
}





// Reverse list method
function reverse(listToReverse) {
  var reversedList = [];
  for (var i = 0; i < listToReverse.length; i++) {
    reversedList.unshift(listToReverse[i]);
  }
  
  return reversedList;
}




// The code actually working

// example of sumSquares function
console.log(sumSquares(-2, 2)); 

// example of sumList function
var randomArray = [Math.PI, Math.sqrt((Math.PI*Math.PI*Math.PI)), 314, 27];
console.log(sumList(randomArray));

// 2d array
var arr = [];
initArray(arr);
console.log(arr[128][2]);

// example of factorial
console.log(factorial(5));

//example of odds
console.log(odds([5, 31, 7, 13, 100])); 

//example of reverse list
console.log(reverse([[0, 1], 1, 2, 3, ["A reversed list that wasn't reversed.", 3.14, "Pi"]]));

