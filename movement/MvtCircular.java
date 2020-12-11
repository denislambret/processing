package movement;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class MvtCircular extends Mover {

	PApplet p;
	public PVector location;
	public float alpha, beta;

	protected PVector center;
	protected float radius;
	protected float stepAlpha, stepBeta;
	protected boolean pause;

	// Constructor
	public MvtCircular(PApplet p_, PVector location, float radius) {

		p = p_;

		this.location = location;
		this.center = this.location;
		this.location.x = center.x + radius;

		// Set a default circular movement with a radius of 100
		this.setAngle(0.0f);
		this.setStepAngle(0.01f);
		this.setRadius(radius);
		start();
	}

	// getters & setters
	public float getAngle() {
		return alpha;
	}

	protected float getAlpha() {
		return alpha;
	}

	protected float getBeta() {
		return beta;
	}

	public PVector getLocation() {
		return location;
	}

	public PVector getCenter() {
		return center;
	}

	public void setLocation(PVector location) {
		this.location = location;
	}

	public void setCenter(PVector center) {
		this.center = center;
	}

	public void setRadius(float r_) {
		this.radius = r_;
	}

	public void setAngle(float alpha_) {
		alpha = alpha_;
	}

	public void setStepAngle(float stepAlpha_) {
		stepAlpha = stepAlpha_;
	}

	public void genRandomMovement() {
		this.setCenter(new PVector(p.random(p.width), p.random(p.height)));
		this.setLocation(new PVector(p.random(p.width), p.random(p.height)));
		this.setAngle(p.random((float) -Math.PI, (float) Math.PI));
		this.setStepAngle(p.random(0.05f,0.00005f));
		this.setRadius(p.random(64.0f, 200.0f));
	}

	public void move() {
		if (!pause) {
			alpha = (alpha + stepAlpha) % PConstants.TWO_PI;
			location.x = center.x + radius * (float) Math.cos(alpha);
			location.y = center.y + radius * (float) Math.sin(alpha);
		}
	}

	public void start() {
		pause = false;
	}

	public void stop() {
		pause = true;
	}

}
