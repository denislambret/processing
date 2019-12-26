package flowfield;

import processing.core.PApplet;
import processing.core.PVector;

public class flowFieldTst extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 0;
	int defaultColor = 255;
	int w = 800;
	int h = 800;
	float inc = 0.1f;
	float scl = 10;
	int cols, rows;
	
	float zoff = 0;

	public static void main(String[] args) {
		PApplet.main("flowfield.flowFieldTst");
	}

	Particule[] particules;
	PVector[] flowfield;

	//
	// settings() routine
	//
	public void settings() {
		size(w, h);
		smooth();
	}

	//
	// setup() routine
	//
	public void setup() {
		background(bgColor);
		stroke(255);
		cols = floor(200 * scl);
		rows = floor(200 * scl);
		flowfield = new PVector[cols * rows];
		particules = new Particule[cols * rows];

		for (int i = 0; i < cols * rows; i++) {
			particules[i] = new Particule(this);
		}
		background(255);
	}

	//
	// draw() routine
	//
	public void draw() {
		float yoff = 0;
		for (int y = 0; y < rows; y++) {
			float xoff = 0;
			for (int x = 0; x < cols; x++) {
				int index = x + y * cols;
				float angle = noise(xoff, yoff, zoff) * TWO_PI * 4;
				PVector v = PVector.fromAngle(angle);
				v.setMag(1);
				flowfield[index] = v;
				xoff += inc;
				stroke(defaultColor, 50);
				// push();
				// translate(x * scl, y * scl);
				// rotate(v.heading());
				// strokeWeight(1);
				// line(0, 0, scl, 0);
				// pop();
			}
			yoff += inc;

			zoff += 0.0003;
		}

		for (int i = 0; i < particules.length; i++) {
			particules[i].follow(flowfield);
			particules[i].update();
			particules[i].edges();
			particules[i].show();
		}
	}

	//
	// Byebye proc.
	//
	public void quit() {
		noLoop();
		exit();
	}

	//
	// Reset procedure
	//
	public void reset() {
	}

	//
	// Control event management
	//
	public void keyPressed() {
		reset();
	}

	public void mousePressed() {
		reset();
	}
}