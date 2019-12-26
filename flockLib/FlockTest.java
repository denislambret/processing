package flockLib;

import java.util.ArrayList;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.Group;
import processing.core.PApplet;

public class FlockTest extends PApplet {
	//
	// Display default settings
	//
	int bgColor = 50;
	int defaultColor = 255;
	int w = 1920;
	int h = 1280;
	
	ArrayList<Boyd> boids;
	int maxBoyd = 1000;
	float maxSpeed;
	float maxForce;
    int fAlign;
    int fCohesion;
    int fSeparation;
    int field;
	float perceptionRadius;
	
    ControlP5 ctrGUI;
	int rpos1 = 10;
	int stepRpos = 25;

	

	public static void main(String[] args) {
		PApplet.main("flockLib.FlockTest");
	}

	//
	// Event GUI init
	//
	protected void initGUI() {
		// -- Justification variables
		int sliderXSize = 200;
		int sliderYSize = 20;
		int knobRadius = 30;
		int grpXPos = 0;
		int grpYPos = 10;
		float xCtr = 10;
		float yCtr = 10;
		float yOffset = 50;

		// -- Instantiate interface
		ctrGUI = new ControlP5(this);

		// -- Create GUI group 1
		// Note : the group
		Group grp1 = ctrGUI.addGroup("grp1").setPosition(grpXPos, grpYPos).setWidth(275).setBackgroundHeight(300)
				.activateEvent(true).setBackgroundColor(color(150,150,250, 50)).setBackgroundHeight(300).setLabel("MASTER");
		
		// -- Add controlers to group 1
		// Note : Controller position is relative to group position top left corner
		// - Level 1 - controllers list
		ctrGUI.addSlider("Particules").setPosition(xCtr, yCtr).setSize(sliderXSize, sliderYSize).setRange(2, 2000)
				.setNumberOfTickMarks(100).setValue(500.0f).moveTo(grp1).show();
		yCtr += yOffset;

		// - Level 2 - Perception radius
		ctrGUI.addSlider("Perception").setPosition(xCtr, yCtr).setSize(sliderXSize, sliderYSize).setRange(10, 200)
				.setNumberOfTickMarks(100).setValue(100.0f).moveTo(grp1).show();
		yCtr += yOffset;

		// - Level 3 - controllers list
		ctrGUI.addKnob("maxForce").setPosition(xCtr, yCtr).setSize(knobRadius, knobRadius).setRange(0.0f, 20.0f)
				.setNumberOfTickMarks(100).setValue(3.0f).moveTo(grp1).show();
		ctrGUI.addKnob("maxSpeed").setPosition(xCtr + 95, yCtr).setSize(knobRadius, knobRadius).setRange(0.5f, 50.0f)
				.setNumberOfTickMarks(100).setValue(1.0f).moveTo(grp1);
		ctrGUI.addKnob("dummy").setPosition(xCtr + 170, yCtr).setSize(knobRadius, knobRadius).setRange(0.0f, 100.0f)
		.setNumberOfTickMarks(100).setValue(50.0f).moveTo(grp1);
		yCtr += yOffset;
		
		// - Level 4 - controllers list
		ctrGUI.addToggle("Align").setPosition(xCtr, yCtr).setValue(1).moveTo(grp1).show();
		ctrGUI.addToggle("Cohesion").setPosition(xCtr + 75, yCtr).setValue(1).moveTo(grp1).show();
		ctrGUI.addToggle("Separation").setPosition(xCtr + 150, yCtr).setValue(1).moveTo(grp1).show();
		yCtr += yOffset;
		
		// - Level 5 - others
		ctrGUI.addToggle("Field").setPosition(xCtr, yCtr).setValue(1).moveTo(grp1).show();
		yCtr += yOffset;
	}

	//
	// Control event management
	//
	public void controlEvent(ControlEvent theEvent) {
		if (theEvent.isController()) {
			// - Level 1
			if (theEvent.getController().getName() == "Particules") {
				maxBoyd = (int) ctrGUI.getController("Particules").getValue();
				reset();
			}

			// - Level 2
			if (theEvent.getController().getName() == "Perception") {
				perceptionRadius = (int) ctrGUI.getController("Perception").getValue();
			}

			// - Level 3
			if (theEvent.getController().getName() == "maxForce") {
				maxForce = (int) ctrGUI.getController("maxForce").getValue();
			}

			if (theEvent.getController().getName() == "maxSpeed") {
				maxSpeed = (int) ctrGUI.getController("maxSpeed").getValue();
			}
		
			if (theEvent.getController().getName() == "Align") {
				fAlign  = (int) ctrGUI.getController("Align").getValue();
			}

			if (theEvent.getController().getName() == "Cohesion") {
				fCohesion = (int) ctrGUI.getController("Cohesion").getValue();
			}

			// - Level 4
			if (theEvent.getController().getName() == "Separation") {
				fSeparation = (int) ctrGUI.getController("Separation").getValue();
			}

			// - Level 5
			if (theEvent.getController().getName() == "Field") {
				field = (int) ctrGUI.getController("Field").getValue();
			}
		}
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
		perceptionRadius = 100.0f;
		initGUI();
		reset();
	}

	//
	// draw() routine
	//
	public void draw() {
		background(bgColor);
		for (Boyd item : boids) {
			item.setCohesion(fCohesion);
			item.setAlign(fAlign);
			item.setSeparation(fSeparation);
			item.setMaxSpeed(maxSpeed);
			item.setMaxSpeed(maxForce);
			item.setPerceptionRadius(perceptionRadius);
			item.setField(field);
			item.edges();
			item.flock(boids);
			item.update();
			item.show();
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
		boids = new ArrayList<Boyd>();
		for (int i = 0; i < maxBoyd; i++) {
			Boyd b = new Boyd(this);
			boids.add(b);
		}

	}

	//
	// Control event management
	//
	public void keyPressed() {

	}

	public void mousePressed() {

	}
}