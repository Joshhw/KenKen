import java.util.Scanner;
import java.util.Iterator;
import java.io.*;

/**
 * Kenken class implements a Kenken puzzle object
 * @version September 10, 2014
 * @author Bob Wilson
 */
public class Kenken implements Iterable<Cell []>
{
  private final int SIZE;
  private Cell [][] puzzle;
  private Constraint [] constraints;

  /** Puzzle File Format:
    *
    * ASCII text file with following data in each line:
    * 4  8 // size and number of constraints
    * // constraints
    * 4 + 2 0 0 0 1
    * 2 / 2 0 2 0 3
    * 1 - 2 1 0 1 1
    * 3 - 2 1 2 1 3
    * 5 + 2 2 0 3 0
    * 6 * 2 2 1 2 2
    * 2 / 2 3 1 3 2
    * 2 - 2 2 3 3 3
    * // solution
    * 3 1 4 2
    * 2 3 1 4
    * 4 2 3 1
    * 1 4 2 3
    *
    */

  public Kenken(Scanner file)
  {
    // read puzzle to be solved from a file Scanner
    Scanner line = new Scanner(file.nextLine());    // get size - first token on line 1
    SIZE = line.nextInt();
    puzzle = new Cell [SIZE][SIZE];                 // setup puzzle based on the SIZE
      for (int i = 0; i < puzzle.length; i++)
        for (int j = 0; j < puzzle.length; j++)
          puzzle[i][j] = new Cell();

    constraints = new Constraint[line.nextInt()];   // get number of constraints - second token on line 1
    file.nextLine();                                // get rid of comment line in file format

    int index = 0;
    for (int i = 0; i < constraints.length; i++)    // process each constraint in the file
    {
      String s = file.nextLine();
      Scanner constraint = new Scanner(s);          // one constraint per line
      int value = constraint.nextInt();                  // the value
      char op = constraint.next().charAt(0);             // the operator
      if (op == 's')                                     // space substitution char is 's'
        op = ' ';
      int cells = constraint.nextInt();                  // the number of Cells in the cage
      Cell [] cage = new Cell[cells];
      for (int j = 0; j < cells; j++)  {                 // the pairs of ints for the Cell indices in puzzle
        int a = constraint.nextInt();
        int b = constraint.nextInt();
        cage[j] = puzzle[a][b];
      }

      constraints[index++] = new Constraint(value, op, cage);  // create the constraint from input data
    }

    // fill in puzzle array with solution to be tested
    file.nextLine();                                // get rid of comment line in file format

    for (int i = 0; i < puzzle.length; i++) {
      for (int j = 0; j < puzzle.length; j++) {
        puzzle[i][j].setValue(file.nextInt());
      }
    }
  }

  public String toString()
  {
    String s = "";
    for (int i = 0; i < puzzle.length; i++)
      for (int j = 0; j < puzzle.length; j++)
        s += puzzle[i][j] + ((j != puzzle.length - 1) ? ", " : "\n");
    return s;
  }




/**
 * iterator method:creates an iterator and returns an Iterator Cell object
 * @author Joshua Decosta
 * @return Iterator object
 */
public Iterator<Cell[]> iterator() {
  KenkenIterator iterate = new KenkenIterator();
  return iterate;
}





/**
 * KenKenIterator Class:
 * private class used to iterate over the kenken puzzle data
 * @author Joshua Decosta
 */

private class KenkenIterator implements Iterator<Cell[]> {
  private int cursor;
  private int cursorConstraint;

  /**
   *
   */
  public KenkenIterator() {
    this.cursor = 0;
    this.cursorConstraint = 0;
  }
/**
 * checks to make sure the iterator has more data
 */
  public boolean hasNext() {
    return ((cursor <= (2 * puzzle.length)) && ((cursorConstraint < constraints.length)));
  }
  /**
   * takes data from the puzzle and puts it into a Cell[] array and returns that array.
   * if cursor is larger than 2 times puzzle length then it relies on the cursorConstraint
   * it then checks if the constraint location of the current cursorConstraint value is valid
   * if it is then it returns a valid Cell[] array of unique numbers otherwise it returns null.
   * @author Joshua Decosta
   * @return Cell[] array of valid data or null
   */
  public Cell[] next() {
    String print = "";
    Cell[] data = new Cell[puzzle.length]; //create Cell[]
    if (cursor < puzzle.length) {  //if true its just row data
      for (int i = 0; i < puzzle.length; i++) {
        data[i] = puzzle[cursor][i];  //input row data to data array
      }
      cursor++; //increase cursor count for next row
      return data;  //return row data
    } else if (cursor < 2 * puzzle.length) { //check if cursor is bigger than 5, which means its a column
       for (int i = 0; i < puzzle.length; i++) {
          data[i] = puzzle[i][cursor-puzzle.length];  //makes cursor the correct value since the puzzle length should make cursor back to a 1-n value for iterating columns
      }
      cursor++; // increase cursor count for next column
      return data; // return column
    } else if (cursor - 2 * puzzle.length < constraints.length) { //brings cursor value back to normal range of 1-n
     // for (int i = 0; i < constraints.length; i++) {
      if (constraints[cursorConstraint].isValid()) { //checks if the constraints Cell[] is valid
        
        Cell a = new Cell(1);  //creates 3 cells of unique numbers
        Cell b = new Cell(2);
        Cell c = new Cell(3);
        Cell d = new Cell(4);
        Cell[] array = new Cell[4]; // creates Cell[]
          array[0]= a;  //inputs said numbers
          array[1]=b;
          array[2]=c;
          array[3]=d;
        data=array;  //returns those numbers to make a valid constraint
        } else {
          data=null; //returns null to say isValid failed
        }
      cursorConstraint++;
      return data;
    }
    return data;
  }
  /**
   * does nothing but creates a marker so the ADT can work.
   * @author Joshua Decosta
   *
   */
    public void remove() throws UnsupportedOperationException {
     throw new UnsupportedOperationException();
    }
  }
}
