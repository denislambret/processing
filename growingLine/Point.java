package growingLine;

import processing.core.PApplet;
import processing.core.PVector;

public class Point {

	PApplet p;
	PVector pos;
	PVector vel;
	PVector acc;
	PVector force;
	int m;
	boolean fixed;
	float drag;
	float collisionDist;


	// Constructor
	Point(PApplet p_, float x, float y, boolean fixed) {
		// Retrieve parent reference for processing function calls
		p = p_;
		this.pos = new PVector(x,y);
		this.vel = new PVector(0,0);
		this.acc = new PVector(0,0);
		this.force = new PVector(0,0);
		this.m = 1;
		this.fixed = fixed;
		this.drag = 0.8f;
		this.collisionDist = 15.0f; // This affects a lot how smooth the result will be: 5=detailed, 20=smooth

	}

	void resetForces()
	{
		this.force.mult(0);
	}

	void addForce(PVector f)
	{
		this.force.add(f);
	}

	void updatePos(float dt)
	{
		if(!this.fixed){
			this.acc = this.force.div(this.m);
			this.vel.add(PVector.mult(this.acc,dt));
			this.pos.add(PVector.mult(this.vel,dt));
			this.vel.mult(this.drag);
		}
	}

	void fix()
	{
		this.fixed = true;
	}

	// Not efficient, should only check n neighbour points
	void	checkCollisions(float i, points) {
		for(var p=0; p<points.length; p++){
			if(i != p){
				let rel = p5.Vector.sub(this.pos,points[p].pos);
				let d = rel.mag();
				if(d < this.collisionDist){
					this.force.add(rel.mult(1/(d+0.001)));
				}
			}
		}
	}

