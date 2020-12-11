package squares;

import java.util.ArrayList;
import java.util.Iterator;

import movement.MvtCircular;
import movement.MvtLinear;
import movement.MvtParam;
import processing.core.PApplet;
import processing.core.PVector;

public class SquareTest extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 50;
	int defaultColor = 255;
	int w = 800;
	int h = 800;

	ArrayList<Square> grid;
	int maxSquares, maxCols, maxRows;
	int side;
	float a;

	public static void main(String[] args) {
		PApplet.main("squares.SquareTest");
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
		stroke(255);
		side = 50;
		maxRows = 100;
		maxCols = 10;
		reset();
	}

	//
	// draw() routine
	//
	public void draw() {
		background(0);
		for (Iterator<Square> iter = grid.iterator(); iter.hasNext();) {
			Square square = (Square) iter.next();
			square.rotate(random(0.05f,0.1f));
			square.move();
			square.show();
		}
		a += 0.0005;
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
		float stepX = width / maxRows;
		float stepY = height / maxCols;

		grid = new ArrayList<Square>();
		for (int j = 0; j < maxCols; j++) {
			for (int i = 0; i < maxRows; i++) {
				Square s = new Square(this, random(5, 50));
				s.setLocation(new PVector(i * stepX + side / 2, j * stepY + side / 2));

				if (s.getSide() > 100.0f) {
					MvtCircular mvt = new MvtCircular(this, s.location, 100.0f);
					mvt.genRandomMovement();
					mvt.setRadius(random(1.0f, 50.0f));
					s.setStepAlpha(random(0.0001f,0.00005f));
					s.setMover(mvt);
				} else if (s.getSide() > 25.0f) {
					MvtLinear mvt = new MvtLinear(this, s.location);
					mvt.genRandomMovement();
					s.setMover(mvt);
				} else {
					MvtParam mvt = new MvtParam(this, s.location);
					mvt.genRandomMovement();
					mvt.setLocation(new PVector(w / 2, h / 2));
					mvt.setTime(random(0.0f, 10000.0f));
					mvt.setScale(h);
					s.setMover(mvt);
				}

				int c = (int) random(128, 255);
				s.setColor(c, c, c, 110);
				grid.add(s);
			}

		}

	}

	//
	// Control event management
	//
	public void keyPressed() {
		for (Iterator<Square> iter = grid.iterator(); iter.hasNext();) {
			Square square = (Square) iter.next();
			square.mvt.stop();
		}
	}

	public void mousePressed() {
		reset();
	}
}
