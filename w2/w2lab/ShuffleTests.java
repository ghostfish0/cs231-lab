/*
file name:      ShuffleTests.java
Authors:        Ike Lage & Max Bender & Allen Harper
last modified:  02/26/2024

How to run:     java -ea ShuffleTests
*/

import java.util.ArrayList;

public class ShuffleTests {

  public static void shuffleTestsLab() {
    // case 1: testing that the arrays before and after shuffle are not equal
    {
      // set up
      // Make an ArrayList of integers from 0 to 9
      ArrayList<Integer> arr0 = new ArrayList<Integer>();
      for (int i = 0; i < 10; i++)
        arr0.add(i);

      // Shuffle it and save the output as a new ArrayList
      ArrayList<Integer> arr1 = DumbShuffle.dumbShuffle(arr0);
      ArrayList<Integer> arr2 = DumbShuffle.noShuffle(arr0);

      // verify
      // Print the original and the shuffled ArrayList s
      System.out.println(arr0);
      System.out.println(arr1);
      System.out.println(arr2);

      // test
      // Assert that the original and new ArrayLists aren't equal
      assert !arr0.equals(arr1);
      assert arr0.equals(arr2);
    }

    // Print that your tests have all passed!
    System.out.println("test passed! 🎉");
  }

  public static void main(String[] args) { shuffleTestsLab(); }
}
