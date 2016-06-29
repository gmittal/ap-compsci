/*
  Probably a good idea to get a refresher of Java ecosystem
  by attempting to write the AP Computer Science Summer Homework
  programs again: http://paleyontology.com/AP_CS/entrance.html

  To learn about the oddities of Java class inheritance, apply to Stanford:
  http://paleyontology.com/AP_CS/Comparator.html
*/
import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        // Problem 1 (Easy)
        System.out.println(sumSquares(-100, 24));

        // Problem 2 (Medium)
        ArrayList<Double> theList = new ArrayList<Double>();
        theList.add(10.5);
        theList.add(2.0);
        theList.add(3.2);
        theList.add(4.8);
        System.out.println(sumList(theList));

        // Problem 3 (Medium)
        int[][] arr = new int[40][40];
        initArray(arr); // initialize the array
        System.out.println(arr[36][5]);   // prints 15 (3 * 5)
        System.out.println(arr[17][4]);  // prints 68 (17 * 4)


    }

    public static int sumSquares(int first, int second) {
      int sum = 0;
      for (int i = first; i <= second; i++) {
        sum += i*i;
      }
      return sum;
    }

    public static Double sumList(ArrayList<Double> list) {
      Double sum = 0.0;
      for (int i = 0; i < list.size(); i++) {
        sum += list.get(i);
      }
      return sum;
    }

    public static void initArray(int[][] arr) {
      for (int i = 0; i < arr.length; i++) {
        int[] subarr = arr[i];
        for (int j = 0; j < subarr.length; j++) {
          arr[i][j] = i*j;
        }
      }
    }

}
