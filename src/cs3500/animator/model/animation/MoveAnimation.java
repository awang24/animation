package cs3500.animator.model.animation;

import cs3500.animator.model.Utils;
import cs3500.animator.model.shape.CreateShapeVisitor;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.Shapes;


/**
 * Represents the move animation.
 */
public class MoveAnimation extends AAnimations {

  private Posn origin;
  private Posn dest;

  /**
   * Constructs a {@code MoveAnimation} object.
   *
   * @param shape The shape that the move animation will be implemented on
   * @param start The start time of the animation
   * @param end   The end time of the animation
   * @param dest  The destination location of the shape
   */
  public MoveAnimation(Shapes shape, int start, int end, Posn origin, Posn dest) {
    super(AnimationType.MOVE, shape, start, end);
    this.origin = origin;//shape.getPosn();
    this.dest = dest;
    //shape.setPosn(dest);
  }

  @Override
  public void animate(double currentTime) {
    double currentX = this.origin.getX();
    double currentY = this.origin.getY();

    double destX = this.dest.getX();
    double destY = this.dest.getY();

    double changeX = destX - currentX;
    double changeY = destY - currentY;

    double changeInTime = (currentTime - this.getStart())
            / (double) (this.getEnd() - this.getStart());

    //Shapes shape = this.getShape();
    //Shapes newShape = shape.accept(new CreateShapeVisitor());

    if ((currentTime > this.getEnd()) || (currentTime < this.getStart())) {
      // doesn't do anything
    } else {
      double newX = currentX + (changeX * changeInTime);
      double newY = currentY + (changeY * changeInTime);

      Posn newPosn = new Posn(newX, newY);

      //newShape.setPosn(newPosn);
      this.getShape().setPosn(newPosn);
    }
    //return newShape;
  }

  @Override
  public String getMovement() {
    return "moves";
  }

  @Override
  public String getBeginState() {
    return Utils.getPosnString(this.origin);
  }

  @Override
  public String getEndState() {
    return Utils.getPosnString(this.dest);
  }

  @Override
  public void changeField(Shapes s) {
    s.setPosn(this.dest);
  }
}
