package Sprites;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Sprite {
	PApplet p;
	PImage img;
	float x,y;
	float radius = 300f;
	float size;
	MvtCircular m;
    MvtLinear t;
    
	String imgFile;

	// Class constructor
	Sprite(PApplet p_,String imgFile_, float x_, float y_) {
		p = p_;
		imgFile = imgFile_;
		img = p.loadImage(imgFile);
		setPos(x_,y_);
		m = new MvtCircular(p, 0, 0);
		t = new MvtLinear(p, p.width / 2, p.height / 2);
		t.genRandomMovement();
		m.genRandomMovement();
	}

	// Set sprite position
	void setPos(float x_, float y_) {
		x = x_;
		y = y_;
	}

	// Set centered rotation
	void setCentered() {
		p.imageMode(PConstants.CENTER);
	}

	// Generate random sprite properties
	public void genRandomProperties()
	{
		size = p.random(32,128);
	}

	// Sprite move 
	public void move() {
		//m.move();
		//t.move();
		m.move();
		//setPos(t.getX(),t.getY());
		setPos(m.getX(),m.getY());
	}

	// Display sprite by index
	public void show() {
		p.imageMode(PConstants.CENTER);
		p.pushMatrix();

		// move to canvas center and rotate alpha radians on center
		p.translate(p.width / 2, p.height / 2);
		p.rotate(m.getAlpha());

		// move to x, y pos and rotate beta radians from pos
		p.translate(x,y);
		p.rotate(m.getBeta());
		
		p.image(img,0,0,size,size);

		p.popMatrix();
	}
}