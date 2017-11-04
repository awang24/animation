package cs3500.animator.model.shape;

import java.awt.Color;

/**
 * A visitor class that implements the shape visitor and the appropriate methods to create a copy of
 * the shape taken in.
 */
public class CreateShapeVisitor implements IShapeVisitor {

  @Override
  public Shapes visit(Oval shape) {
    Posn p = new Posn(shape.getPosn().getX(), shape.getPosn().getY());
    Color shapeColor = shape.getColor();
    Color c = new Color(shapeColor.getRed(), shapeColor.getGreen(), shapeColor.getBlue());
    return new Oval(shape.getName(), shape.getAppear(), shape.getDisappear(), p, c, shape.getD1(),
            shape.getD2());
  }

  @Override
  public Shapes visit(RectangleShape shape) {
    Posn p = new Posn(shape.getPosn().getX(), shape.getPosn().getY());
    Color shapeColor = shape.getColor();
    Color c = new Color(shapeColor.getRed(), shapeColor.getGreen(), shapeColor.getBlue());
    return new RectangleShape(shape.getName(), shape.getAppear(), shape.getDisappear(), p, c,
            shape.getD1(), shape.getD2());
  }
}
