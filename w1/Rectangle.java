// Task 1
// Write a file header with your name and class (file) purpose
//

/**
 * Tin Nguyen
 *
 * Rectangle.java: Contains the template for a Rectangle object, with
 * associated getters and setters.
 */

// Define a rectangle class -- make sure to use capitals for the class name
public class Rectangle {
  // define but don't initialize a width field of type double
  // make sure it has the correct access modifier (private)
  private double width;
  // define but don't initialize a height field of type double
  // make sure it has the correct access modifier
  private double height;

  // Make a constructor that takes in values for the width and height
  // Make sure both of your class fields are initialized by the end of
  // the constructor!
  // Write a JavaDoc comment for your constructor
  /**
   * Constructs a Rectangle with specified width and height.
   *
   * @param width Width of rectangle
   * @param height Height of rectangle
   */
  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  // Make a second constructor that takes in just the width
  // and calls the first constructor with the width and the
  // height equal (i.e. it makes a square)
  // Write a JavaDoc comment for your constructor
  /**
   * Constructs a Square with specified width and height.
   *
   * @param width Side length of rectangle
   */
  public Rectangle(double width) { this(width, width); }

  // Write a getter method for the width
  // Write a JavaDoc comment for your method
  /**
   *
   * @return The width of the rectangle
   */
  public double getWidth() { return this.width; }

  // Write a getter method for the height
  // Write a JavaDoc comment for your method
  /**
   *
   * @return The height of the rectangle
   */
  public double getHeight() { return this.height; }

  // Write a setter method for the width
  // Write a JavaDoc comment for your method
  /**
   * Set the width of the rectangle
   *
   * @param x The new width of the rectangle
   */
  public void setWidth(double x) { this.width = x; }

  // Write a setter method for the height
  // Write a JavaDoc comment for your method
  /**
   * Set the height of the rectangle
   *
   * @param x The new height of the rectangle
   */
  public void setHeight(double x) { this.height = x; }

  // Write a method that called computeDiagonal that
  // computes the length of the diagonal and returns it as a double
  // Your method should use the Math.sqrt and the Math.pow methods
  // Write a JavaDoc comment for your method
  // Write a single line comment for at least one
  // line in your method
  /**
   * Compute the length of the diagonal of the rectangle
   *
   * @return The length of the diagonal
   */
  public double computeDiagonal() {
    double diagonal = Math.sqrt(Math.pow(width, 2) +
                                Math.pow(height, 2)); // This is a comment
    return diagonal;
  }

  // Write a toString method that has the following format:
  //"Height: [[height]], Width: [[width]], Diagonal: [[length of diagonal]]"
  //  eg: "Height: 3.0, Width: 4.0, Diagonal: 5.0"
  public String toString() {
    double diagonal = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    String results =
        "Height: " + height + ", Width: " + width + ", Diagonal: " + diagonal;
    return results;
  }

  // Once you have completed both the Rectangle class and this Tester class,
  // You should be able to run this file, Tester.java and get the following
  // Output to your terminal.  When evaluating your code, we will run our own
  // version of this tester file and confirm that this is the output we get.
  /***
  4.0
  5.0
  2.2
  2.2
  Height: 2.2, Width: 2.2, Diagonal: 3.111269837220809
  Height: 1.0, Width: 1.0, Diagonal: 1.4142135623730951
  Height: 2.0, Width: 2.0, Diagonal: 2.8284271247461903
  Height: 3.0, Width: 3.0, Diagonal: 4.242640687119285
  Height: 4.0, Width: 4.0, Diagonal: 5.656854249492381
  Height: 1.0, Width: 1.0, Diagonal: 1.4142135623730951
  Height: 2.0, Width: 2.0, Diagonal: 2.8284271247461903
  Height: 3.0, Width: 3.0, Diagonal: 4.242640687119285
  Height: 5.0, Width: 5.0, Diagonal: 7.0710678118654755
  */
}
