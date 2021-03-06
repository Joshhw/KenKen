//import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
//@RunWith(Junit4.class)
public class CellJunitTest extends TestCase {
  
 private Cell test;
 
 @Before
 public void setUp() {
   this.test = new Cell(3);
 }
 
 @After
 public void tearDown() {
    this.test.setValue(0);
 }
 
 @Test
 public void testSetValue() {
   test.setValue(4);
   assertEquals(4, test.getValue());
 }
 
 @Test
 public void testGetValue() {
   assertEquals(3, test.getValue());
 
 }
 
 
}