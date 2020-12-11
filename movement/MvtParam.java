package movement;

import processing.core.PApplet;
import processing.core.*;

public class MvtParam extends Mover {

	PApplet p;
	boolean fPause = false;
	public float t;
	public float r;
	public float q;
	float scale;
	float step;
	float alpha;
	public PVector location;
	boolean pause;

	// Constructor
	public MvtParam(PApplet p_, PVector location) {
		// Retrieve parent reference for processing function calls
		p = p_;

		this.t = 0.0f;
		this.location = location;
		this.alpha = 0.0f;
		this.scale = 100.0f;
		this.step = 0.01f;
		this.q = 0.5f;
		this.r = 0.333f;
		this.pause = false;
	}

	public void setStep(float s_) {
		step = s_;
	}

	public void setTime(float t)
	{
		this.t = t;
	}
	
	public void setScale(float s_) {
		scale = s_;
	}

	public void setCoeficients(float q_, float r_) {
		r = r_;
		q = q_;
	}

	public void setLocation(PVector location) {
		this.location = location;
	}
	
	public PVector getLocation() {
		return location;
	}

	public float getAngle() {
		return alpha;
	}

	public PVector getCenter() {
		return location;
	}

	public void genRandomMovement()
	{
		location = new PVector(p.random(0, p.width), p.random(0, p.height));
		t = p.random(0,10000.0f);
		q = p.random(0.1f,1.0f);
		r = p.random(0.1f,1.0f);
		scale = p.random(50.0f,150.0f);
	}
	
	public void move() {
		if (!pause) {
			t = t + step;
			location.x = (float) Math.sin(t / q) * scale;
			location.y = (float) Math.sin(t / r) * scale;
		}
	}

	public void start() {
		pause = false;
	}

	public void stop() {
		pause = true;
	}

}
