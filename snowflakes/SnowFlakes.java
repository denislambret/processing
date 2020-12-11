package snowflakes;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class SnowFlakes extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 50;
	int defaultColor = 200;
	int blend = 100;
	int w = 800;
	int h = 800;
	Particule current;
	ArrayList<Particule> snowFlake;

	public static void main(String[] args) {
		PApplet.main("snowflakes.SnowFlakes");
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
		stroke(defaultColor,blend);
		reset();
	}

	//
	// draw() routine
	//
	public void draw() {
		background(bgColor);
		stroke(defaultColor, blend);
		translate(width / 2, height / 2);
		current.update();

		if (current.finished() || current.intersects(snowFlake)) {
			snowFlake.add(current);
			current = new Particule(this, new PVector(width / 2, 0));
		}

		for (int i = 0; i < 6; i++) {
			rotate(PI / 3);

			current.show();
			for (Particule pt : snowFlake) {
				pt.show();
			}

			pushMatrix();
			scale(1, -1);
			current.show();
			for (Particule pt : snowFlake) {
				pt.show();
			}
			popMatrix();
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
		snowFlake = new ArrayList<Particule>();
		current = new Particule(this, new PVector(width / 2, 0));

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