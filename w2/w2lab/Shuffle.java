/**
* Author: Tin Nguyen
* 
* Purpose of the class
*/	

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {
  public static void main(String[] args) {
    ArrayList<Integer> arr0 = new ArrayList<Integer>();
    Random generator = new Random();
    for(int i = 0; i < 10; i++) {
      arr0.add(generator.nextInt(100));
    }
    System.out.println("Gettin arr0: ");
    for(int rNum : arr0) {
      System.out.print(rNum + " ");
    }
    System.out.println("");

    System.out.println("Gettin arr1: ");
    ArrayList<Integer> arr1 = new ArrayList<Integer>(arr0);
    for(int rNum : arr1) {
      System.out.print(rNum + " ");
    }
    System.out.println("");

    ArrayList<Integer> arr2 = arr0;

    System.out.println("Gettin all three: ");
    System.out.println(arr0); 
    System.out.println(arr1); 
    System.out.println(arr2); 
    System.out.println("arr0 == arr1: " + (arr0 == arr1) + "\narr1 == arr2: " + (arr1 == arr2) + "\narr2 == arr0: " + (arr2 == arr0));

  }
}
