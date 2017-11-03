package cs3500.animator.controller;

import java.awt.Color;
import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.animation.MoveAnimation;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.starter.TweenModelBuilder;

/**
 * Represents a simple animation builder that will add shapes and animations to the model.
 */
public class SimpleAnimationModelBuilder implements TweenModelBuilder<IAnimationModel> {

  private IAnimationModel model;

  /**
   * Constructs a {@code SimpleAnimationBuilder} object.
   **/
  public SimpleAnimationModelBuilder() {
    this.model = new SimpleAnimationModel();
  }

  @Override
  public TweenModelBuilder<IAnimationModel> addOval(
          String name,
          float cx, float cy,
          float xRadius, float yRadius,
          float red, float green, float blue,
          int startOfLife, int endOfLife) {
    System.out.println("supposed to be x " + cx + " y " + cy);
    Posn p = new Posn(cx, cy);
    Color c = new Color(red, green, blue);
    Shapes shape = new Oval(name, startOfLife, endOfLife, p, c, xRadius, yRadius);
    model.addShape(shape);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimationModel> addRectangle(
          String name,
          float lx, float ly,
          float width, float height,
          float red, float green, float blue,
          int startOfLife, int endOfLife) {
    System.out.println("supposed to be x " + lx + " y " + ly);
    Posn p = new Posn(lx, ly);
    Color c = new Color(red, green, blue);
    Shapes shape = new RectangleShape(name, startOfLife, endOfLife, p, c, width, height);
    model.addShape(shape);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimationModel> addMove(
          String name,
          float moveFromX, float moveFromY, float moveToX, float moveToY,
          int startTime, int endTime) {
    Posn origin = new Posn(moveFromX, moveFromY);
    Posn dest = new Posn(moveToX, moveToY);
    Shapes s = null;
    List<Shapes> loshapes = model.getShapes();
    for (int i = 0; i < loshapes.size(); i++) {
      Shapes current = loshapes.get(i);
      if (current.getName().equals(name)) {
        s = current;
        //System.out.println(name + " before add x " + s.getPosn().getX() + " y " + s.getPosn().getY());
      }
    }
    try {
      //s.setPosn(origin);
      //System.out.println(name + " set posn before create x " + s.getPosn().getX() + " y " + s.getPosn().getY());
      Animations animation = new MoveAnimation(s, startTime, endTime, origin, dest);
      //System.out.println(name + " set posn after create x " + s.getPosn().getX() + " y " + s.getPosn().getY());
      model.addAnimations(animation);
    } catch (Exception e) {
      // do nothing
    }
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimationModel> addColorChange(
          String name,
          float oldR, float oldG, float oldB, float newR, float newG, float newB,
          int startTime, int endTime) {
    Color oldColor = new Color(oldR, oldG, oldB);
    Color newColor = new Color(newR, newG, newB);

    Shapes s = null;
    List<Shapes> loshapes = model.getShapes();
    for (int i = 0; i < loshapes.size(); i++) {
      Shapes current = loshapes.get(i);
      if (current.getName().equals(name)) {
        s = current;
      }
    }
    try {
      //s.setColor(oldColor);
      Animations animation = new ChangeColor(s, startTime, endTime, oldColor, newColor);
      model.addAnimations(animation);
    } catch (Exception e) {
      // do nothing
    }
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx, float
          fromSy, float toSx, float toSy, int startTime, int endTime) {

    Shapes s = null;
    List<Shapes> loshapes = model.getShapes();
    for (int i = 0; i < loshapes.size(); i++) {
      Shapes current = loshapes.get(i);
      if (current.getName().equals(name)) {
        s = current;
      }
    }
    try {
      //s.setD1(fromSx);
      //s.setD2(fromSy);
      Animations animation = new ChangeDimension(s, startTime, endTime, fromSx, fromSy, toSx, toSy);
      model.addAnimations(animation);
    } catch (Exception e) {
      // do nothing
    }
    return this;
  }

  @Override
  public IAnimationModel build() {
   // System.out.println("rect x " + model.getShapes().get(0).getPosn().getX() + " y " + model.getShapes().get(0).getPosn().getY());
    return model;
  }
}
