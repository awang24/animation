package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.shape.Shapes;

/**
 * The visual view interface.
 */
public interface IVisualView extends IView {

  /**
   * Makes the view visible.
   **/
  void makeVisible();

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error String error message
   **/
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   **/
  void refresh();

  /**
   * Sets the list of shapes to see.
   *
   * @param shapes List of shapes to add
   **/
  void setShapes(List<Shapes> shapes);

}
