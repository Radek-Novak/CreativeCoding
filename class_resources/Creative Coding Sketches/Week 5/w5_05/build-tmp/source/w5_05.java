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

public class w5_05 extends PApplet {

/*
 * Creative Coding
 * Week 5, 05 - Text agents
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch creates a simple agent-based simulation using text and objects
 * The sketch reads in characters from the keyboard and dynamically creates
 * new objects for each character.
 *
 */
ArrayList<AniCharacter> aniChars;

PFont myFont;

int currentCount;

public void setup() {
  size(700, 700);
  myFont = loadFont("HelveticaNeue-UltraLight-200.vlw");
  textFont(myFont);
  //textMode(SHAPE);

  aniChars = new ArrayList<AniCharacter>();

  smooth(8); // enable antialiasing
}

public void draw() {
  background(255);

  for (int i = aniChars.size()-1; i >= 0; i--) {
    AniCharacter tempObj = aniChars.get(i);
    tempObj.run(); // run each char
  }
}

public void keyReleased() {
  if (8 == PApplet.parseInt(key) && aniChars.size() > 0) {
    println("c");
    aniChars.remove(aniChars.size()-1);
  }else{ 
    aniChars.add( new AniCharacter(random(width), random(height), key) );
  }
}
// AniCharacter class
//
// 
class AniCharacter {

  float x, y;
  float nOff_x;
  float nOff_y;
  float dynamic;
  float dynamic_c;
  float size_font;
  float size_shape;
  char letter;


  AniCharacter(float x_, float y_, char c_) {

    x = x_;
    y = y_;
    nOff_x = random(3000);
    nOff_y = random(3000);

    letter = c_;
    size_font = random(50, 200);
    size_shape = size_font + random(10, 50);
    dynamic_c = random(-0.1f, 0.1f);
  }

  public void run() {

    switch(letter) {
    case 'a':
      dynamic -= dynamic_c;
      break;
    case 'c':
      dynamic += dynamic_c;
      break;
    case 'e':
      dynamic += dynamic_c;
      break;
    }

    // speed of motion
    nOff_x += 0.0005f;
    nOff_y += 0.0003f;

    x = width*noise(nOff_x);
    y = height*noise(nOff_y);

    visual();
  }

  /*
   * visual
   * draw the char with special cases for some letters
   */
  public void visual() {

    textSize(size_font);
    float textWidth =  textWidth(letter);
    float ascent = textAscent() * 0.75f;

    switch(letter) {
    case 'a':

      fill(0);
      noStroke();
      text(letter, x-textWidth/2, y+ascent/2);

      stroke(0);
      noFill();
      pushMatrix();
      translate(x, y);
      rotate(dynamic);
      arc(0, 0, size_shape, size_shape, HALF_PI, PI+HALF_PI);
      popMatrix();
      break;

    case 'c':
      fill(0);
      noStroke();
      text(letter, x-textWidth/2, y+ascent/2);

      float tempx = x + size_shape*cos(dynamic*0.5f);
      float tempy = y + size_shape*sin(dynamic*0.5f);
      fill(255, 0, 0);
      stroke(255, 0, 0, 140);
      line(x, y, tempx, tempy);
      noStroke();
      ellipse(tempx, tempy, 10, 10);
      break;

    case 'e':
      rectMode(CENTER);
      fill(0);
      rect(x, y, textWidth, size_shape*sin( radians(dynamic) ));
      rectMode(CORNER);

      fill(255);
      noStroke();
      text(letter, x-textWidth/2, y+ascent/2);
      break;

    default:
      fill(0);
      noStroke();
      text(letter, x-textWidth/2, y+ascent/2);
      break;
    }

    //ellipse(x, y, 10, 10);
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w5_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
