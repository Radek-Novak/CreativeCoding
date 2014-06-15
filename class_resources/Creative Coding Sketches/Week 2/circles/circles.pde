/*
 * Creative Coding
 * Week 2, 05 - Moving Patterns 1
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch builds on the previous sketches, drawing shapes based on the
 * current framerate. The movement of individual shapes combine to create a
 * gestalt field of motion. Use the arrow keys on your keyboard to change the
 * frame rate. 
 * 
 */

/*
  heavy remake by radek-novak.com
*/
int num = 13;
int radius = 200;
int fillOpac = 0;
int strokeOpac = 255;

void setup() {
  size(800, 800);
  fill(0,0,0,fillOpac);
  stroke(0,0,0, strokeOpac);
  //blendMode(SCREEN);
  circles(num, radius);
}

void draw() {
  if (mousePressed) {
/*    fillOpac = floor(((float) mouseY / height) * 10);
    fill(0,0,0,fillOpac);
    strokeOpac = 255 - fillOpac;
    stroke(0,0,0, strokeOpac);*/

    radius = mouseX;
    circles(num, radius);
  }
} //end of draw 

void circles( int num, int size) {
    background(255);
    int margin = 0;
    float gutter = 0; //distance between each cell
    float cellsize = ( width - (2 * margin) - gutter * (num - 1) ) / (num - 1);

    int circleNumber = 0; // counter
    for (int i=0; i<num; i++) {
      for (int j=0; j<num; j++) {
        float tx = margin + cellsize * i + gutter * i;
        float ty = margin + cellsize * j + gutter * j;

        arc(tx, ty, mouseX, mouseX, 0, TWO_PI);
      }
    }
}

void keyPressed() {
  if (keyCode == RIGHT) {
    num++;
  } else if (keyCode == LEFT && num > 1) {
    num--;
  }
  circles(num, radius);
}