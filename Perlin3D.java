import processing.core.*;
import peasy.PeasyCam;

public class Perlin3D extends PApplet {
    float step = 30;
    float inc = 0.0185f;
    float t = 0.015f;
    float xoff = 0.0f;
    float yoff = 0.0f;
    float zoff = 0.0f;
    PVector[] flowField;
    int col;
    int row;
    int dep;
    PeasyCam cam;

    public static void main(String[] args) {
        PApplet.main("Perlin3D");
    }

    public void settings() {
        size(2000, 1000, P3D);
    }
    public void setup() {
        background(32);
        col = floor(100 / step);
        row = floor(100 / step);
        dep = floor(100 / step);
        flowField = new PVector[col * row * dep];
        cam = new PeasyCam(this, 100);
        cam.setMinimumDistance(50);
        cam.setMaximumDistance(500);
          
    
    }

    public void mousePressed() {
        background(32);
        xoff = random(0.0f, 1.0f);
        yoff = random(0.0f, 1.0f);
        zoff = random(0.0f, 1.0f);
    }

    public void draw() {
        background(32,10);
        
        yoff = 0.0f;
        for (int y = 0; y <= row; y++) {
            xoff = 0.0f;
            for (int x = 0; x <= col; x++) {
                zoff = 0.0f;
                for (int z = 0; z <= dep; z++) {
                float n = noise(xoff, yoff, zoff);
                float f = n * 64;
                float b = n * 255;
                float a = n * 2 * TWO_PI;
                PVector v = PVector.fromAngle(a);
                int index = x + y * row;
                if (index < col * row * dep)
                    flowField[index] = v;
                stroke(b,f);
                fill(b,f);
                strokeWeight(1);
                pushMatrix();
                    translate(x * step, y * step);    
                    rotate(v.heading());
                    line(0,0,0,0,step,0);
                popMatrix();

                zoff += inc;
            }
            yoff += inc;
        }
        zoff += t;
    }
  }
}