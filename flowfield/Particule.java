package flowfield;

import processing.core.PApplet;
import processing.core.PVector;

public class Particule {

	PApplet p;
	PVector pos, vel, acc;
	PVector prevPos;
	int cols;
	float scl;
	float maxspeed;

	// Constructor
	public Particule(PApplet p_) {
		// Retrieve parent reference for processing function calls
		p = p_;
		this.pos = new PVector(p.random(p.width), p.random(p.height));
		this.vel = new PVector(0, 0);
		this.acc = new PVector(0, 0);
		this.prevPos = this.pos.copy();
		this.cols = (int) Math.floor(p.width/scl);
	}

	void update() {
		this.vel.add(this.acc);
		this.vel.limit(this.maxspeed);
		this.pos.add(this.vel);
		this.acc.mult(0);
	}

	void follow(PVector[] vectors) {
		float x = (float) Math.floor(this.pos.x / scl);
		float y = (float) Math.floor(this.pos.y / scl);
		int index = (int) (x + y * cols);
		PVector force = vectors[index];
		this.applyForce(force);
	}

	void applyForce(PVector force) {
		this.acc.add(force);
	}

	void show() {
		p.stroke(0, 5);
		p.strokeWeight(1);
		p.line(this.pos.x, this.pos.y, this.prevPos.x, this.prevPos.y);
		this.updatePrev();
	}

	void updatePrev() {
		this.prevPos.x = this.pos.x;
		this.prevPos.y = this.pos.y;
	}

	void edges() {
		if (this.pos.x > p.width) {
			this.pos.x = 0;
			this.updatePrev();
		}
		if (this.pos.x < 0) {
			this.pos.x = p.width;
			this.updatePrev();
		}
		if (this.pos.y > p.height) {
			this.pos.y = 0;
			this.updatePrev();
		}
		if (this.pos.y < 0) {
			this.pos.y = p.height;
			this.updatePrev();
		}
	}

}
