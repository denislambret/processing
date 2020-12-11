package flockLibOpt;

import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.*;

public class Boid {

	PApplet p;
	PVector location;
	PVector acceleration;
	PVector velocity;
	float maxForce;
	float maxSpeed;
	float perceptionRadius = 50.0f;
	int field;
	int fAlign, fCohesion, fSeparation;
    ArrayList<Boid> others;
    
	// Constructor
	Boid(PApplet p_) {
		// Retrieve parent reference for processing function calls
		p = p_;
		others = new ArrayList<Boid>();
		location = new PVector(p.random(p.width), p.random(p.height));
		velocity = PVector.random2D();
		velocity.setMag(p.random(2.0f, 4.5f));
		acceleration = new PVector(0.0f, 0.0f);
		maxForce = 1.0f;
		maxSpeed = 4.0f;
	}

	public void setMaxSpeed(float ms) {
		this.maxSpeed = ms;
	}

	public void setMaxForce(float mf) {
		this.maxForce = mf;
	}

	public float getPerceptionRadius() {
		return perceptionRadius;
	}

	public void setPerceptionRadius(float perceptionRadius) {
		this.perceptionRadius = perceptionRadius;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	public void edges() {
		if (this.location.x > p.width) {
			this.location.x = 0;
		} else if (this.location.x < 0) {
			this.location.x = p.width;
		}

		if (this.location.y > p.height) {
			this.location.y = 0;
		} else if (this.location.y < 0) {
			this.location.y = p.height;
		}
	}

	void findNeighbourgs(ArrayList<Boid> boids)
	{
		others = null;
		others = new ArrayList<Boid>();
		for (Boid other : boids) {
			float d = PApplet.dist(this.location.x, this.location.y, other.location.x, other.location.y);
			if (d < perceptionRadius && other != this) {
				others.add(other);
			}
		}
	}
	
	public void flock() {
		acceleration = new PVector(0, 0);
		PVector alignSteering = new PVector(0.0f, 0.0f);
		PVector separationSteering = new PVector(0.0f, 0.0f);
		PVector cohesionSteering = new PVector(0.0f, 0.0f);
		int total = 0;
		
		for (Boid other : others) {
			float d = PApplet.dist(this.location.x, this.location.y, other.location.x, other.location.y);
			if (d < perceptionRadius && other != this) {
				alignSteering.add(other.velocity);
				cohesionSteering.add(other.location);
				PVector diff = PVector.sub(this.location, other.location);
				diff.div(d);
				separationSteering.add(diff);
				total++;
			}
		}

		if (total > 0) {
			alignSteering.div(total);
			alignSteering.setMag(this.maxSpeed);
			alignSteering.sub(this.velocity);
			alignSteering.limit(maxForce);
			
			cohesionSteering.div(total);
			cohesionSteering.sub(this.location);
			cohesionSteering.sub(this.velocity);
			cohesionSteering.limit(maxForce);
			
			separationSteering.div(total);
			separationSteering.setMag(maxSpeed);
			separationSteering.sub(this.velocity);
			separationSteering.limit(maxForce);
		
		}
   
	    if (fAlign > 0) {
			acceleration.add(alignSteering);
		}
		if (fCohesion > 0) {
			acceleration.add(cohesionSteering);
		}
		if (fSeparation > 0) {
			acceleration.add(separationSteering);
		}
	}

	// Update position
	public void update() {
		this.location.add(velocity);
		this.velocity.add(acceleration);
		this.velocity.limit(maxSpeed);
	}

	public void show() {
		p.strokeWeight(8);
		p.stroke(255);
		p.point(location.x, location.y);
		if (field > 0) {
			p.strokeWeight(2);
			p.stroke(150);
			p.fill(150,150,250, 25);
			p.ellipse(location.x, location.y, perceptionRadius, perceptionRadius);
		}
	}

}
