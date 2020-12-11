package fourier;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Fourier extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 50;
	int defaultColor = 255;
	int w = 800;
	int h = 800;

	float time;
	float radius;
	ArrayList<Float> wave;

	PVector p1;

	public static void main(String[] args) {
		PApplet.main("fourier.Fourier");
	}

	//
	// settings() routine
	//
	public void settings() {
		// Fullscreen mode
		// fullscreen();

		// Custom screen mode
		size(w, h);
		smooth();
	}

	//
	// setup() routine
	//
	public void setup() {
		background(bgColor);
		stroke(defaultColor);
		reset();

	}

	//
	// draw() routine
	//
	public void draw() {
		background(bgColor);
		stroke(defaultColor);
		noFill();
		translate(200, 200);
		ellipse(0, 0, radius * 2, radius * 2);

		p1 = new PVector(0, 0);
		p1.x = radius * cos(time);
		p1.y = radius * sin(time);
		wave.add(wave.size(),p1.y);
		fill(defaultColor);
		ellipse(p1.x, p1.y, 8, 8);
		line(0, 0, p1.x, p1.y);

		translate(200, 0);
		line(p1.x - 200, p1.y, 0, wave.get(0));
		beginShape();
		noFill();
		for (int i = 0; i < wave.size(); i++) {
			vertex(i, wave.get(i));
		}
		endShape();
		time += 0.05f;
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
		radius = 100.0f;
		wave = new ArrayList<Float>();
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