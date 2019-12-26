package snowflakes;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Particule {

	PApplet p;
    PVector position;
    float radius;
    
	// Constructor
	Particule(PApplet p_,PVector position) {
		// Retrieve parent reference for processing function calls
		p = p_;
        this.position = position;
	    this.radius = 8.0f;
	}	
	
	void update()
	{
		position.x -= 1;
		position.y += p.random(-1,1);
		float angle = position.heading();
		angle = PApplet.constrain(angle,0, PConstants.PI / 3);
		//position = PVector.fromAngle(angle);
		float magnitude = position.mag();
		position.setMag(magnitude);
	}
	
	boolean finished()
	{
		return (position.x < 0);
	}
	
	boolean intersects(ArrayList<Particule> snowFlake)
	{
		boolean result = false;
		for (Particule s : snowFlake)
		{
			float d = PApplet.dist(s.position.x, s.position.y, this.position.x, this.position.y);
			if (d < radius * 2)
			{
				result = true;
				break;
			}
		}
		return result;
	}
	
	void show()
	{
		p.ellipse(position.x, position.y, radius * 2, radius * 2);
		}
}
