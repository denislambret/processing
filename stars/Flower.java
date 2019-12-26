package stars;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import movement.*;

public class Flower {

	PApplet p;
	PVector location;
	float radius;
	float alpha, stepAlpha;
	int nbpoints;
	int r, g, b;
	int blend;
	float angle, startAngle;
	float w,h,proportion;
	Mover mvt;




	public Flower(PApplet p,  PVector location, int n, float w, float h, float startAngle, float proportion) {
		this.p = p;
		this.nbpoints = n;
		this.proportion = proportion;
		this.startAngle = startAngle;
		this.radius = w;
		if (n > 2) {
			angle = PConstants.TWO_PI/ (2 * this.nbpoints);  // twice as many sides
			this.w = (float) (w / 2.0f);
			this.h = (float) (h / 2.0f);

		}
	}

	public Flower(PApplet p,  PVector location, int n, float radius) {
		this.p = p;
		this.nbpoints = n;
		this.startAngle = 0.0f;
		this.radius = radius;
		this.proportion = 0.5f;
		
		if (n > 2) {
			angle = PConstants.TWO_PI/ (2 * this.nbpoints);  // twice as many sides
			this.w = (float) (radius / 2.0f);
			this.h = (float) (radius / 2.0f);

		}
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

	public void setRadius(float radius)
	{
		this.radius = radius;
	}

	public float getRadius()
	{
		return radius;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(PVector location) {
		this.location = location;
	}


	public int getNbPoints() {
		return nbpoints;
	}

	public void setNbPoints(int nbpoints) {
		this.nbpoints = nbpoints;
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
		float angle = PConstants.TWO_PI / nbpoints;
		float dw,dh;

		p.pushMatrix();
		p.rectMode(PConstants.CENTER);
		p.translate(location.x, location.y);
		p.rotate(alpha);
		p.stroke(r, g, b, 255);
		p.fill(r, g, b, blend);
		
		p.beginShape();
		p.vertex(w * (float) Math.cos(startAngle),
				h * (float) Math.sin(startAngle));
		
		for (int i = 0; i < nbpoints * 2; i++) {
			dw = w;
			dh = h;
			if (i % 2 == 1) { // for odd vertices, use short radius

				dw = w * proportion;
				dh = h * proportion;
			}
			p.vertex(dw * (float) Math.cos(startAngle + angle * i), dh * (float) Math.sin(startAngle + angle * i));
			
			
			float cx1 = (float) (Math.cos(startAngle + angle * i) * dw);
			float cy1 =  (float) (Math.sin(startAngle + angle * i) * dw);
			float x2 =  (float) (Math.cos(startAngle + angle * (i + 1)) * (dw * proportion));
			float y2 =  (float) (Math.sin(startAngle + angle * (i + 1)) * (dw * proportion));
			float cx2 =  (float) (Math.cos(startAngle + angle * (i + 1)) * dw);
			float cy2 =  (float) (Math.sin(startAngle + angle * (i + 1)) * dw);
			p.bezierVertex(cx1, cy1, cx2, cy2, x2, y2);
		}
		p.endShape(PConstants.CLOSE);
		p.popMatrix();
	}
}

