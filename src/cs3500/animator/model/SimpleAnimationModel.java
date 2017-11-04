package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * This is a class for a simple animation model which represents a model that processes an
 * animation.  It implements the IAnimationModel and its operations.
 */

public class SimpleAnimationModel implements IAnimationModel {

  private List<Shapes> shapes;
  private List<Animations> animations;

  /**
   * Constructs a {@code SimpleAnimationModel} object.
   */
  public SimpleAnimationModel() {
    this.shapes = new ArrayList<Shapes>();
    this.animations = new ArrayList<Animations>();
  }

  @Override
  public void addShape(Shapes s) {
    this.shapes.add(s);
  }

  @Override
  public void addAnimations(Animations a) {
    AnimationType addType = a.getType();
    Shapes addShape = a.getShape();
    int addStart = a.getStart();
    int size = animations.size();

    for (int i = 0; i < animations.size(); i++) {
      Animations current = animations.get(i);
      AnimationType type = current.getType();
      Shapes shape = current.getShape();
      int start = current.getStart();
      int end = current.getEnd();

      if (addType == type) {
        if (addShape.getName().equals(shape.getName())) {
          if ((addStart >= start) && (addStart <= end)) {
            throw new IllegalArgumentException("Incompatible move for the same shape "
                    + "during overlapping time intervals");
          }
        }
      }
    }

    // Add animation in order of start time
    for (int i = 0; i < size; i++) {
      Animations current = animations.get(i);
      int start = current.getStart();

      if (addStart < start) {
        animations.add(i, a);
      }
    }
    if (size == animations.size()) {
      animations.add(a);
    }
  }

  @Override
  public String getDescription() {
    String state = "Shapes:\n";
    for (int i = 0; i < shapes.size(); i++) {
      state += shapes.get(i).getState() + "\n";
    }

    for (int i = 0; i < animations.size(); i++) {
      state += animations.get(i).getDescription() + "\n";
    }
    return state;
  }

  @Override
  public List<Shapes> getShapes() {
    return shapes;
  }

  @Override
  public List<Animations> getAnimations() {
    return animations;
  }
}
