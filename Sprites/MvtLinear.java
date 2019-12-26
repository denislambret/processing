package Sprites;
import processing.core.PApplet;

public class MvtLinear {

	PApplet p;
    float x,y;
    float transX, transY;
    
	// Constructor
	MvtLinear(PApplet p_, float x_, float y_) {
		// Retrieve parent reference for processing function calls
		p = p_;
		x = x_;
		y = y_;

	}
	
	void genRandomMovement() {
		transX = p.random(-10,10);
		transY = p.random(-10,10);
	}
	
	void setTransX(float sx_)
	{
		transX = sx_;
	}
	
	void setTransY(float sy_)
	{
		transY = sy_;
	}
	
	float getX() {
		return x;
	}
	
	float getY() {
		return y;
	}
	
	void move()
	{
		if ((x <= 0) || (x >= p.width)) {
			transX = -transX;
		}
		
		if ((y <= 0) || (y >= p.height)) {
			transY = -transY;
		}
		
		x = x + transX;
		y = y + transY;
	}
}

