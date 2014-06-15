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

public class w2_04_Interaction_keyboard extends PApplet {

/*
 * Creative Coding
 * Week 2, Foldout 04: Basic Keyboard Interaction
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch shows how to do interaction with the keyboard.
 * Try pressing, releasing, clicking and dragging the mouse and observe the results.
 * When each event happens the sketch also prints a message in the console.
 *
 */

float circleWidth;
float backgroundValue;

public void setup() {
  size(300, 300);
  circleWidth =   50;
  backgroundValue = 120;
}


public void draw() {
  background(backgroundValue);
  noStroke();
  ellipse(150, 150, circleWidth, circleWidth);
  
  if (keyPressed) {
    if (key == 'a') {
      backgroundValue ++;
    } else if (key == 's') {
      backgroundValue --;
    }
    // ensure backgroundValue is constrianed between 0 and 255
    backgroundValue = constrain(backgroundValue,0,255);
  }
  
}


public void keyPressed() {
  if (key == 'c') {
    circleWidth = 150;
  }
  
  println("pressed " + PApplet.parseInt(key) + " " + keyCode);
}   

public void keyReleased() {
  if (key == 'c') {
    circleWidth = 50;
  }
  
  println("Released " + PApplet.parseInt(key) + " " + keyCode);
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w2_04_Interaction_keyboard" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
