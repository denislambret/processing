package movement;

import processing.core.PVector;

public abstract class Mover {
  public PVector location;
  public float alpha;
  
  public abstract void start();
  public abstract void move();
  public abstract void stop();
  public abstract PVector getLocation();
  public abstract float getAngle();
  public abstract PVector getCenter();
}
