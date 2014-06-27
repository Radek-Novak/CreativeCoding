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

public class mazal extends PApplet {

/*
Copyright (c) 2014 Radek Nov\u00e1k
radek-novak.com


element Schizoid
shape:      circle
behavior:   move in line at constant speed
behavior:   bounce from canvas edges
behavior:   grow when not overlapped with another element
behavior:   shrink when overlapping

process Mazal
a picture to be read from is loaded
every element is positioned randomly and sent in random direction
color under the center of each element is painted under the whole element

*/
PImage bgImg;
int[] pixelColors;
int scanLine;  // vertical position

float[] elX;
float[] elY;
float[] elDX;
float[] elDY;
float[] elR;

//float[] strpW;
int[] strpC;

float sum = 0;
int N = 20;

boolean showImg = false;
boolean showBalls = true;

float lastX = 0;

public void setup() {
  size(700, 1005);
  background(0);
  bgImg = loadImage("karelin.jpg");
  
  rectMode(CENTER);
  smooth(4);
  noStroke();

  elX = new float[N];
  elY = new float[N];
  elDX = new float[N];
  elDY = new float[N];
  elR = new float[N];

  strpC = new int[N];

  // init 
  for (int i = 0; i < N; i++) {
    elX[i] = random(0, width);
    elY[i] = random(0, height);
    elDX[i] = random(-1, 1);
    elDY[i] = random(-1, 1);
    elR[i] = random(-1, 1);
  }
}

public void draw() {
  //background(0);
  if (showImg) {
    image(bgImg, 0, 0, width, height);
  }

  // count sum
  for (int i = 0; i < N; i++) {
    sum += elR[i];
  }

  // for each element:
  for (int i = 0; i < N; i++) {
    // draw circle
    if (showBalls) {
      fill(strpC[i], 50);
      ellipse(elX[i], elY[i], elR[i], elR[i]);
    }

    // move 
    elX[i] += elDX[i]; 
    elY[i] += elDY[i];

    // bounce?
    elDX[i] = elX[i] < 1 ? -elDX[i] : elDX[i];
    elDY[i] = elY[i] < 1 ? -elDY[i] : elDY[i];
    elDX[i] = elX[i] > width - elR[i] ? -elDX[i] : elDX[i];
    elDY[i] = elY[i] > height - elR[i] ? -elDY[i] : elDY[i];

    // grow 
    if (frameCount % 100 == 0) {
      elR[i]++;
    }

    // shrink
    for (int j = i+1; j < N; j++) {
      if (dist(elX[i], elY[i], elX[j], elY[j]) <= (elR[i] + elR[j])/2 
          && elR[i] > 0 
          && elR[j] > 0){

          elR[i]--;
          elR[j]--;
      }
    }

    // get color
    strpC[i] = bgImg.get( (int) map(elX[i], 0, width, 0, bgImg.width),
                          (int) map(elY[i], 0, height, 0, bgImg.height));

  }

}
public void saveImg() {
  String s = String.valueOf(second());
  String m = String.valueOf(minute());
  String h = String.valueOf(hour());
  String D = String.valueOf(day());
  String M = String.valueOf(month());
  String Y = String.valueOf(year());
  saveFrame(Y+"-"+M+"-"+D+"_"+h+":"+m+":"+s+".jpg");
}

public void keyPressed() {
  if ( key == 'i') {
    showImg = showImg ? false : true;
  } else if ( key == 'c') {
    showBalls = showBalls ? false : true;
  } else if ( key == 's') {
    saveImg();
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "mazal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
