package Perlin;

import processing.core.*;

public class NoiseField extends PApplet {
    int bgColor = 64;
    int resX = 2048;
    int resY = 1024;
    int step = 40;
    float inc = 0.01f;
    float zoff = 0.0f;

    PVector[] field;
    int cols, rows;

    // Perlin noise demo code
    public static void main(String _args[]) {
        PApplet.main(NoiseField.class.getName());
    }

    public void settings() {
        size(resX, resY);
        //fullScreen();
    }

    public void setup() {
        colorMode(HSB);
        background(bgColor,0,10);
        cols = floor(width / step) + 1;
        rows = floor(height / step) + 1;
        field = new PVector[cols * rows];
    }

    public void draw() {
        background(bgColor,0,10);
                              
        float yoff = 0.0f;
        for (int y = 0; y < rows; y++) {
            float xoff = 0.0f;
            for (int x = 0; x < cols; x++) {
                
                float n =noise(xoff, yoff, zoff);
                float angle =  n * TWO_PI * 4;
                float c = map(n,-1,1,0,255) ;
                PVector v = PVector.fromAngle(angle);
                v.setMag(1);
                int index = x + y * cols;
                field[index] = v;
                
                pushMatrix();
                    translate(x * step, y * step);
                    rotate(v.heading());
                    colorMode(HSB);
                    stroke(c,255,250,125);
                    strokeWeight(.5f);
                    line(0, 0, step, 0);
                    strokeWeight(4);
                    point(0,0);
                popMatrix();

                xoff = xoff + inc;
            }
            yoff = yoff + inc;
        }
        zoff = zoff + 0.004f;
    }
}
