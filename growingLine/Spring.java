package growingLine;

import processing.core.PApplet;
import processing.core.PVector;

public class Spring {

	PApplet p;
    Point pointA;
    Point pointB;
    float restLen;
    float k;
    
    
	// Constructor
	Spring(PApplet p_, Point pointA, Point pointB, float l, float k) {
		// Retrieve parent reference for processing function calls
		p = p_;
		  this.pointA = pointA;
		  this.pointB = pointB;
		  this.restLen = l;
		  this.k = k;

	}
	
	void applyForce()
	{
		PVector vecAB = PVector.sub(this.pointB.pos, this.pointA.pos);
		float forceMag = this.k*(this.restLen-vecAB.mag());
		PVector forceAB = vecAB.setMag(forceMag);
		PVector forceBA = PVector.mult(forceAB,-1);
	    this.pointA.addForce(forceBA);
	    this.pointB.addForce(forceAB);
	}
}