import processing.core.*;

public class Particule {
    PApplet p;
    PVector pos;
    PVector vel;
    PVector acc;
    int cols, rows;
    int step;
    int width;
    int height;
    float fMag;

    Particule(PApplet _p, int _step) {
        this.p = _p;
        step = _step;
        fMag = 5.0f;
        pos = new PVector(p.random(0.0f, p.width), p.random(0, p.height));
        acc = new PVector(p.random(0.0f, 1.0f), p.random(0.0f, 1.0f));
        vel = new PVector(0.0f, 0.0f);
        width = p.width;
        height = p.height;
        cols = (int) Math.floor(this.p.width / step);
        rows = (int) Math.floor(height / step);
    }

    void update() {
        this.vel.add(acc);
        this.vel.limit(5);
        this.pos.add(vel);
        this.acc.mult(0);
    }

    void applyForce(PVector f) {
        f.setMag(fMag);
        this.acc.add(f);
    }

    void follow(PVector[] flowField) {
        int xp = (int) Math.floor(this.pos.x / step);
        int yp = (int) Math.floor(this.pos.y / step);
        int index = xp + yp * cols;
        if (index < rows * cols) this.applyForce(flowField[index]);

    }

    void edge() {
        if ((this.pos.x < 0) || (this.pos.x > width) || (this.pos.y < 0) || (this.pos.y > height)) {
            this.pos.x = PApplet.round(p.random(0, width));
            this.pos.y = PApplet.round(p.random(0, height));
        }
            
    }

    void show() {
        p.stroke(255, 10);
        p.fill(255, 10);
        p.ellipse(pos.x, pos.y, 20, 20);

        p.stroke(200, 100);
        p.strokeWeight(3);
        p.point(this.pos.x, this.pos.y);
    }
}
