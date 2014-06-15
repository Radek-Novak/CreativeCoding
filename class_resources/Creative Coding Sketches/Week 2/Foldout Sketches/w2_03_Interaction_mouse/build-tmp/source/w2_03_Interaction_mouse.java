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

public class w2_03_Interaction_mouse extends PApplet {

/*
 * Creative Coding
 * Week 2, Foldout 04: Basic Mouse Interaction
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch shows how to do interaction with the mouse.
 * Try pressing, releasing, clicking and dragging the mouse and observe the results.
 * When each event happens the sketch also prints a message in the console.
 *
 */
 
float circleWidth;
float strokeWeightValue;
float backgroundValue;
float strokeColorValue;

public void setup() {
  size(300, 300);
  circleWidth = 150;
  backgroundValue = 120;
  strokeWeightValue = 40;
  strokeColorValue = 80;
}


public void draw() {
  background(backgroundValue);
  stroke(strokeColorValue);
  strokeWeight(strokeWeightValue);
  ellipse(150, 150, circleWidth, circleWidth);
  
  if(mousePressed){
     strokeColorValue = 50 + mouseY/5;
  }

}


public void mousePressed() {
  println("The mouse button was pressed");
  circleWidth = 150;
}

public void mouseReleased() {
  println("The mouse button was released");
  circleWidth = 50;
}

public void mouseClicked() {
  println("The mouse button was clicked");
  if (backgroundValue == 120) {
    backgroundValue = 180;
  }else{
    backgroundValue = 120;
  }
}

public void mouseDragged(){
  println("The mouse is being dragged");
  strokeWeightValue = mouseX/10;
  
  //Ensure that the value for stroke weight is never negative.
  if(strokeWeightValue < 0)
    strokeWeightValue = 0;
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w2_03_Interaction_mouse" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
