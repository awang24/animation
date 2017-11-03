package cs3500.animator.view;

import java.awt.*;

import javax.swing.*;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.shape.Shapes;
import java.util.List;

/**
 * Represents the view for a visual animation.
 */

public class VisualAnimationView extends JFrame implements IVisualView {

  //private IAnimationModel model;
  //private JLabel display;
  //private double speed;
  private AnimationPanel animatePanel;
  private JScrollPane scrollPane;
  private List<Shapes> shapes;

  /**
   * Constructs a {@code VisualAnimationView} object.
   *
   * @param speed represents the speed at which the animation occurs
   */
  public VisualAnimationView(double speed, List<Shapes> shapes) {//IAnimationModel model) {
    super();

    this.shapes = shapes;
    this.setTitle("Simple Animation");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animatePanel = new AnimationPanel();
    animatePanel.setPreferredSize(new Dimension(700, 700));

    animatePanel.setShapes(shapes);

    scrollPane = new JScrollPane(animatePanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);

    //this.add(animatePanel, BorderLayout.CENTER);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }



  @Override
  public void setShapes(List<Shapes> shapes) {
    //this.shapes = shapes;
    animatePanel.setShapes(shapes);
  }

  /*@Override
  public void setAnimations(List<Animations> animations) {
    animatePanel.setAnimations(animations);
  }*/
}
