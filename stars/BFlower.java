package stars;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;


public class BFlower {
	PApplet p;
	PVector location;
	ArrayList<Leaf> core, body;
	float alpha;
	float coreWidth, coreHeight;
	float bodyWidth, bodyHeight;
	int coreLeaves, bodyLeaves;

	int coreIter, bodyIter;
	
	// Constructor
	public BFlower(PApplet p_,PVector location, float width, float height) {
		// Retrieve parent reference for processing function calls
		this.p = p_;
        this.alpha = 0.0f;
        this.coreWidth = width / 2;
        this.coreHeight = height / 2;
        this.bodyWidth = width;
        this.bodyHeight = height;
        
        this.location = location;
        this.body = new ArrayList<Leaf>();
		this.core = new ArrayList<Leaf>();
		
		for(int j = 1; j <= coreIter; j++)
		{
			for(int i = 1; i <= coreLeaves; i++)
			{
				Leaf myLeaf = new Leaf(this.p, new PVector(coreWidth / 2, coreHeight / 2),50 * j,50 * j);
				myLeaf.setAlpha(PConstants.TWO_PI/coreIter * i + PConstants.PI/coreIter);
				body.add(myLeaf);
			}	
		}
		
		for(int j = 6; j <= bodyIter; j++)
		{
			for(int i = 1; i <= bodyLeaves; i++)
			{
				Leaf myLeaf = new Leaf(this.p, new PVector(bodyWidth / 2, bodyHeight / 2),15 * j,15 * j);
				myLeaf.setAlpha(PConstants.TWO_PI / bodyIter * i);
				core.add(myLeaf);
			}	
		}
        
	}

	/**
	 * @return the coreLeaves
	 */
	public int getCoreLeaves() {
		return coreLeaves;
	}

	/**
	 * @param coreLeaves the coreLeaves to set
	 */
	public void setCoreLeaves(int coreLeaves) {
		this.coreLeaves = coreLeaves;
	}

	/**
	 * @return the bodyLeaves
	 */
	public int getBodyLeaves() {
		return bodyLeaves;
	}

	/**
	 * @param bodyLeaves the bodyLeaves to set
	 */
	public void setBodyLeaves(int bodyLeaves) {
		this.bodyLeaves = bodyLeaves;
	}

	/**
	 * @return the location
	 */
	public PVector getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(PVector location) {
		this.location = location;
	}

	/**
	 * @return the alpha
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	/**
	 * @return the coreWidth
	 */
	public float getCoreWidth() {
		return coreWidth;
	}

	/**
	 * @param coreWidth the coreWidth to set
	 */
	public void setCoreWidth(float coreWidth) {
		this.coreWidth = coreWidth;
	}

	/**
	 * @return the coreHeight
	 */
	public float getCoreHeight() {
		return coreHeight;
	}

	/**
	 * @param coreHeight the coreHeight to set
	 */
	public void setCoreHeight(float coreHeight) {
		this.coreHeight = coreHeight;
	}

	/**
	 * @return the bodyWidth
	 */
	public float getBodyWidth() {
		return bodyWidth;
	}

	/**
	 * @param bodyWidth the bodyWidth to set
	 */
	public void setBodyWidth(float bodyWidth) {
		this.bodyWidth = bodyWidth;
	}

	/**
	 * @return the bodyHeight
	 */
	public float getBodyHeight() {
		return bodyHeight;
	}

	/**
	 * @param bodyHeight the bodyHeight to set
	 */
	public void setBodyHeight(float bodyHeight) {
		this.bodyHeight = bodyHeight;
	}

	/**
	 * @return the coreIter
	 */
	public int getCoreIter() {
		return coreIter;
	}

	/**
	 * @param coreIter the coreIter to set
	 */
	public void setCoreIter(int coreIter) {
		this.coreIter = coreIter;
	}

	/**
	 * @return the bodyIter
	 */
	public int getBodyIter() {
		return bodyIter;
	}

	/**
	 * @param bodyIter the bodyIter to set
	 */
	public void setBodyIter(int bodyIter) {
		this.bodyIter = bodyIter;
	}

	public void show()
	{

		p.pushMatrix();
		p.translate(location.x, location.y);
		p.rotate(alpha);
		
		p.stroke(0,0);
		p.fill(50 * (float) Math.cos(alpha)+80,255,180,125);
		for (Leaf l : body) {
			l.setWidth(l.getWidth() * (float) Math.cos(alpha));
			l.setWidth(l.getHeight() * (float) Math.sin(alpha));
			l.show();	
		}
		
		p.fill(30 * (float) Math.cos(alpha),255,200,50);
		for (Leaf l : core) {
			l.setWidth(l.getWidth() * (float) Math.cos(alpha + PConstants.PI/3));
			l.setWidth(l.getHeight() * (float) Math.sin(alpha + PConstants.PI/3));
			l.show();	
		}
		alpha = alpha + 0.01f;
		p.popMatrix();
	}
}
