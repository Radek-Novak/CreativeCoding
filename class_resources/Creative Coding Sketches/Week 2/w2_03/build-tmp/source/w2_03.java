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

public class w2_03 extends PApplet {

/*
 * Creative Coding
 * Week 2, 03 - n squares
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * In the next iteration of the square drawing sketch, this version selects a random number of squares
 * and a random gap between them. From this it calculates the width of each square then draws the squares
 * using two nested for loops.
 *
 * A simple drop shadow is also drawn to give the illusion of depth.
 * 
 */

/*

remade to imitate vera molnar's 25 squares
radek-novak.com
*/

public void setup() {
  size(600, 600);
  rectMode(CORNER);
  noStroke();
  frameRate(20);  // set the frame rate to 1 draw() call per second

  colorMode(HSB, 255);
}


public void draw() {

  
  if (mousePressed) {
    background(180); // clear the screen to grey
    int num = 5;   // select a random number of squares each frame
    int gap = 1; // select a random gap between each square
    int opac = 210;
    // calculate the size of each square for the given number of squares and gap between them
    float cellsize = ( width - (num + 1) * gap ) / (float)num;
    
    // print out the size of each square
    println("cellsize = " + cellsize);

      for (int i=0; i<num; i++) {
        for (int j=0; j<num; j++) {

          int xoff = (int) random(16)-8;
          int yoff = (int) random(16)-8;
          
          if (random(5) < 4) {
            fill(0, 170, 120, opac);
          } else {
            fill(0, 230, 120, opac);
          }
          rect(gap * (i+1) + cellsize * i - xoff, gap * (j+1) + cellsize * j - yoff, cellsize, cellsize);
        }
      }
  }
  if (keyPressed == true && key=='s') {
    saveFrame("25sq.jpg");
  }
} //end of draw 
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w2_03" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
