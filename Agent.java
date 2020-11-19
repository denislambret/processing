import processing.core.*;

public class Agent {
  PApplet p;
  PVector vector;
  PVector vectorOld;
  float stepSize;
  float angle;
  boolean isOutside;

  Agent(PApplet p) {
    this.p = p;
    this.vector = new PVector(p.random(p.width), p.random(p.height));
    this.vectorOld = this.vector.copy();
    this.stepSize = p.random(1, 2);
    this.isOutside = false;
    this.angle = 0.0f;
  };

  void update(float strokeWidth) {
    // Move agent in direction pointed by angle on a stepSize length
    this.vector.x += Math.cos(this.angle) * this.stepSize;
    this.vector.y += Math.sin(this.angle) * this.stepSize;
    
    // Manage boundary cases
    this.isOutside = this.vector.x < 0 || this.vector.x > p.width || this.vector.y < 0
        || this.vector.y > p.height;
    
    // if outside, we reset agent position in the window
    if (this.isOutside) {
      this.vector.set(p.random(p.width), p.random(p.height));
      this.vectorOld = this.vector.copy();
    }
    
    // Stroke width is linked to stepSize, the greater, the bigger
    p.strokeWeight(strokeWidth * this.stepSize);
    p.line(this.vectorOld.x, this.vectorOld.y, this.vector.x, this.vector.y);
    
    // Save position as previous position
    this.vectorOld = this.vector.copy();
    
    // Reset outside flag
    this.isOutside = false;
  };

  void update1(float noiseScale, float noiseStrength, float strokeWidth) {
    // Compute angle based on perlin noise field
    // We move our agent in the noise, meaning for x,y position we compute noise position function as angle value
    // associated to our agent. 
    this.angle = p.noise(this.vector.x / noiseScale, this.vector.y / noiseScale) * noiseStrength;
    this.update(strokeWidth);
  };

  void update2(float noiseScale, float noiseStrength, float strokeWidth) {
    this.angle = p.noise(this.vector.x / noiseScale, this.vector.y / noiseScale) * 12;
    this.angle = (this.angle - (float) Math.floor( this.angle)) * noiseStrength;
    this.update(strokeWidth);
  };

}
