/*
 * Creative Coding
 * Week 3, 02 - array with sin()
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program demonstrates the use of arrays.
 * It creates three arrays that store the y-position, speed and phase of some oscillating circles.
 * You can change the number of circles by changing the value of num in setup()
 * You can change the background colour by holding the left mouse button and dragging.
 * 
 */

int     num;    // the number of items in the array (# of circles)
float   gap;
int     s;

float[] x;
float[] y;      // y-position of each circle (fixed)
float[] speed;  // speed of each circle
float[] phase;  // phase of each circle
int[]   stopped;

float red = 120;
float green = 120;
float blue = 120;

void setup() {
  size(500, 500);

  num = 10;
  s = 30;
  // allocate space for each array
  x = new float[num];
  y = new float[num];
  speed = new float[num];
  phase = new float[num]; 
  stopped = new int[num];

  // calculate the gap in y based on the number of circles
  gap = height / (num + 1);

  //setup an initial value for each item in the array
  for (int i=0; i<num; i++) {
    y[i] = gap * (i + 1);      // y is constant for each so can be calculated once
    speed[i] = random(3);
    phase[i] = random(TWO_PI);
    println(y[i]);
  }
}


void draw() {
  background(red, green, blue);

  for (int i=0; i<num; i++) {
    // calculate the x-position of each ball based on the speed, phase and current frame
    //float x = width/2 + sin(radians(frameCount*speed[i] ) + phase[i])* 200;
    
    if (stopped[i] == 0) {
      x[i] = width/2 + sin(radians(frameCount*speed[i] ) + phase[i])* 200;
    }
    ellipse(x[i], y[i], s, s);
  }
}


// change the background colour if the mouse is dragged
void mouseDragged() {
  red = map(mouseX, 0, width, 0, 255);
  green = map(mouseY, 0, height, 0, 255);
  blue = map(mouseX+mouseY, 0, width+height, 255, 0);
}

void mouseClicked() {
  for (int i = 0; i < y.length; i++) {
    if (dist(x[i], y[i], mouseX, mouseY) < s ) {
      println(x[i], y[i], i);
      if (stopped[i] == 0) {
        speed[i] = 0;
        stopped[i] = 1;
      } else {
        speed[i] = random(10);
        stopped[i] = 0;
      }
    }

  }
}