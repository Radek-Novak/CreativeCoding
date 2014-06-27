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

public class w4_02 extends PApplet {

/*
 * Creative Coding
 * Week 4, 02 - images as textures - image drawing program
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program demonstrates the use of images in Processing
 * See also the foldout: Image Basics in Processing
 *
 * Keyboard interaction:
 * key 'd' = delete drawing
 * key LEFT and RIGHT = De/Increase Image size
 * key '1' and '2' = change image
 *
 */

PImage[] myImage;    // PImage is used to store an image in memory
float scaleOfImage;  // current scale of the image
PImage bgImage;      // display window image
int selectedImageNum;

public void setup() {
  size(800, 800);  

  // allocate a new array with space for two images
  myImage = new PImage[2];
  myImage[0] = loadImage("sample_01.png");
  myImage[1] = loadImage("sample_02.png");
  bgImage = createImage(width, height, RGB);

  // set initial scale to be 0.5 and use the first image (index 0)
  scaleOfImage = 0.5f;
  selectedImageNum = 0;
}

public void draw() {
  // note this new kind of arguement to background - an image!
  background(bgImage);

  // left and right arrow keys to scale the image
  if ( keyPressed ) {
    if (keyCode == LEFT) {
      scaleOfImage -= 0.01f;
    } 
    else if (keyCode == RIGHT) {
      scaleOfImage += 0.01f;
    }
  }

  // draw image accorinding to current scale and mouse position
  pushMatrix();
  translate(mouseX, mouseY);
  float scaleValue = constrain(scaleOfImage, 0.05f, 6);
  scale(scaleValue); 
  rotate(radians(frameCount));
  imageMode(CENTER);
  popMatrix();
  image(myImage[selectedImageNum], 0, 0);

  // if the mouse is pressed load the image into the main background image
  if (mousePressed) {
    loadPixels();
    bgImage.loadPixels();
    bgImage.pixels = pixels;
    bgImage.updatePixels();
  }
}

public void keyReleased() {
  if (key == 'd') {
    bgImage = createImage(width, height, RGB);
  }
  else if (key == '1') {
    selectedImageNum = 0;
  }
  else if (key == '2') {
    selectedImageNum = 1;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w4_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
