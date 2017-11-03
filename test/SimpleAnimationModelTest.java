import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs3500.hw05.animation.Animations;
import cs3500.hw05.animation.ChangeColor;
import cs3500.hw05.animation.ChangeDimension;
import cs3500.hw05.animation.MoveAnimation;
import cs3500.hw05.AnimationOperations;
import cs3500.hw05.shape.Oval;
import cs3500.hw05.shape.Posn;
import cs3500.hw05.shape.RectangleShape;
import cs3500.hw05.shape.ShapeType;
import cs3500.hw05.shape.Shapes;
import cs3500.hw05.SimpleAnimationModel;

import static junit.framework.TestCase.assertEquals;

public class SimpleAnimationModelTest {
  AnimationOperations aop;
  Posn p1;
  Posn p2;
  Shapes oval1;
  Shapes oval2;
  Shapes rect1;
  Shapes rect2;

  Animations changeEclipseColor;
  Animations changeRectColor;
  Animations changeEclipseDimension;
  Animations changeRectDimension;
  Animations moveEclipse;
  Animations moveRect;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    this.aop = new SimpleAnimationModel();

    p1 = new Posn(0.0, 0.0);
    p2 = new Posn(102.112, 50.5);
    oval1 = new Oval("oval 1", 0, 10, p1, Color.BLACK, 10.0, 10.0);
    oval2 = new Oval("oval 2", 10, 15, p2, Color.BLUE, 20.0, 25.5);
    rect1 = new RectangleShape("rectangle 1", 0, 10,
            p1, Color.BLACK, 10.0, 10.0);
    rect2 = new RectangleShape("rectangle 2", 10, 15,
            p2, Color.BLUE, 20.0, 25.5);

    changeEclipseColor = new ChangeColor(this.oval1, 5, 10, Color.RED);
    changeRectColor = new ChangeColor(this.rect1, 5, 10, Color.PINK);
    changeEclipseDimension =
            new ChangeDimension(this.oval2, 11, 12, 15.5, 15.5);
    changeRectDimension = new ChangeDimension(this.rect2, 11, 12, 15.5, 15.5);
    moveEclipse = new MoveAnimation(this.oval1, 8, 10, this.p2);
    moveRect = new MoveAnimation(this.rect1, 8, 10, this.p2);
  }

  // Test for adding shape to model
  @Test
  public void addShape() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addShape(ShapeType.RECTANGLE, "rectangle 1", 0, 10, 0.0,
            0.0, Color.BLACK, 10.0, 10.0);
    assertEquals("Shapes:\n"
            + "Name: rectangle 1\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n\n", this.aop.getDescription());
    this.aop.addShape(ShapeType.OVAL, "oval 2", 10, 15, 102.112,
            50.5, Color.BLUE, 20.0, 25.5);
    assertEquals("Shapes:\n"
            + "Name: rectangle 1\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n\n"
            + "Name: oval 2\n"
            + "Type: oval\n"
            + "Center: (102.112, 50.5), X radius: 20.0, Y radius: 25.5, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=15\n\n", this.aop.getDescription());
  }

  // Test for adding animation to model
  @Test
  public void addAnimations() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addAnimations(this.changeEclipseColor);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to "
                    + "(1.0,0.0,0.0) from t=5 to t=10\n",
            this.aop.getDescription());

    this.aop.addAnimations(this.changeEclipseDimension);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
    this.aop.addAnimations(this.moveEclipse);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
  }

  // Test for adding animation to model
  @Test
  public void addAnimationsInDifferentOrder() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addAnimations(this.changeEclipseDimension);
    assertEquals("Shapes:\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());

    this.aop.addAnimations(this.changeEclipseColor);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
    this.aop.addAnimations(this.moveEclipse);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
  }

  // Test for adding in animation for Incompatible move for the same shape during overlapping
  // time intervals
  @Test(expected = IllegalArgumentException.class)
  public void addInvalidAnimation() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addAnimations(this.changeEclipseColor);
    this.aop.addAnimations(this.changeEclipseColor);
  }

  // Test for getting description
  @Test
  public void getDescription() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addShape(ShapeType.RECTANGLE, "rectangle 1", 0, 10, 0.0,
            0.0, Color.BLACK, 10.0, 10.0);
    this.aop.addShape(ShapeType.OVAL, "oval 2", 10, 15, 102.112,
            50.5, Color.BLUE, 20.0, 25.5);
    this.aop.addAnimations(this.changeEclipseColor);
    this.aop.addAnimations(this.changeEclipseDimension);
    this.aop.addAnimations(this.moveEclipse);
    assertEquals("Shapes:\n"
                    + "Name: rectangle 1\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, "
                    + "Color: (0.0,0.0,0.0)\n"
                    + "Appears at t=0\n"
                    + "Disappears at t=10\n" + "\n"
                    + "Name: oval 2\n"
                    + "Type: oval\n"
                    + "Center: (102.112, 50.5), X radius: 20.0, Y radius: 25.5, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=10\n"
                    + "Disappears at t=15\n"
                    + "\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
  }

}