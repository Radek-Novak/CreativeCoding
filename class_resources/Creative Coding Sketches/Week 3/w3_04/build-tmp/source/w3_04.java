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

public class w3_04 extends PApplet {

/*
 * Creative Coding
 * Week 3, 04 - spinning top: curved motion with sin() and cos()
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch is the first cut at translating the motion of a spinning top
 * to trace a drawing path. This sketch traces a path using sin() and cos()
 *
 */

float x, y;      // current drawing position
float dx, dy;    // change in position at each frame
float rad;       // radius for the moving hand

public void setup() {
  size(800, 800);

  // initial position in the centre of the screen
  x = width/2;
  y = height/2;

  // dx and dy are the small change in position each frame
  dx = random(-1, 1);
  dy = random(-1, 1);
  background(255);
  //frameRate(200);
}


public void draw() {
  // blend the old frames into the background
  //blend(BLEND);
  fill(255, 2);
  //rect(0, 0, width, height);
  rad = radians(frameCount);

  // calculate new position
  x += dx;
  y += dy;

  float max = 1;
  float min = 0.5f;

 if (x > width-100 || x < 100) {
    x = x > width - 100 ? 100 : width - 100;
    //dx = dx > 0 ? -random(min, max) : random(min, max);
  }

  if (y > height-100 || y < 100) {
    y = y > height - 100 ? 100 : height - 100;
    //dy = dy > 0 ? -random(min, max) : random(min, max);
  }
  float bx = x + 100 * sin(rad);
  float by = y + 100 * cos(rad);

  fill(80,55,20);

  float radius = 100 * sin(rad*0.1f);
  float handX = bx + radius * sin(rad*3);
  float handY = by + radius * cos(rad*3);

  stroke(80,55,20, 50);
  line(bx, by, handX, handY);
  line(width - bx, height - by, width -  handX, height - handY);
  line(width - bx, by, width -  handX, handY);
  line(bx, height - by, handX, height - handY);
  //dx = random(99) > 97 ? -dx : dx;
  //dy = random(99) > 97 ? -dy : dy;
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w3_04" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
