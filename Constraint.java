/** Constraint Class: creates all the constraint arrays for the Kenken puzzle
  * @author Joshua Decosta
  * @version Sept 24th, 2014
  */

public class Constraint {
  private int value;
  private char operator;
  private Cell[] array;
  
  public Constraint(int value, char operator, Cell...array) {
    this.value = value;
    this.operator = operator;
    this.array = array;
  }
  /**
   * creates a boolean test to return if the tests all fail. this method checks all the constraint arrays in the Kenken
   * if its a certain operator then it performs the test if the test works it should return false
   * @author Joshua Decosta
   * @return true or false
   * 
   */
  public boolean isValid() {
    boolean test = false;
    
    if (this.operator == '=') {
      int numCheck = this.array[0].getValue();
      if (this.value == numCheck) {
        test = true;
        return test;
      }
    } else
    
    if (this.operator == '+') {
      int numCheck = 0;
      // for each loops require compatible data type
      for(int i = 0;i < this.array.length;i++)
        numCheck += this.array[i].getValue();
      if (this.value == numCheck) {
        return true;
      } else {
        return test;
      }
    } else
    // checks operator for minus symbol, then checks 2 numbers within array for match of value.
    if (this.operator == '-') {
      int numCheck = 0;
      if (this.value == ((this.array[0].getValue()) - (this.array[1].getValue()))) {
        test = true;
        return test;
      } else if (this.value == ((this.array[1].getValue()) - (this.array[0].getValue()))) {
        test = true;
        return test;
      } else {
        return test;
      }
    } else
    // checks operator for division symbol, then checks 2 numbers within array for match of value.
    if (this.operator == '/') {
      int numCheck = 0;
      if (this.value == ((this.array[0].getValue()) / (this.array[1].getValue()))) {
        test = true;
        return test;
      } else if (this.value == ((this.array[1].getValue()) / (this.array[0].getValue()))) {
        test = true;
        return test;
      } else {
        return test;
      }
    } else
    
    if (this.operator == '*') {
      int numCheck = 1;
      for(int i = 0;i < this.array.length;i++) {
        numCheck *= this.array[i].getValue();
      }
      if (this.value == numCheck) {
        test = true;
        return test;
      } else {
        return test;
      }
    } else {
    return test;
    }
   return test;
  }
}