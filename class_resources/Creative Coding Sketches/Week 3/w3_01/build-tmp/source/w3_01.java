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

public class w3_01 extends PApplet {

/*
 * Creative Coding
 * Week 3, 01 - using map() to map mouse co-ordinates to background colour 
 * by Indae Hwang
 * Copyright (c) 2014 Monash University
 *
 * This program allows you to change the background color.
 * Press and hold 'left mouse button' to change color.
 * 
 */

float red;
float green;
float blue;


public void setup() {
  size(500, 500);

  // initialise the colour variables
  red = 0;
  blue = 0;
  green = 0;
}


public void draw() {
  background(red, green, blue);

  if (mousePressed) {
    red = map(mouseX-mouseY, 0, width, 0, 255);
    green = map(mouseY-mouseX, 0, height, 0, 255);
    blue = map(mouseX+mouseY, 0, width+height, 255, 0);
    
    println("red: "+red+", green: "+green+", blue: "+blue);
    
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w3_01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
