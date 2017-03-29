/*
  * AP Computer Science Sorting Algorithms
  * Written by Gautam Mittal
  * 2/19/2017
*/

// Takes array, index, another index, and swaps the two positions
function swap(a, f, n) {
  var tmp = a[f];
  a[f] = a[n];
  a[n] = tmp;
}

/*
  * BUBBLE SORT
  * https://en.wikipedia.org/wiki/Bubble_sort
*/
function bubble(a) {
  var swaps = 0;
  var comparisons = 0;

  for (var x = 0; x < a.length; x++) {
    for (var i = 0; i < a.length; i++) { // bubble once
      comparisons++;
      if (a[i] > a[i+1]) { // NOTE: Out of bounds error in any non-JS language
        swap(a, i, i+1);
        swaps++;
      }
    }
  }

  console.log("BUBBLE; swaps: " + swaps + ", comparisons: " + comparisons);
}

/*
  * SELECTION SORT
  * https://en.wikipedia.org/wiki/Selection_sort
*/
function selection(a) {
  // Keep track of the number of swaps and comparisons the algorithm makes as a standard
  var swaps = 0;
  var comparisons = 0;

  var c = 0;
  for (var x = 0; x < a.length; x++) {
    var smallest = c;
    for (var i = c; i < a.length; i++) { // find the smallest
      comparisons++;
      if (a[i] < a[smallest]) {
        smallest = i;
      }
    }
    swap(a, c, smallest);
    swaps++;
    c++;
  }

  console.log("SELECTION; swaps: " + swaps + ", comparisons: " + comparisons);
}

/*
  * INSERTION SORT
  * https://en.wikipedia.org/wiki/Insertion_sort
*/
function insertion(a) {
  var swaps = 0;
  var comparisons = 0;

  for (var x = 0; x < a.length; x++) {
    for (var i = 0; i < x; i++) {
      comparisons++;
      if (a[x] < a[i]) {
        swap(a, x, i);
        swaps++;
      }
    }
  }

  console.log("INSERTION; swaps: " + swaps + ", comparisons: " + comparisons);
}


// Create a random array
var array = [];
for (var i = 0; i < Math.floor(Math.random() * 100) + 1; i++) {
  array.push(Math.floor(Math.random() * 99) - 10);
}

console.log("INITIAL: " + JSON.stringify(array) + ", length: " + array.length); // pre-sort

insertion(array);
console.log("SORTED: " + JSON.stringify(array)); // post-sort
