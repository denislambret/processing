import processing.core.*;

public class PerlinMatrix extends PApplet {
    int step = 15;
    float inc = 0.02f;
    float t = 0.005f;
    float xoff = 0.0f;
    float yoff = 0.0f;
    float zoff = 0.0f;
    Particule[] particules;
    PVector[] flowField;
    int col;
    int row;
    int maxParticules = 1000;
    

    public static void main(String[] args) {
        PApplet.main("PerlinMatrix");
    }

    public void settings() {
        size(1400, 1400);
    }
    public void setup() {
        background(32,20);
        col = floor(width / step);
        row = floor(height / step);

        particules = new Particule[maxParticules];
        for (int i = 0; i < particules.length; i++) {
            particules[i] = new Particule(this,step);
        }

        flowField = new PVector[col * row];
    }

    public void mousePressed() {
        background(32,20);
        xoff = random(0.0f, 1.0f);
        yoff = random(0.0f, 1.0f);
        zoff = random(0.0f, 1.0f);

        particules = new Particule[maxParticules];
        for (int i = 0; i < particules.length; i++) {
            particules[i] = new Particule(this, step);
        }
    }

    public void draw() {
        background(32,20);
        
        yoff = 0.0f;
        for (int y = 0; y <= row; y++) {
            xoff = 0.0f;
            for (int x = 0; x <= col; x++) {
                float n = noise(xoff, yoff, zoff);
                float f = n * 2;
                float a = n * 2 * TWO_PI;
                PVector v = PVector.fromAngle(a);
                int index = x + y * row;
                if (index < col * row)
                    flowField[index] = v;
                stroke(255,64);
                strokeWeight(1);
                pushMatrix();
                    translate( x * step, y * step);
                    rotate(v.heading());
                    line(0,0,step,0);
                popMatrix();
                xoff += inc;
            }
            yoff += inc;
        }
        zoff += t;

        for (Particule pa : particules) {
            pa.follow(flowField);
            pa.update();
            pa.edge();
            pa.show();
        }
    }
}
