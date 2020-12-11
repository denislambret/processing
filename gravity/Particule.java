package gravity;
import processing.core.*;

class Particule {
    PApplet p;
    PVector pos;
    PVector prev;
    PVector vel;
    PVector acc;
    int r = 10;
    float   G = 50.0f;
  
    Particule(PApplet _p, float x, float y) {
      p = _p;
      pos = new PVector(x, y);
      prev = new PVector(x, y);
      vel = new PVector(); 
      vel = PVector.random2D();
      vel.setMag(p.random(2, 5));
      acc = new PVector();
    }
  
    void update() {
      vel.add(acc);
      vel.limit(5);
      pos.add(vel);
      acc.mult(0);
    }
  
    void edges() {
        if ( pos.x < 0 ) { pos.x = prev.x = p.width; };
        if ( pos.x > p.width ) { pos.x = prev.x = 0; };
        if ( pos.y < 0 ) { pos.y = prev.y = p.height; };
        if ( pos.y > p.height ) { pos.y = prev.y = 0; };
    }
    
    void show() {
      p.stroke(255, 150);
      p.strokeWeight(1);
      p.line(this.pos.x, this.pos.y, this.prev.x, this.prev.y);
  
      prev.x = pos.x;
      prev.y = pos.y;
    }
  
    void attracted(PVector target) {
      // PVector dir = target - pos
      PVector force = PVector.sub(target, pos);
      float d = force.mag();
      d = PApplet.constrain(d, 1, 25);
      float strength = G / (d * d);
      force.setMag(strength);
       if (d < r) {
         force.mult(-10);
       }
        acc.add(force);
    }
  };