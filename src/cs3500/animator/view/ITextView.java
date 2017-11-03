package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * The text view interface.
 */
public interface ITextView extends IView{

  /**
   * Gets the string representation/text view of the animation.
   *
   * @return string representation of the animations and shapes
   */
  String getDescription();

  /**
   * Writes the string description out to a given text file name
   */
  void writeOut(String fileName);

}
