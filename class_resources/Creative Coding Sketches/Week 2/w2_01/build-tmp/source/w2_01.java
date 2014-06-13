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

public class w2_01 extends PApplet {

/*
 * Creative Coding
 * Week 2, 01 - Nine Squares
 * by Indae Hwang
 * Copyright (c) 2014 Monash University
 *
 * This program draws 3 rows of 3 squares in the display window
 * Each row is coloured red, green, and blue.
 * Each rectangle is draw individually, meaning there are 9 rect calls.
 * 
 */

public void setup() {
  size(600, 600);
  background(180);
  noLoop();  // only execute the draw function once
  rectMode(CENTER); // set the rectangle drawing mode to specify the rectangle's centre
  noStroke();
}


public void draw() {

  // row 1: red
  fill(255, 0, 0);
  rect(150, 150, 100, 100);
  rect(300, 150, 100, 100);
  rect(450, 150, 100, 100);

  // row 2: green
  fill(0, 255, 0);
  rect(150, 300, 100, 100);
  rect(300, 300, 100, 100);
  rect(450, 300, 100, 100);

  // row 3: blue
  fill(0, 0, 255);
  rect(150, 450, 100, 100);
  rect(300, 450, 100, 100);
  rect(450, 450, 100, 100);
  
} // end draw

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w2_01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
