//Define a Tester class 
public class Tester {
	//Define a static testRectangle method 
  
  public static void testRectangle() {
    //Define and initialize 1 rectangle with width 3 and height 7 

    //Set the height to be 4 

    //Get the height and confirm that it is 4
    //by printing it

    //Call the computeDiagonal method and print the output
    //Confirm that it is 5
    Rectangle myRectangle = new Rectangle(3, 7);
    myRectangle.setHeight(4);
    double myRectangleHeight = myRectangle.getHeight();
    System.out.println(myRectangleHeight);
    double myRectangleDiag = myRectangle.computeDiagonal();
    System.out.println(myRectangleDiag);

    //Initialize 1 square using the second constructor 
    //with width and height of 2.2
    //Get the height and width of the square
    //Confirm they are both 2.2 by printing them
    
    Rectangle mySquare = new Rectangle(2.2);
    System.out.println(mySquare.getWidth());
    System.out.println(mySquare.getHeight());

    //Print the string representation of the square.  Don't call 
    //the toString() method directly, instead use the following 
    //line (if your object is named square).  Confirm it calls your toString method
    //System.out.println( square );
    //It should print: Height: 2.2, Width: 2.2, Diagonal: 3.111269837220809
    System.out.println(mySquare);

  }


  //define a static main method 
  public static void main(String args[]) {
    //Call the testRectangle method to run your tests

    //Define an array of Rectangle objects of size 4

    //Use an indexed for loop to generate 4 squares of 

    //size 1x1, 2x2, 3x3 and 4x4 and put them in the array
    testRectangle();
    Rectangle[] ArrRect = new Rectangle[4];
    for(int i = 0; i < 4; i++) {
      ArrRect[i] = new Rectangle(i + 1);
    }

    //Use another indexed for loop to print each square in order
    for(int i = 0; i < 4; i++) 
      System.out.println(ArrRect[i]);

    //Change the last rectangle in the list to be of size 5x5 instead of 4x4
    ArrRect[3].setWidth(5);
    ArrRect[3].setHeight(5);

    //Use another indexed for loop to print each square in order
    for(int i = 0; i < 4; i++) 
      System.out.println(ArrRect[i]);
  }
}
		
