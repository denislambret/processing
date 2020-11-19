import processing.core.PApplet;

class Circle
{
  PApplet p;
  float x, y, d, stepAlpha, alpha;
  boolean fixed;
  
  // Object circle constructor
  Circle(PApplet p_, float x_, float y_, float d_, float stepAlpha_, boolean fFixed)
  {
    x = x_;
    y = y_;
    d = d_;
    p = p_;
    stepAlpha = stepAlpha_;
    alpha = 0;
    fixed = fFixed;
  }
 
 
  // Set circle color based on the distance of the circle to screen center
  void setColor()
  {
    // Color definitions based on brightness and opacity
    float opacityStroke = 150;
    float opacityFill = 100;
    float brightFillLow = 255;
    float brightFillHigh = 100;
    float brightStrokeLow = 255;
    float brightStrokeHigh = 155;
   
    // Map colors based on circle center distance to origin
    float colorStroke = PApplet.map(PApplet.dist(x, y, 0, 0), 0, (float) (p.width * 0.2), brightStrokeLow, brightStrokeHigh);
    float colorFill = PApplet.map(PApplet.dist(x, y, 0, 0), 0, (float) (p.width * 0.2), brightFillLow, brightFillHigh);
    
    // Set processing stuffs
    //noStroke();
    p.stroke(colorStroke, opacityStroke);
    //noFill();
    p.fill(colorFill, opacityFill);
  }
  
  // Return fixed flag status
  boolean isFixed()
  {
    return fixed;
  }
  
  // Set fixed flag status
  void setFixed(boolean flag)
  {
    fixed = flag;
  }
  
  // Draw circle 
  void show()
  {
    setColor();
    p.ellipse(x, y, d, d);
    if (!isFixed()) alpha = alpha + stepAlpha;   
  }
}