// P_2_2_6_01
//
// Generative Gestaltung – Creative Coding im Web
// ISBN: 978-3-87439-902-9, First Edition, Hermann Schmidt, Mainz, 2018
// Benedikt Groß, Hartmut Bohnacker, Julia Laub, Claudius Lazzeroni
// with contributions by Joey Lee and Niels Poldervaart
// Copyright 2018
//
// http://www.generative-gestaltung.de
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * A chain of linked pendulums. Each a little shorter and faster than the one it's linked to.
 * Each joint of the pendulum leaves behind its own trail.
 *
 * KEYS
 * 1                   : toggle pendulum
 * 2                   : toggle pendulum path
 * -                   : decrease speed relation
 * +                   : increase speed relation
 * arrow down          : decrease length of lines
 * arrow up            : increase length of lines
 * arrow left          : decrease joints
 * arrow right         : increase joints
 * del, backspace      : clear screen
 * s                   : save png
 *
 * CONTRIBUTED BY
 * [Niels Poldervaart](http://NielsPoldervaart.nl)
 */
import processing.core.*;

public class Pendulum extends PApplet {

  PVector[] pendulumPath;
  int joints = 5;
  int lineLength = 100;
  float speedRelation = 2;
  PVector center;
  float angle = 0;
  float maxAngle = 360;
  float speed;
  boolean showPendulum = true;
  boolean showPendulumPath = true;


  public static void main(String[] args) {
    PApplet.main("Pendulum");
  }

  public void settings() {
    size(width, height);

  }

  public void setup() {
    colorMode(HSB, 360, 100, 100, 100);
    noFill();
    strokeWeight(1);
    center = new PVector(width / 2, height / 2);
    startDrawing();
  }

void startDrawing() {
  pendulumPath = new PVector[joints];
  // new empty array for each joint
  for (int i = 0; i < joints; i++) {
    PVector v = new PVector(0.0f, 0.0f);
    pendulumPath[i] = v;
  }

  angle = 0;
  speed = (8 / (float) Math.pow(1.75, joints - 1) / (float) Math.pow(2, speedRelation - 1));
}

  public void draw() {
    background(0, 0, 100);
    angle += speed;

    // each frame, create new positions for each joint
    if (angle <= maxAngle + speed) {
      // start at the center position
      var pos = center.copy();

      for (var i = 0; i < joints; i++) {
        var a = angle * pow(speedRelation, i);
        if (i % 2 == 1)
          a = -a;
        PVector nextPos = PVector.fromAngle(radians(a));
        nextPos.setMag((joints - i) / joints * lineLength);
        nextPos.add(pos);

        if (showPendulum) {
          noStroke();
          fill(0, 10);
          ellipse(pos.x, pos.y, 4, 4);
          noFill();
          stroke(0, 10);
          line(pos.x, pos.y, nextPos.x, nextPos.y);
        }

        pendulumPath[i]= nextPos;
        pos = nextPos;
      }
    }

    // draw the path for each joint
    if (showPendulumPath) {
      strokeWeight(1.6f);
      for (var i = 0; i < pendulumPath.length; i++) {
        PVector[] path = pendulumPath;

        beginShape();
        var hue = map(i, 0, joints, 120, 360);
        stroke(hue, 80, 60, 50);
        for (var j = 0; j < path.length; j++) {
          vertex(path[j].x, path[j].y);
        }
        endShape();
      }
    }
  }

public void keyPressed() {
  if (key == 's' || key == 'S') saveFrame();

  if (keyCode == DELETE || keyCode == BACKSPACE) startDrawing();

  if (keyCode == UP) {
    lineLength += 2;
    startDrawing();
  }
  if (keyCode == DOWN) {
    lineLength -= 2;
    startDrawing();
  }
  if (keyCode == LEFT) {
    joints--;
    if (joints < 1) joints = 1;
    startDrawing();
  }
  if (keyCode == RIGHT) {
    joints++;
    if (joints > 10) joints = 10;
    startDrawing();
  }

  if (key == '+') {
    speedRelation += 0.5;
    if (speedRelation > 5) speedRelation = 5;
    startDrawing();
  }
  if (key == '-') {
    speedRelation -= 0.5;
    if (speedRelation < 2) speedRelation = 2;
    startDrawing();
  }

  if (key == '1') showPendulum = !showPendulum;
  if (key == '2') showPendulumPath = !showPendulumPath;
}
}