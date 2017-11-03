package cs3500.animator.controller;

import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.view.ITextView;

/**
 * Represents the controller for the textual view and implements IAnimationController and its
 * associated operations.
 */
public class TextController implements IAnimationController {

  private IAnimationModel model;
  private ITextView view;
  private String filename;

  /**
   * Constructs a {@code TextController} object.
   *
   * @param model    the model that the controller will be using
   * @param view     the view that the controller will be using to display
   * @param filename the output file for the controller to write out to
   */
  public TextController(IAnimationModel model, ITextView view, String filename) {
    this.model = model;
    this.view = view;
    this.filename = filename;
  }

  @Override
  public void start() {
    List<Animations> animations = model.getAnimations();
    List<Shapes> shapes = model.getShapes();

    for (int i = 0; i < animations.size(); i++) {
      Animations a = animations.get(i);
      Shapes aShape = a.getShape();
      String aName = aShape.getName();

      for (int j = 0; j < shapes.size(); j++) {
        Shapes current = shapes.get(j);
        if (aName.equals(current.getName())) {
          /*System.out.println("Should get here " + current.getName() + " x " + current.getPosn().getX()
          + " y " + current.getPosn().getY());
          System.out.println("Origin " + a.getBeginState());
          System.out.println("Destination before " + a.getEndState());*/
          a.changeField(current);
/*          System.out.println("After " + current.getName() + " x " + current.getPosn().getX()
                  + " y " + current.getPosn().getY());
          System.out.println("Destination after " + a.getEndState());*/
        }
      }
    }
    this.view.writeOut(filename);
  }
}
