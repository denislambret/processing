package Sprites;

import java.util.ArrayList;

import processing.core.PApplet;

public class movingSprite extends PApplet{
	// 
	// Setting definitions
	//
	int display = 1;
	int defaultColor = 250;
	int bkgColor = 75;
	int w = 800;
	int h = 800;
	
	int maxSprites = 32;
	
	ArrayList<Sprite> sprites;
	
	//
	// Set PApplet handle name
	//
	public static void main(String[] args) {
		PApplet.main("Sprites.movingSprite");
	}
	
	//
	// settings()
	//
	public void settings() {
		// Full screen mode
		fullScreen(display);
		
		// Custom screen mode
		//size(w,h,P2D);
		smooth();
		//frameRate(30);
	}
	
	//
	// Reset sprites
	//
	void reset() {
		sprites = new ArrayList<Sprite>();
		for (int i = 0; i < maxSprites; i++) {
			int idx = (int) random(0,3);
			Sprite sprite = null;
			if (idx == 0)
			{
				sprite = new Sprite(this,"./res/goat.png",0,0);
			} else if (idx == 1)
			{
				sprite = new Sprite(this,"./res/heart.png",0,0);
			} else if (idx == 2)
			{
				sprite = new Sprite(this,"./res/bear.png",0,0);
			};
			sprite.genRandomProperties();
			sprites.add(sprite);
		}
		
		// The big skull in the middle
//		Sprite sprite = new Sprite(this,"./res/skull.png",0,0);
//		sprite.setPos(0,0);
//		sprite.setSize(300);
//		sprite.setRadius(1);
//		sprite.setCentered();
//		sprites.add(sprite);
	}
	
	//
	// setup()
	//
	public void setup() {
		// Default background color
		background(bkgColor);
		stroke(defaultColor);
		reset();
	}
	
	//
	// draw()
	//
	public void draw() {
		background(bkgColor);
		for (Sprite sprite : sprites) {
			sprite.move();
			sprite.show();
		}	
	}
	
	//
	// mousePressed() control event
	//
	public void mousePressed() {
		reset();
	}
	
	//
	// keyPressed() control event
	//
	public void keyPressed() {
		noLoop();
		exit();
	}
	
}
