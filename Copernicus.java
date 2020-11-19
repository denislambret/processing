import processing.core.*;
import java.util.ArrayList;

public class Copernicus extends PApplet {

	ArrayList<Circle> circles;
	float fRate = 30; // frame rate
	float initRadius = 250.0f;
	float stepPhi = 0.0075f; // rotation speed
	float minScale = 4.5f; // minimum circule radius
	float divRatio = 1.8f; // division ratio between recursive loops

	public static void main(String[] args) {
		PApplet.main("Copernicus");
	}
	
	void reset()
	{
	  // Reset ArrayList
	  circles.clear();
	  
	  // draw a circle systems, one moving, one fixed
	  drawCircle(0, 0, initRadius, true);
	  drawCircle(0, 0, initRadius, false);
	}

	void drawCircle(float x, float y, float d, boolean fFixed)
	{
	  Circle c = new Circle(this, x, y, d, random(-stepPhi,stepPhi), fFixed);
	  circles.add(c);
	  
	  // Recursion exit condition
	  if (d > minScale)
	  {
	    // Recursive calls to drawCircle draws in 4 directions 
	    // Up, down, left, right
	    drawCircle(x + d / divRatio, y, d / divRatio, fFixed);
	    drawCircle(x - d / divRatio, y, d / divRatio, fFixed);
	  }
	}

	public void settings() {
		size(800, 800);
		  
	}

public void setup() {
	  
	  // Create circle systems list
	  circles = new ArrayList<Circle>();
	  reset();
	}

	public void draw() {
	  background(0);
	 
	  for (Circle c : circles)
	  {
	     // Do the color stuff
	    pushMatrix();
	    translate(width/2, height/2);
	    rotate(c.alpha); 
	    c.show();
	    popMatrix();
	  }

	}

	public void mousePressed()
	{
	  reset();
	}

	public void keyPressed()
	{
	  reset();
	}


}
