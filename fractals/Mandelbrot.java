package fractals;

import processing.core.PApplet;

public class Mandelbrot extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 50;
	int defaultColor = 255;
	int defaultWeight = 1;
	int w = 800;
	int h = 800;

	float minval = -0.5f;
	float maxval = 0.5f;

	float minSlider;
	float maxSlider;

	float frDiv;

	public void draw() {
		int maxiterations = 100;
		println("Here");
		loadPixels();
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {

				float a = map(x, 0.0f, width, -2.5f, 0.0f);
				float b = map(y, 0.0f, height, 0.0f, 2.5f);

				float ca = a;
				float cb = b;

				float n = 0;

				while (n < maxiterations) {
					float aa = a * a - b * b;
					float bb = 2 * a * b;
					a = aa + ca;
					b = bb + cb;
					if (a * a + b * b > 16) {
						break;
					}
					n++;
				}

				int bright = (int) map(n, 0, maxiterations, 0, 1);
				bright = (int) map(sqrt(bright), 0, 1, 0, 255);
				println("Bright :" + bright);
				if (n == maxiterations) {
					bright = 0;
				}

				int pix = (x + y * width) * 4;
				pixels[pix + 0] = bright;
				pixels[pix + 1] = bright;
				pixels[pix + 2] = bright;
				pixels[pix + 3] = 255;
			}
		}
		updatePixels();
	}

	public static void main(String[] args) {
		PApplet.main("fractals.Mandelbrot");
	}

	//
	// settings() routine
	//
	public void settings() {
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
		strokeWeight(defaultWeight);
		reset();
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
		if (key == 'r') {
			reset();
		}

		if (key == 'q') {
			noLoop();
			System.exit(1);
		}
	}

	public void mousePressed() {
		reset();
	}
}