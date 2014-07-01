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

public class w5_01 extends PApplet {

/*
 * Creative Coding
 * Week 5, 01 - Basic Text
 * by Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch shows how to use text in Processing
 * It draws the current location in x and y of the mouse on the screen
 * and a red + centered at the mouse location
 * Note that this reqires that you have the "Arial" typeface installed on
 * your computer. This font is standard on Mac and Windows computers
 *
 */

PFont myFont;      // STEP 1: the font to be used
float xSize = 10;

public void setup() {
  size(800, 600);

  String [] fonts = PFont.list();
  println("Fonts available:");
  println(fonts);

  // STEP 2: create the font object and reference it to myFont
  // Using the Arial font, 16 point with antialiasing (smoothing)
  myFont = createFont("Arial", 16, true); 

  // STEP 3: use myFont at size 24
  textFont(myFont, 24);

  // set the fill colour to white
  fill(255);
}

public void draw() {
  // clear the screen to black
  background(0);

  // get the current mouse position as a string in the form "(x,y)"
  String mousePosition = "(" + str(mouseX) + "," + str(mouseY) + ")";

  // STEP 4: display the mousePosition string at the current mouse location
  float tWd = textWidth(mousePosition);
  float tAsc = textAscent();

  text(mousePosition, 
        mouseX + tWd > width ? mouseX - tWd : mouseX, 
        mouseY - tAsc < 0 ? mouseY + tAsc : mouseY);

  // draw the red '+' at the mouse location
  stroke(255, 0, 0);
  line(mouseX - xSize, mouseY, mouseX + xSize, mouseY);
  line(mouseX, mouseY - xSize, mouseX, mouseY + xSize);
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w5_01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
