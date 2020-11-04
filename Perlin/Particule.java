package Perlin;

import processing.core.*;

public class Particule {
    PApplet p;
    PVector pos;
    PVector vel;
    PVector acc;

    Particule(PApplet p) {
        this.p = p;
        this.pos = new PVector(p.random(p.width), p.random(p.height));
        this.vel = new PVector(p.random(-3,3),p.random(-3,3));
        this.acc = new PVector(0,0);
    }
    
    void applyForce(PVector f) {
        this.acc.add(f);
    }

    void update() {
        edgesControl();
        this.vel = this.vel.add(this.acc);
        this.pos = this.pos.add(this.vel);
    }

    void edgesControl() {
        if (this.pos.x < 0 ) {this.pos.x = p.width;}
        if (this.pos.x > p.width) {this.pos.x = 0;}
        if (this.pos.y < 0 ) {this.pos.y = p.height;}
        if (this.pos.y > p.height) {this.pos.y = 0;}
    }

    void show() {
       p.stroke(0,255,0);
       p.fill(0,255,0,50);
       p.strokeWeight(4);
       p.point(this.pos.x, this.pos.y);
       p.strokeWeight(0);
       p.ellipse(this.pos.x, this.pos.y, 20, 20);
    }
}
