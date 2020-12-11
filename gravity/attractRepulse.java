package gravity;

import processing.core.*;
import java.util.*;

public class attractRepulse extends PApplet {
  List<PVector> attractors = new ArrayList<PVector>();
  List<Particule> particules = new ArrayList<Particule>();
  int maxParticules = 1000;
  int radius = 100;
  public static void main(String _args[]) {
    PApplet.main(attractRepulse.class.getName());
  }

  public void settings() {
    size(1200, 800);
    // or fullscreen
    // fullscreen();
  }

  public void setup() {
    for (int i = 0; i < maxParticules; i++) {
        particules.add(new Particule(this, random(width), random(height)));
     }
  }

  public void mousePressed() {
    attractors.add(new PVector(mouseX, mouseY));
  }

  public void draw() {
    //background(50);
    fill(50,50);
    stroke(50);
    rect(0,0,width,height);
    stroke(255);
    strokeWeight(4);
    particules.add(new Particule(this, random(width), random(height)));

    if (particules.size() > maxParticules) {
      particules.remove(0);
    }

    for (int i = 0; i < particules.size(); i++) {
      Particule particle = particules.get(i);
      for (int j = 0; j < attractors.size(); j++) {
        particle.attracted(attractors.get(j));
      }
      particle.update();
      particle.edges();
      particle.show();
    }

    for (int i = 0; i < attractors.size(); i++) {
      stroke(0, 255, 0);
      strokeWeight(1);
      point(attractors.get(i).x, attractors.get(i).y);
      fill(0, 255, 0, 5);
      ellipse(attractors.get(i).x, attractors.get(i).y,radius,radius);
    }
    
  }

}
