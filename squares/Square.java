package squares;

import processing.core.PApplet;
import processing.core.PVector;
import movement.*;

import processing.core.*;

public class Square {


	PApplet p;
	PVector location;
	float side;
	float alpha, stepAlpha;

	int r, g, b;
	int blend;
	Mover mvt;

	// Constructor
	public Square(PApplet p, float side) {
		// Retrieve parent reference for processing function calls
		this.p = p;
		this.side = side;
		this.alpha = 0.0f;
		this.stepAlpha = 0.01f;
		this.location = new PVector(0.0f, 0.0f);
	}

	public void setMover(Mover mvt) {
		this.mvt = mvt;
	}

	public void setColor(int r, int g, int b, int blend) {
		p.colorMode(PConstants.HSB);
		this.r = r;
		this.g = g;
		this.b = b;
		this.blend = blend;
	}

	/**
	 * 
	 * Rotate of a angle
	 */
	public void rotate(float a) {
		alpha = alpha + a;
	}

	/**
	 * 
	 * Rotate of stepAlpha
	 */
	public void rotate() {
		alpha = alpha + stepAlpha;
	}

	/**
	 * @return the location
	 */
	public PVector getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(PVector location) {
		this.location = location;
	}

	public int getBlend() {
		return blend;
	}

	public void setBlend(int blend) {
		this.blend = blend;
	}

	
	/**
	 * @return the side
	 */
	public float getSide() {
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(float side) {
		this.side = side;
	}

	/**
	 * @return the alpha
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	/**
	 * @param alpha the alpha to set
	 */
	public void setStepAlpha(float stepAlpha) {
		this.stepAlpha = stepAlpha;
	}
	public float getAngle() {
		return alpha;
	}

	public void move() {
		if (mvt != null) {
			mvt.move();
			this.setLocation(new PVector(mvt.getLocation().x, mvt.getLocation().y));
		}
	}

	public void show() {

		p.pushMatrix();
		p.rectMode(PConstants.CENTER);
		p.translate(location.x, location.y);
		p.rotate(alpha);
		//p.stroke(r, g, b, blend);
		p.stroke(r, g, b, 255);
		p.fill(r, g, b, blend);
		p.rect(0, 0, side, side);
		p.popMatrix();
	}
}
