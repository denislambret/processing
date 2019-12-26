package wordCounts;

import processing.core.PApplet;
import processing.core.PVector;

public class Word {
	PApplet p;
	PVector loc;
	PVector v;
	PVector a;
	String value;
	int count;
	int decay;
	int maxDecay;
	int blend;

	public Word(PApplet p_, String value_,int count_, float x_, float y_, int decay_)
	{
		p = p_;
		loc = new PVector(x_,y_);
		a = new PVector(0.0f,0.0f);
		v = new PVector(p.random(-5.0f,5.0f),0.0f);
		value = value_;
		count = count_;
		decay = decay_;
		maxDecay = decay_;
	}

	public void move()
	{
		v.add(a);
		loc.add(v);
		blend = (int) PApplet.map(decay,0,maxDecay,0,255); 
		decay--;
	}
	
	boolean isVisible()
	{
		if ((loc.x < 0) || (loc.x > p.width ))
		{
			return false;
		}
		return true;
	}

	Boolean isAlive()
	{
		if (decay <= 0)
		{
			return false;
		}
		return true;
	}

	public void show()
	{
		if (isAlive())
		{
			p.fill(255,blend);
			p.textSize(count * 0.1f); 
			p.text(value, loc.x, loc.y);
		}
	}

	public void dump()
	{
		System.out.println("loc.x "+loc.x);
		System.out.println("loc.y "+loc.y);
		System.out.println("v.x "+v.x);
		System.out.println("v.y "+v.y);
		System.out.println("a.x "+a.x);
		System.out.println("a.y "+a.y);
		System.out.println("decay "+decay);

	}

}
