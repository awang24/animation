package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

/**
 * A class to represent a SVG view that extends the Textual view.
 */
public class SVGView extends TextualView {

  /**
   * Constructs a {@code TextController} object.
   * @param tempo the tempo that the view is converted to
   * @param model the model used by the view
   */
  public SVGView(double tempo, IAnimationModel model) {
    super(tempo, model);
  }

  @Override
  public String getDescription() {
    List<Shapes> shapes = this.getModel().getShapes();
    List<Animations> animations = this.getModel().getAnimations();
    double tempo = this.getTempo();

    String state = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n";

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      state += currentShape.toSVGTag();
      for (int j = 0; j < animations.size(); j++) {
        Animations currentA = animations.get(j);
        Shapes currentS = currentA.getShape();
        if (currentShape.getName().equals(currentS.getName())) {
          state += currentA.toSVGTag(this.getTempo());
        }
      }
      state += currentShape.svgEndTag() + "\n";
    }
    state += "</svg>";

    return state;
  }

}
