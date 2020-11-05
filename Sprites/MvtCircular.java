package Sprites;

import processing.core.*;

public class MvtCircular {

	PApplet p;
    float x,y;
    float r;
    float cx,cy;
    float alpha,beta;
    float sAlpha, sBeta;
    
	// Constructor
	public MvtCircular(PApplet p_, float cx_, float cy_) {
		// Retrieve parent reference for processing function calls
		p = p_;
		cx = cx_;
		cy = cy_;

		setAlpha(PConstants.PI / 2);
		setBeta(PConstants.PI);
		setStepAlpha(0.01f);
		setStepBeta(0.01f);
	}
	
	// Setter - radius from rotation center 
	public void setRadius(float r_) {
		r = r_;
	}
	
	// Setter - Alpha rotation angle
	public void setAlpha(float alpha_) {
		alpha = alpha_;
	}

	// Setter - Beta rotation angle
	public void setBeta(float beta_) {
		beta = beta_;
	}
	
	// Setter - step increment alpha
	public void setStepAlpha(float stepAlpha_) {
		sAlpha = stepAlpha_;
	}
	
	// Setter - step increment beta
	public void setStepBeta(float stepBeta_) {
		sBeta = stepBeta_;
	}
	
	public void genRandomMovement() {
		/*this.setAlpha(0p.random(0, PConstants.TWO_PI));
		this.setStepAlpha(p.random(0.001f,0.01f));
		this.setBeta(p.random(0, PConstants.TWO_PI));
		this.setStepAlpha(p.random(0.001f,0.05f));
		
		*/
		this.setAlpha(0);
		this.setStepAlpha(0.01f);
		this.setBeta(0);
		this.setStepAlpha(0.05f);
		this.setStepBeta(p.random(0.01f,0.001f));
		this.setRadius(p.random(64,800));
	}
	
	public void move() {
		alpha = (alpha + sAlpha) % PConstants.TWO_PI;
		beta = (beta + sBeta) % PConstants.TWO_PI;
		x = cx + r * (float) Math.cos(alpha);
		y = cy + r * (float) Math.sin(alpha);
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public float getBeta() {
		return beta;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
