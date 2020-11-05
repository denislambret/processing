package Perlin;

import processing.core.*;

public class NoiseField extends PApplet {
    int bgColor = 64;
    int resX = 1024;
    int resY = 800;
    int step = 30;
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
        background(bgColor);
        cols = floor(width / step) + 1;
        rows = floor(height / step) + 1;
        field = new PVector[cols * rows];
    }

    public void draw() {
        background(bgColor);
        stroke(255);
        strokeWeight(.5f);
               
        float yoff = 0.0f;
        for (int y = 0; y < rows; y++) {
            float xoff = 0.0f;
            for (int x = 0; x < cols; x++) {
                
                float angle = noise(xoff, yoff, zoff) * TWO_PI * 4;
                PVector v = PVector.fromAngle(angle);
                v.setMag(1);
                int index = x + y * cols;
                field[index] = v;
                
                pushMatrix();
                    translate(x * step, y * step);
                    rotate(v.heading());
                    line(0, 0, step, 0);
                popMatrix();

                // fill(c);
                // rect(x * step, y * step, step, step);
                xoff = xoff + inc;
            }
            yoff = yoff + inc;
        }
        zoff = zoff + 0.004f;
    }
}
