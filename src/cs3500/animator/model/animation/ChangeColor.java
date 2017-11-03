package cs3500.animator.model.animation;

import java.awt.Color;

import cs3500.animator.model.Utils;
import cs3500.animator.model.shape.Shapes;

/**
 * Represents the change color animation.
 */
public class ChangeColor extends AAnimations {
  private Color origin;
  private Color dest;

  /**
   * Constructs a {@code ChangeColor} object.
   *
   * @param shape The shape that the move animation will be implemented on
   * @param start The start time of the animation
   * @param end   The end time of the animation
   * @param origin The original color
   * @param dest  The color to change to
   */
  public ChangeColor(Shapes shape, int start, int end, Color origin, Color dest) {
    super(AnimationType.CHANGECOLOR, shape, start, end);
    this.origin = origin;//shape.getColor();
    this.dest = dest;
    //shape.setColor(dest);
  }

  @Override
  public void animate(double currentTime) {
    float currentRed = Utils.getColorFloat(this.origin.getRed());
    float currentGreen = Utils.getColorFloat(this.origin.getGreen());
    float currentBlue = Utils.getColorFloat(this.origin.getBlue());

    float destRed = Utils.getColorFloat(this.dest.getRed());
    float destGreen = Utils.getColorFloat(this.dest.getGreen());
    float destBlue = Utils.getColorFloat(this.dest.getBlue());

    float changeRed = destRed - currentRed;
    float changeGreen = destGreen - currentGreen;
    float changeBlue = destBlue - currentBlue;

    float changeInTime = (float) (currentTime - this.getStart())
            / (float) (this.getEnd() - this.getStart());

    if ((currentTime > this.getEnd()) || (currentTime < this.getStart())) {
      // won't do anything
    } else {
      float newRed = currentRed + (changeRed * changeInTime);
      float newGreen = currentGreen + (changeGreen * changeInTime);
      float newBlue = currentBlue + (changeBlue * changeInTime);

      Color newColor = new Color(newRed, newGreen, newBlue);
      this.getShape().setColor(newColor);
    }
  }

  @Override
  public String getMovement() {
    return "changes color";
  }

  @Override
  public String getBeginState() {
    return Utils.getColorString(this.origin);
  }

  @Override
  public String getEndState() {
    return Utils.getColorString(this.dest);
  }

  @Override
  public void changeField(Shapes s) {
    s.setColor(dest);
  }
}
