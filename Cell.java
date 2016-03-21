/**
 * Cell class takes a number and stores it.
 * @author Joshua Decosta
 * @version Sept 23rd, 2014
 */

public class Cell {
  private int value = 0;
  
  public Cell() {
    this.value = 0;
  }
  
  public Cell(int value) {
    this.value = value;
  }
  
  public void setValue(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public String toString() {
    String value = "";
    return (Integer.toString(this.value));
  }
  
}
  
  
    