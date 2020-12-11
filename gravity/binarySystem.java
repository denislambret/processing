package gravity;

import processing.core.*;

public class binarySystem extends PApplet {
    int w = 1024;
    int h = 1024;
    float scale = 100000;
    float G = 6.67408f;
    Particule p1;
    Particule p2;

    public static void main(String _args[]) {
        PApplet.main(binarySystem.class.getName());
    }    

    public void settings() {
        size(w, h);
		smooth();
    }

    public void setup() {
        p1 = new Particule(this);
        p2 = new Particule(this);
    }

    public void draw() {
        background(0);
        float d = dist(p1.pos.x, p1.pos.y, p2.pos.x, p2.pos.y);
        float f = G / (d * d) * scale;
        f = constrain(f, 1, 50);
        
        PVector F = new PVector(0.0f,0.0f);
        F.setMag(f);
        println("F : " + f);
        float a1 = asin((p2.pos.y-p1.pos.y)/d);
        PVector F1 = F.rotate(a1);
        p1.applyForce(F1);
        p1.update();
        p1.edge();
        p1.show();
        
        float a2 = asin((p1.pos.y-p2.pos.y)/d);
        PVector F2 = F.rotate(a2);
        p2.applyForce(F2);
        p2.update();
        p2.edge();
        p2.show();
    }
}
