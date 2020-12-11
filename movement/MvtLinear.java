package movement;

import processing.core.PApplet;
import processing.core.PVector;

public class MvtLinear extends Mover {

	PApplet p;
	public PVector location;
	public float alpha;
	PVector trans;
	boolean pause;

	// Constructor
	public MvtLinear(PApplet p_, PVector location_) {
		// Retrieve parent reference for processing function calls
		p = p_;
		this.location = location_;
		this.trans = new PVector(0.0f, 0.0f);
		alpha = 0.0f;
	}

	public void genRandomMovement() {
		trans.x = p.random(-10, 10);
		trans.y = p.random(-10, 10);
	}

	public void setTrans(PVector trans) {
		this.trans.x = trans.x;
		this.trans.y = trans.y;
	}

	public PVector getLocation() {
		return location;
	}

	public PVector getCenter() {
		return location;
	}

	public float getAngle() {
		return alpha;
	}

	public void move() {
		if (!pause) {
			if ((location.x <= 0) || (location.x >= p.width)) {
				trans.x = -trans.x;
			}

			if ((location.y <= 0) || (location.y >= p.height)) {
				trans.y = -trans.y;
			}

			location.x = location.x + trans.x;
			location.y = location.y + trans.y;
		}
	}

	public void start() {
		pause = false;
	}

	public void stop() {
		pause = true;
	}

}
