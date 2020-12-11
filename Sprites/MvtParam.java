package Sprites;

import processing.core.PApplet;
import processing.core.*;

public class MvtParam {

	PApplet p;
	float t;
	float r;
	float q;
	float scale;
	float step;
	float x,y;

	// Constructor
	MvtParam(PApplet p_) {
		// Retrieve parent reference for processing function calls
		p = p_;

		t = 0;
		scale = 100.0f;
		step = 0.01f;
		q = 0.5f;
		r = 0.333f;
	}

	void setStart(float t_)
	{
		t = t_;
	}

	void setStep(float s_)
	{
		step = s_;
	}

	void setScale(float s_)
	{
		scale = s_;
	}

	void setCoeficients(float q_, float r_)
	{
		r = r_;
		q = q_;
	}

	PVector getPos()
	{
		return new PVector(x,y);
	}

	void stop()
	{

	}
	
	void live()
	{
		t = t + step;
		x = (float) Math.sin(t / q) * scale;
		y = (float) Math.sin(t / r) * scale;	
	}

	void show()
	{
		live();
		p.pushMatrix();
		p.translate(p.width/2, p.height/2);

		p.stroke(255);
		p.point(x,y);
		p.popMatrix();
	}
}
