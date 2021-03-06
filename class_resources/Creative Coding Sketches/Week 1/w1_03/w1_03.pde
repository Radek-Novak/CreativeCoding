/*
 * Creative Coding
 * Week 1, 03 - Draw your name! (part 3)
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 
 * This program allows you to draw using the mouse.
 * Press 's' to save your drawing as an image.
 * Press 'r' to erase all your drawing and start with a blank screen
 * 
 */

// variables to store the delay and target counts
int delayCount;
int targetCount;

// setup function
void setup() {
  size(500, 500);
  background(255);

  delayCount = 0;
  targetCount = (int) random(5, 10); // set target count to a random integer between 10 and 50
}

// draw function
void draw() {

  /* draw a rectangle on your mouse point while you pressing 
   the left mouse button*/

  int style; 

  delayCount++; // increment delay count (shorthand for delayCount = delayCount + 1)

  if (delayCount == targetCount) {
    style = (int) random(4);
    targetCount = (int) random(5, 20) ;
    delayCount = 0;
  }
  else {
    style = 0;
  }


  if (mousePressed) {

    stroke(0); 
    fill(0);

    // switch statement
    switch(style) {
    case 0:
      // draw a point
      ellipse(500-mouseX, 500-mouseY, 2, 2);
      ellipse(mouseX, mouseY,2, 2);
      break;

    case 1:
      // draw a circle with random radius
      float esize = random(1, 5);
      ellipse(500-mouseX, 500-mouseY, esize, esize);
      ellipse(mouseX, mouseY, esize, esize);
      break;

    case 2:
      // draw a random sized rectangle
      float qsize = random(1, 5);
      quad(500-mouseX-qsize, 500-mouseY,500-mouseX, 500-mouseY-qsize, 500-mouseX+qsize, 500-mouseY, 500-mouseX, 500-mouseY+qsize);
      quad(mouseX-qsize, mouseY,mouseX, mouseY-qsize, mouseX+qsize, mouseY, mouseX, mouseY+qsize);
      break;

    case 3:
      // draw a triangle with random size
      float tsize = random(1, 5);
      triangle(500-mouseX-tsize, 500-mouseY+tsize, 500-mouseX, 500-mouseY-tsize, 500-mouseX+tsize, 500-mouseY+tsize); 
      triangle(mouseX-tsize, mouseY+tsize, mouseX, mouseY-tsize, mouseX+tsize, mouseY+tsize); 
      break;
    } // end of switch statement
  }


  // save your drawing when you press keyboard 's'
  if (keyPressed == true && key=='s') {
    saveFrame("yourName.jpg");
  }

  // erase your drawing when you press keyboard 'r'
  if (keyPressed == true && key == 'r') {
    background(255);
  }
}

