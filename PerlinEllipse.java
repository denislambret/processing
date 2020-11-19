import processing.core.*;

public class PerlinEllipse extends PApplet {
    float step = 30;
    float inc = 0.0185f;
    float t = 0.015f;
    float xoff = 0.0f;
    float yoff = 0.0f;
    float zoff = 0.0f;
    PVector[] flowField;
    int col;
    int row;
    

    public static void main(String[] args) {
        PApplet.main("PerlinEllipse");
    }

    public void settings() {
        size(2000, 1000);
    }
    public void setup() {
        background(32);
        col = floor(width / step);
        row = floor(height / step);
        flowField = new PVector[col * row];
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
                float n = noise(xoff, yoff, zoff);
                float f = n * 64;
                float b = n * 255;
                float a = n * 2 * TWO_PI;
                PVector v = PVector.fromAngle(a);
                int index = x + y * row;
                if (index < col * row)
                    flowField[index] = v;
                stroke(b,f);
                fill(b,f);
                strokeWeight(1);
                pushMatrix();
                    translate(x * step, y * step);

                    ellipse(0, 0, map(a,0,2*TWO_PI,0,step * 2), map(a,0,2*TWO_PI,0,step * 2));
                    rotate(v.heading());
                popMatrix();

                xoff += inc;
            }
            yoff += inc;
        }
        zoff += t;

    }
}
