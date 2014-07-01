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

public class w5_02 extends PApplet {

/*
 * Creative Coding
 * Week 5, 02 - Digital Clock
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch shows how to use text in Processing
 * The sketch creates a digital clock that shows the current time in hours, minutes and seconds
 * Use the 'h', 'm' and 's' keys to enlarge the hours, minutes or seconds in the display.
 *
 */
PFont myFont;    // font data
char  selected;  // what is selected (h,m,s)
int   gap;       // gap between digits

int curHour;
int curMin;
int curSec;
int count = 0;
int rotated = 0;
int H_N = 15;
int[] lastCol = new int[H_N];
int Hz = H_N;
public void setup() {
  size(1024, 600);

  myFont = loadFont("Frutiger65-Bold-200.vlw");  // load the font from this sketch's data directory
  textFont(myFont);  // set the current font to myFont
  selected = '0';
  gap = 300;
  //noStroke();
  //rectMode(CENTER);
  //colors = new ArrayList<color>();
  for (int i = 0; i < H_N; i++) {
    lastCol[i] = color(random(255), random(255), random(255));;
  }
  frameRate(Hz);
}

public void draw() {
  background(0, 100, 200);
  fill(255);
  
  if (curSec != second()) {
    count = 0;
  } else {
    count++;
  }

  curSec = second();
  drawHour(hour(), 40, -gap, 0, lastCol);
  drawMinute(minute(), 40, 30*cos(count), 30*sin(count), count );
    
  drawSecond(curSec, Hz*20 - count * 20, gap, 0, 0);
  lastCol[frameCount % H_N] = color(random(255), random(255), random(255));
  
}

public void drawSecond(int numero, int sz, float offsetX, float offsetY, float rot) {
  String theText = str(numero); // convert numero to string
  textSize(sz); // set big font size

  float textW = textWidth(theText) * 0.5f;
  float textAsc = textAscent() * 0.375f;
  if (offsetX > 0) {
    fill(255, 255,255, map(sz, 0, Hz*20, 255, 0));
    
  }
  // draw text offset from the centre of the screen
  //rectMode(RADIUS);
  text(theText, width/2 - textW + offsetX, height/2 + textAsc + offsetY);

}

public void drawHour(int numero, int sz, float offsetX, float offsetY, int[] col) {
  String theText = str(numero); // convert numero to string
  float textW;
  float textAsc;

  for ( int i = H_N - 1; i > 0; i--) {
    textSize(i*sz);
    fill(lastCol[i]);
    textW = textWidth(theText) * 0.5f;
    textAsc = textAscent() * 0.375f;

    text(theText, width/2 - textW + offsetX, height/2 + textAsc + offsetY);
  }
}
public void drawMinute(int numero, int sz, float offsetX, float offsetY, float rot) {
  String theText = str(numero); // convert numero to string
  textSize(sz); // set big font size

  float textW = textWidth(theText) * 0.5f;
  float textAsc = textAscent() * 0.375f;
  fill(255, 255,255);
    
  // draw text offset from the centre of the screen
  text(theText, width/2 - textW + offsetX, height/2 + textAsc + offsetY);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w5_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
