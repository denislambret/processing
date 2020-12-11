
// M_1_5_02
//
// Generative Gestaltung – Creative Coding im Web
// ISBN: 978-3-87439-902-9, First Edition, Hermann Schmidt, Mainz, 2018
// Benedikt Groß, Hartmut Bohnacker, Julia Laub, Claudius Lazzeroni
// with contributions by Joey Lee and Niels Poldervaart
// Copyright 2018

import processing.core.*;

public class NoiseAgent extends PApplet {
  Agent[] agents;
  int agentCount = 40000;
  float noiseScale = 300;
  float noiseStrength = 20;
  int overlayAlpha = 10;
  int agentAlpha = 90;
  float strokeWidth = 0.3f;
  int drawMode = 1;

  public static void main(String[] args) {
    PApplet.main("NoiseAgent");
  }

  public void settings() {
    fullScreen();
    //size(1000, 800);
  }

  public void setup() {
    background(64);
    agents = new Agent[agentCount];
    for (int i = 0; i < agentCount; i++) {
      agents[i] = new Agent(this);
    }

  }

  public void draw() {
    // Loop for constant alpha blending applies to background 
    fill(64, overlayAlpha);
    noStroke();
    rect(0, 0, width, height);

    // Draw agents array
    stroke(255, agentAlpha);
    for (int i = 0; i < agentCount; i++) {
      if (drawMode == 1)
        agents[i].update1(noiseScale, noiseStrength, strokeWidth);
      else
        agents[i].update2(noiseScale, noiseStrength, strokeWidth);
    }
  }

  public void keyPressed() {
    drawMode++;
    drawMode = drawMode % 2;
  };

  public void mousePressed() {
    background(64);
    float newNoiseSeed = floor(random(agentCount));
    noiseSeed((long) newNoiseSeed);
  }
}
