package cs3500.animator.model;

import java.awt.Color;

import cs3500.animator.model.shape.Posn;

/**
 * Class for static methods that are used for testing and used among different classes.
 */
public class Utils {

  /**
   * Checks to see if two doubles are the same.
   *
   * @param x1 First number to compare to
   * @param x2 Second number to compare to
   * @return true if doubles are the same, false if they are not
   */
  public static boolean checkDoubles(double x1, double x2) {
    return Math.abs(x1 - x2) <= 0.0000001;
  }

  /**
   * Checks to see if the number is negative.
   *
   * @param x Number to check if negative
   * @return true if number is negative, false if it isn't
   */
  public static boolean isNegative(double x) {
    return x < 0.0;
  }

  /**
   * Returns the float representation of a field of a color. Number ranges from 0.0 to 1.0.
   *
   * @return the float representation of a color field
   */

  public static float getColorFloat(int x) {
    return (float) x / (float) 255;
  }

  /**
   * Returns the string representation of a color in float representation.
   *
   * @param c the color
   * @return string representation of the color
   */
  public static String getColorString(Color c) {
    return "(" + Utils.getColorFloat(c.getRed()) + "," + Utils.getColorFloat(c.getGreen()) + ","
            + Utils.getColorFloat(c.getBlue()) + ")";
  }

  /**
   * Returns the string representation of a color.
   *
   * @param c the color
   * @return string representation of the color.
   */
  public static String getNotFloatColorString(Color c) {
    return "(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
  }

  /**
   * Returns the posn string representation.
   *
   * @param p the posn
   * @return the string representation of the posn
   */
  public static String getPosnString(Posn p) {
    return "(" + p.getX() + ", " + p.getY() + ")";
  }
}
