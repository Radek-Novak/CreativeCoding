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

public class w2_02 extends PApplet {

/*
 * Creative Coding
 * Week 2, 02 - Nine Squares, part 2
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program draws 3 rows of 3 squares in the display window
 * Each row is coloured red, green, and blue.
 * In this example the 9 squares are drawn using two different methods involing
 * for loops. Change the value of the variable "caseNum" from 1 to 2 to swap methods.
 *
 * The first method (caseNum == 1) uses 3 for loops to draw each row.
 * The second method (caseNum == 2) uses 2 nested for loops to draw rows and columns.
 *
 * The on-screen results are the same, but the execution flow is different.
 * Notice that in the nested loop case, only one call to rect is made, but it is called 9 times
 * 
 */

// variables:
int num = 3;  // number of rows/cols
int caseNum;  // which method of looping to use

public void setup() {
  size(600, 600);
  background(180);
  noLoop(); // only execute the draw function once
  rectMode(CENTER);
  noStroke();

  // change the value of caseNum from 1 to 2 to execute different parts of the code
  caseNum = 1;
}


public void draw() {
  
  // the first case: 3 for loops, 1 for each row
  if (caseNum == 1) {
    
    // row 1
    fill(255, 0, 0);
    for (int i=0; i<num; i++) {
      rect(150 + 150*i, 150, 100, 100);
    }

    // row 2
    fill(0, 255, 0);
    for (int i=0; i<num; i++) {
      rect(150 + 150*i, 300, 100, 100);
    }

    // row 3
    fill(0, 0, 255);
    for (int i=0; i<num; i++) {
      rect(150 + 150*i, 450, 100, 100);
    }
    
  } else if (caseNum == 2) {
    // second case: nested for loops
    
    for (int i=0; i<num; i++) { // col
      for (int j=0; j<num; j++) { // row
      
        // select the fill colour based on row
        switch(j) {
        case 0:
          fill(255, 0, 0);
          break;
        case 1:
          fill(0, 255, 0);
          break;
        case 2:
          fill(0, 0, 255);
          break;
        }
        // draw the rectangle
        rect(150 + 150*i, 150 + 150*j, 100, 100);
      } // end for (j)
    } // end for (i)
  } // end if
} //end of draw 

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w2_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
