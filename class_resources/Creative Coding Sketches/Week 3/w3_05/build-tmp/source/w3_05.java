import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class w3_05 extends PApplet {

/*
 * Creative Coding
 * Week 3, 05 - spinning top: dynamic motion
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch builds on the w3_04 spinning top sketch
 *
 */
float[] x;
float[] y;
float[] dx;
float[] dy;
float[] bx;
float[] by;
float[] ang;
float[] ang_inc;
float[] tDist;
int num = 50;
float max = 0.5f;
float min = 0.1f;

float rad;

public void setup() {
  // size(displayWidth, displayHeight, OPENGL);
  size(displayWidth, displayHeight);

  // allocate
  x = new float[num];
  y = new float[num];
  dx = new float[num];
  dy = new float[num];
  ang = new float[num];
  ang_inc = new float[num];
  bx = new float[num];
  by = new float[num];
  tDist = new float[num];

  // initialise
  for (int i=0; i<num; i++) {
    x[i] = width/2;
    y[i] = height/2;
    dx[i] = random(-0.9f, 0.9f);
    dy[i] = random(-0.9f, 0.9f);
    ang[i] = random(360);
    ang_inc[i] = random(-0.1f, 0.1f);
    bx[i] = 0;
    by[i] = 0;
    tDist[i] = random(10, 100);
  }

  // clear screen
  background(0);
}

public void draw() {

  // slowly fade the image over time
  if (frameCount%100 == 0) {
    fill(0, 5);
    rect(0, 0, width, height);
  }

  for (int i=0; i<num; i++) {
    x[i] += dx[i];
    y[i] += dy[i];
    ang[i] += ang_inc[i];

    if (x[i] > width-100 || x[i] < 100) {

      dx[i] = dx[i] > 0 ? -random(min, max) : random(min, max);
    }

    if (y[i] > height-100 || y[i] <100) {
      dy[i] = dy[i] > 0 ? -random(min, max) : random(min, max);
    }

    bx[i] = x[i] + 100*sin( radians(ang[i]) );
    by[i] = y[i] + 100*cos( radians(ang[i]) );

    fill(180);
  }


  for (int i=0; i<num; i++) {
    for (int j=0; j<i; j++) {

      float dist = dist(x[i], y[i], x[j], y[j]);
      if (dist < tDist[i]) {
        stroke(0, 10);
        beginShape(LINES);
        vertex(x[i], y[i]);
        stroke(255, 10);
        vertex(bx[j], by[j]);
        endShape();
      }

      float b_dist = dist(bx[i], by[i], bx[j], by[j]);
      if (b_dist < tDist[i]) {

        beginShape(LINES);

        vertex(bx[i], by[i]);
        stroke(255, 15);
        vertex(bx[j], by[j]);
        stroke(255, 5);
        endShape();
      }
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w3_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
