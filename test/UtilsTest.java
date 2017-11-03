import org.junit.Test;

import java.awt.Color;

import cs3500.hw05.shape.Posn;
import cs3500.hw05.Utils;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
  Posn p1 = new Posn(0.0, 0.0);
  Posn p2 = new Posn(102.112, 50.5);

  // Test for checking if 2 doubles are the same
  @Test
  public void checkDoubles() {
    assertEquals(true, Utils.checkDoubles(0.0, 0.0));
    assertEquals(true, Utils.checkDoubles(20.0, 20.0));
    assertEquals(false, Utils.checkDoubles(0.5, 0.0));
    assertEquals(false, Utils.checkDoubles(1.0, 20.0));
  }

  // Test for checking to see if a double is negative
  @Test
  public void isNegative() {
    assertEquals(true, Utils.isNegative(-5.0));
    assertEquals(false, Utils.isNegative(5.0));
    assertEquals(false, Utils.isNegative(0.0));
  }

  // Test for getting the float representation of a double
  @Test
  public void getColorFloat() {
    assertEquals(true, Utils.checkDoubles(Utils.getColorFloat(255), 1.0));
    assertEquals(true, Utils.checkDoubles(Utils.getColorFloat(150), 0.5882352941));
    assertEquals(false, Utils.checkDoubles(Utils.getColorFloat(150), 1.0));
  }

  // Test for getting the string representation of the color
  @Test
  public void getColorString() {
    assertEquals("(0.0,0.0,1.0)", Utils.getColorString(Color.BLUE));
    assertEquals("(0.0,1.0,1.0)", Utils.getColorString(Color.CYAN));
    assertEquals("(1.0,0.0,0.0)", Utils.getColorString(Color.RED));
    assertEquals("(0.2509804,0.2509804,0.2509804)", Utils.getColorString(Color.DARK_GRAY));
  }

  // Test for getting the string representation of the posn
  @Test
  public void getPosnString() {
    assertEquals("(0.0, 0.0)", Utils.getPosnString(this.p1));
    assertEquals("(102.112, 50.5)", Utils.getPosnString(this.p2));
  }

}