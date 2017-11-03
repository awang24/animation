package cs3500.animator.controller;

import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.view.IVisualView;

/**
 * Represents the controller for the visual view and implements IAnimationController and its
 * associated operations.
 */
public class VisualController implements IAnimationController {

  private IAnimationModel model;
  private IVisualView view;
  private double tempo;
  private boolean isAnimationStarted;

  /**
   * Constructs a {@code TextController} object.
   *
   * @param tempo the tempo that the animation will be animated
   * @param model the model that the controller will be using
   * @param view  the view that the controller will be using to display
   */
  public VisualController(double tempo, IAnimationModel model, IVisualView view) {
    this.model = model;
    this.view = view;
    this.tempo = tempo;
    this.isAnimationStarted = false;
  }

  @Override
  public void start() {
    this.view.refresh();
    this.isAnimationStarted = true;
    long startTime = System.currentTimeMillis();
    long timeElapsed = 0;

    double secondsElapsed = 0;
    double unitsElapsed = 0;
    List<Animations> animations = model.getAnimations();

    while (this.isAnimationStarted) {
      timeElapsed = System.currentTimeMillis() - startTime;
      secondsElapsed = timeElapsed / 1000.0;
      unitsElapsed = secondsElapsed * tempo;
      for (int i = 0; i < animations.size(); i++) {
        Animations current = animations.get(i);
        int start = current.getStart();
        int end = current.getEnd();

        if (start <= unitsElapsed && end >= unitsElapsed) {
          current.animate(unitsElapsed);
          this.view.refresh();
        }
      }
      this.view.makeVisible();
    }
  }
}
