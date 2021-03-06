import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class ConstraintJunitTest extends TestCase {

  public void testIsValid() {
  Cell[] testValues = new Cell[3];
  Cell a = new Cell(4);
  Cell b = new Cell(2);
  Cell c = new Cell(3);
  int ab = 4;
  char cb = '=';
  testValues[0]=a;
  testValues[1]=b;
  testValues[2]=c;
  Constraint test = new Constraint(ab, cb, a,b,c);
  
   assertEquals(true, test.isValid());
  }
  
}
    