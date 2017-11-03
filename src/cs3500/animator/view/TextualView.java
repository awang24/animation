package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.Utils;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;

public class TextualView implements ITextView {

  private IAnimationModel model;
  private double tempo;
  //private List<Shapes> shapes;
  //private List<Animations> animations;

  /**
   * Constructs a {@code TextualView} object.
   *
   * @param tempo represents the speed at which the animation occurs
   * @param model model that view will use
   */

  public TextualView(double tempo, IAnimationModel model) {
    super();
    this.tempo = tempo;
    this.model = model;
    //shapes = model.getShapes();
    //animations = model.getAnimations();
    //this.model = reader.readFile()
  }

  @Override
  public String getDescription() {
    List<Shapes> shapes = model.getShapes();
    List<Animations> animations = model.getAnimations();

    //System.out.println("size of shapes: " + shapes.size() + ", size of animations: " + animations.size());

    String state = "Shapes:\n";
    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      double newAppearTime = (double) currentShape.getAppear() / this.tempo;
      double newDisappearTime = (double) currentShape.getDisappear() / this.tempo;
      String current = "";
      current += "Name: " + currentShape.getName() + "\n" + "Type: "
              + currentShape.getShapeType().toString() + "\n"
              + currentShape.location() + ", "
              + currentShape.getDimensions() + ", Color: "
              + Utils.getColorString(currentShape.getColor()) + ""
              + "Appears at t=" + newAppearTime + "s\n" + "Disappears at t="
              + newDisappearTime + "s\n";
      state += current + "\n";
    }

    for (int i = 0; i < animations.size(); i++) {
      Animations currentAnimation = animations.get(i);
      double newAppearTime = (double) currentAnimation.getStart() / this.tempo;
      double newDisappearTime = (double) currentAnimation.getEnd() / this.tempo;

      String current = "";
      current += "shape " + currentAnimation.getShape().getName() + " "
              + currentAnimation.getMovement() + " from "
              + currentAnimation.getBeginState() + " to " + currentAnimation.getEndState()
              + " from t=" + newAppearTime
              + "s to t=" + newDisappearTime + "s";
      state += current + "\n";
    }
    return state;
  }

  @Override
  public void writeOut(String fileName) {
    String description = this.getDescription();
    try {
      BufferedWriter output = null;
      if (fileName.equals("System.out")) {
        output = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File file = new File(fileName);
        output = new BufferedWriter(new FileWriter(file));
      }
      output.write(description);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
