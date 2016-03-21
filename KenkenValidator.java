import java.util.*;
import java.io.*;

/** 
 * This class instantiates a Kenken object passing a Scanner on a
 * file to the Kenken constructor.  It prints the puzzle using the
 * Kenken toString method.  It determines if the digit matrix is a
 * valid solution for a Kenken puzzle or not and prints the result.
 * 
 * @version September 10, 2014
 * @author Bob Wilson or Joshua Decosta (at points)
 * 
 */

public class KenkenValidator
{
  private Kenken puzzle;
  
  /**
   * @param args - not used
   */
  public static void main( String [] args)
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter name of file containing puzzle to verify");   
    KenkenValidator myValidator = new KenkenValidator(scan.nextLine());
    System.out.println(myValidator.isSolution() ? 
                "It is a valid solution" : "It is not a valid solution");
  }
    
  public KenkenValidator(String fileName)
  {
    Scanner file = null;
    try
    {
      file = new Scanner(new File(fileName));
    }
    catch (Exception e)
    {
      System.out.println("Bad file name");
      System.exit(0);
    }
    
    puzzle = new Kenken(file);
    System.out.println(puzzle);
  }
  /**creates a boolean value called arrayCheck to return true if the tests implemented don't return false
    * it then creates an iterator object to use to iterate over the puzzle data if all the tests fail
    * then it will return boolean true.
    * @return true or false
    * @author Joshua Decosta
   * 
   * 
   */
  public boolean isSolution() {
    boolean arrayCheck = true;
    Iterator<Cell[]> itr = puzzle.iterator();
    Cell[] array = null; //needed to do the string concatenation outside of the while loop
    String print = "";   //for printing the array values
    while(itr.hasNext()) {
      array = itr.next();
      if (array == null) {
        return false;
      }
      for (int i = 0; i < array.length; i++) {  //makes a nested for loop to check all values against each other
        for (int j = i+1; j < array.length; j++) {
         // if (array[i] != null && array[j] != null) {  //checks for a null value
            if (array[i].getValue() == array[j].getValue()) {  //checks for same value
              for(int k = 0;k < array.length; k++) {
                print += array[k].getValue() + " ";
              }
                System.out.println("array " + print + "is the problem");  //prints value if found same (not correct, still checking this)
              return false;  //returns false since test failed
            }
          }
        }
      }
  return arrayCheck;
  }
}