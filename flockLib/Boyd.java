package flockLib;

import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.*;

public class Boyd {

	PApplet p;
	PVector location;
	PVector acceleration;
	PVector velocity;
	float maxForce;
	float maxSpeed;
	float perceptionRadius = 50.0f;
	int field;
	int fAlign, fCohesion, fSeparation;

	// Constructor
	Boyd(PApplet p_) {
		// Retrieve parent reference for processing function calls
		p = p_;
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

	public void setAlign(int flag) {
		this.fAlign = flag;
	}

	public void setCohesion(int flag) {
		this.fCohesion = flag;
	}

	public void setSeparation(int flag) {
		this.fSeparation = flag;
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

	PVector align(ArrayList<Boyd> boids) {
		PVector steering = new PVector(0.0f, 0.0f);
		int total = 0;
		for (Boyd other : boids) {
			float d = PApplet.dist(this.location.x, this.location.y, other.location.x, other.location.y);
			if (d < perceptionRadius && other != this) {
				steering.add(other.velocity);
				total++;
			}
		}

		if (total > 0) {
			steering.div(total);
			steering.setMag(this.maxSpeed);
			steering.sub(this.velocity);
			steering.limit(maxForce);
		}

		return steering;
	}

	PVector cohesion(ArrayList<Boyd> boids) {
		PVector steering = new PVector(0.0f, 0.0f);
		int total = 0;
		for (Boyd other : boids) {
			float d = PApplet.dist(this.location.x, this.location.y, other.location.x, other.location.y);
			if (d < perceptionRadius && other != this) {
				steering.add(other.location);
				total++;
			}
		}

		if (total > 0) {
			steering.div(total);
			steering.sub(this.location);
			steering.sub(this.velocity);
			steering.limit(maxForce);
		}

		return steering;
	}

	PVector separation(ArrayList<Boyd> boids) {
		PVector steering = new PVector(0.0f, 0.0f);
		int total = 0;
		for (Boyd other : boids) {
			float d = PApplet.dist(this.location.x, this.location.y, other.location.x, other.location.y);
			if (d < perceptionRadius && other != this) {
				PVector diff = PVector.sub(this.location, other.location);
				diff.div(d);
				steering.add(diff);
				total++;
			}
		}

		if (total > 0) {
			steering.div(total);
			steering.setMag(maxSpeed);
			// steering.sub(this.location);
			steering.sub(this.velocity);
			steering.limit(maxForce);
		}

		return steering;
	}

	public void flock(ArrayList<Boyd> boids) {
		acceleration = new PVector(0, 0);
		if (fAlign > 0) {
			acceleration.add(this.align(boids));
		}
		if (fCohesion > 0) {
			acceleration.add(this.cohesion(boids));
		}
		if (fSeparation > 0) {
			acceleration.add(this.separation(boids));
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
