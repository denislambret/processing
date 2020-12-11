import processing.core.*;
import peasy.*;

public class PerlinSphere extends PApplet {
    PeasyCam cam;
    float offset;

    public static void main(String _args[]) {
        PApplet.main(PerlinSphere.class.getName());
    }    

    public void settings() {
        size(800, 800, P3D);
    }
    public void setup() { 
        cam = new PeasyCam(this, 1000); lights(); 
    }

    
    public void draw() {
        background(135, 206, 235);
        fill(0, 128, 0);
        rectMode(CORNER);
        rect(-width, height / 2, width * 2, height / 2);
        noStroke();
        for (int z = -50; z < 32; z++) {
            for (int y = 8; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    float spot = noise(x * 0.2f, y * 0.2f, z * 0.2f + offset) - 0.65f;
                    if (spot > 0) {
                        pushMatrix();
                        translate((x - 8) * 40, (y - 8) * 40, (z - 8) * 40);
                        fill(255, 128);
                        sphere(40);
                        popMatrix();
                    }
                }
            }
        }
        offset -= 0.005;
    }
}
