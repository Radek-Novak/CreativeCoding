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

public class w4_02_RandomNosieRandomGaussian extends PApplet {

/*
 * Creative Coding
 * Week 4, Foldout 02: Random, Noise and Gaussian
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program illustrates the use of different methods for generating randomness in Processing
 * See the foldout associated with this sketch for more details
 * 
 */   


public void setup() {
  size(400, 700);
  frameRate(1);
}

public void draw() {
  background(255);

  int num = width;
  float noiseOff = 0;
  
  // noise
  for (int i=0; i< num; i+=5) {
    float y = 150*noise(noiseOff);
    line(i, 150, i, y);
    fill(0);
    ellipse(i,y, 3, 3);
    noiseOff += 0.05f;
  }

  // random
  for (int i=0; i< num; i+=5) {
    float y = random(100);
    line(i,350, i, 350-y);
    ellipse(i, 350-y,3,3);
  }

  // randomGaussian
  for (int i=0; i< num; i+=5) {
    float y = 50*randomGaussian();
    line(i, 540,i, y+540);
    ellipse(i, y+540, 3,3);
  }
  

}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w4_02_RandomNosieRandomGaussian" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
