package cs3500.animator;

import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.Scanner;

import javax.swing.*;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.controller.SimpleAnimationModelBuilder;
import cs3500.animator.controller.TextController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.starter.AnimationFileReader;
import cs3500.animator.starter.TweenModelBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualAnimationView;

public class EasyAnimator {
  public static void main(String[] args) {
    System.out.println(String.join(" ", args));

    Readable r = new StringReader(String.join(" ", args));

    IAnimationModel model = null; //= new SimpleAnimationModel();
    Scanner scan = new Scanner(r);
    String filename = "";
    String viewType = "";
    String output = "";
    double speed = -1;

    IView view = null;
    IAnimationController controller = null;

    while (scan.hasNext()) {
      //System.out.println("has next");
      String in = scan.next();

      switch (in) {
        case "-if":
          if (filename.equals("") && scan.hasNext()) {
            filename = scan.next();
          }
          break;
        case "-iv":
          if (viewType.equals("") && scan.hasNext()) {
            viewType = scan.next();
          }
          break;
        case "-o":
          if (output.equals("") && scan.hasNext()) {
            output = scan.next();
          }
          break;
        case "-speed":
          if (speed == -1 && scan.hasNext()) {
            speed = Double.parseDouble(scan.next());
          }
          break;
        default:
          JFrame frame = new JFrame();
          frame.setSize(100, 100);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (speed == -1) {
      speed = 1;
    }
    if (output.equals("") || output.equals("out")) {
      output = "System.out";
    }

    AnimationFileReader fileReader = new AnimationFileReader();
    TweenModelBuilder<IAnimationModel> simpleBuilder = new SimpleAnimationModelBuilder();

  /*  if (model == null) {
      System.out.println("NULL");
    } else {
      System.out.println("NOT NULL");
    }*/

    try {
      model = fileReader.readFile(filename, simpleBuilder);
      //System.out.println("Rectangle x " + model.getShapes().get(0).getPosn().getX() + " y " + model.getShapes().get(0).getPosn().getY());
      //for (int i = 0; i < model.getShapes().size(); i++) {
        //System.out.println("file in " + model.getShapes().get(i).getShapeType().toString()
               // + " x " + model.getShapes().get(i).getPosn().getX() + " y " + model.getShapes().get(i).getPosn().getY());
     // }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame, "Invalid file", "Error", JOptionPane.ERROR_MESSAGE);
    }

    switch (viewType) {
      case "text":
        view = new TextualView(speed, model);
        break;
      case "visual":
        view = new VisualAnimationView(speed, model);
        break;
      case "svg":
        //view = new SVGView(speed, model);
        break;
      default:
        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Invalid view type", "Error", JOptionPane.ERROR_MESSAGE);
    }

    switch (viewType) {
      case "text":
        controller = new TextController(model, (TextualView) view, output);
        break;
      case "visual":
        controller = new VisualController(speed, model, (VisualAnimationView) view);
        break;
      case "svg":
        //view = new SVGView(speed, model);
        break;
      default:
        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Invalid view type", "Error", JOptionPane.ERROR_MESSAGE);
    }

    try {
      controller.start();
    } catch (Exception e) {
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame, "ERROR", "Error", JOptionPane.ERROR_MESSAGE);
    }

/*    Posn p1 = new Posn(200.0, 200.0);
    Posn p2 = new Posn(500.0, 100.0);
    Posn p3 = new Posn(300.0, 300.0);
    Posn p4 = new Posn(500.0, 500.0);
    Shapes oval = new Oval("O", 6, 100, p2, Color.BLUE, 60.0, 30.0);
    Shapes rect = new RectangleShape("R", 1, 100,
            p1, Color.RED, 50.0, 100.0);

    Animations moveR1 = new MoveAnimation(rect, 10, 50, p3);
    Animations moveC1 = new MoveAnimation(oval, 20, 70, p4);
    Animations changeColorC = new ChangeColor(oval, 50, 80, Color.GREEN);
    Animations scaleR = new ChangeDimension(rect, 51, 70, 50.0, 100.0);
    Animations moveR2 = new MoveAnimation(rect, 70, 100, p1);

    model.addShape(oval);
    model.addShape(rect);
    model.addAnimations(moveR1);
    model.addAnimations(moveC1);
    model.addAnimations(changeColorC);
    model.addAnimations(scaleR);
    model.addAnimations(moveR2);

    ITextView view = new TextualView(2, model);*/
  }
}
