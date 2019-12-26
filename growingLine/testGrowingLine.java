package growingLine;
import processing.core.PApplet;

public class testGrowingLine extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 50;
	int defaultColor = 255;
	int w = 800;
	int h = 800;
	GrowingLine gl;
	float tStep = 0.3f;

	public static void main(String[] args) {
		PApplet.main("growingLine.testGrowingLine");
	}


	public void settings() {
		// Custom screen mode
		size(w, h);
		smooth();
	}

	
	public void setup() {
	  
	  // Circle
	  gl = new GrowingLine(true);
	  for(float a=0; a<TAU; a+=TAU/100){
	    float r = 100;
	    gl.addPoint(0.5*width+r*cos(a),0.5*height+r*sin(a),false,5); //(a==0 || a==TAU)? true : 
	  }
	} 

	public void draw() {
	  background(30);
	  if(mousePressed){
	    gl.insertPoint(false,5,5,Math.floor(random(gl.points.length-2)));
	  }
	  gl.update(tStep);
	  gl.display();
	  

	}
	
	
	
	---
	

}