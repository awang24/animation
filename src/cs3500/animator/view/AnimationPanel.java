package cs3500.animator.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Shapes;

public class AnimationPanel extends JPanel {

  private List<Shapes> shapes;
  private List<Animations> animations;

  public AnimationPanel() {
    super();
    shapes = new ArrayList<Shapes>();
    animations = new ArrayList<Animations>();
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    // Changes the origin to bottom left
    AffineTransform originalTransform = g2d.getTransform();

    //g2d.translate(0, -this.getHeight());
    //g2d.translate(0, this.getPreferredSize().getHeight());
    //g2d.scale(1, -1);

    for (Shapes s : shapes) {
      Posn p = s.getPosn();
      int x = (int) p.getX();
      int y = (int) p.getY();
      int d1 = (int) s.getD1();
      int d2 = (int) s.getD2();
      Color c = s.getColor();
      if (s.getShapeType().equals(ShapeType.OVAL)) {
        g2d.setColor(c);
        g2d.fillOval(x, y, d1, d2);
        g2d.drawOval(x, y, d1, d2);
        //System.out.println("oval x " + x + " y " + y);
      } else if (s.getShapeType().equals(ShapeType.RECTANGLE)) {
        g2d.setColor(c);
        g2d.fillRect(x, y, d1, d2);
        g2d.drawRect(x, y, d1, d2);
        //System.out.println("rectangle x " + x + " y " + y);
      }
    }

    g2d.setTransform(originalTransform);
  }

  public void setShapes(List<Shapes> shapes) {
    this.shapes = shapes;
  }

  public void setAnimations(List<Animations> animations) {
    this.animations = animations;
  }
}
