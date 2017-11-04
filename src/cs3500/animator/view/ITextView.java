package cs3500.animator.view;

import cs3500.animator.model.IAnimationModel;

/**
 * The text view interface.
 */
public interface ITextView extends IView {

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

  /**
   * Returns the model that the view is using.
   *
   * @return the model that the view is using
   */
  IAnimationModel getModel();

  /**
   * Returns the tempo of the view.
   *
   * @return the tempo of the view
   */
  double getTempo();
}
