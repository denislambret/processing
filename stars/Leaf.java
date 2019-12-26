package stars;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import movement.*;

public class Leaf {

	PApplet p;
	PVector location;
	float radius;
	float alpha, stepAlpha;
	int r, g, b;
	int blend;
	float angle, startAngle;
	float w, h, proportion;
	Mover mvt;

	public Leaf(PApplet p, PVector location, float w, float h) {
		this.p = p;
		this.location = location;
		this.w = w;
		this.h = h;		
		this.radius = w;
	}

	
	/**
	 * @return the w
	 */
	public float getWidth() {
		return w;
	}


	/**
	 * @param w the w to set
	 */
	public void setWidth(float w) {
		this.w = w;
	}


	/**
	 * @return the h
	 */
	public float getHeight() {
		return h;
	}


	/**
	 * @param h the h to set
	 */
	public void setHeight(float h) {
		this.h = h;
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

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getRadius() {
		return radius;
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
 
			p.rotate(alpha);
			p.beginShape();
				p.vertex(0,0);
				p.bezierVertex(0, h / 3, w, 2/3 * h, 0,h);
				p.vertex(0,0);
				p.bezierVertex(0, h / 3, -w, 2/3 * h, 0,h);
			p.endShape();
		p.popMatrix();
	}
}

