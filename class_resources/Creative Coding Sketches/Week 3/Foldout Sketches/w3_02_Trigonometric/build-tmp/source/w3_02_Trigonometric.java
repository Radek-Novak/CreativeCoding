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

public class w3_02_Trigonometric extends PApplet {

/*
 * Creative Coding
 * Week 3, Foldout 02: Processing functions
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program illustrates the use of the Processing funcions:
 * sin, cos
 *
 * See the foldout associated with this sketch for more details
 * 
 */   

float ball_x, ball_y;
float center_x, center_y;
float radius;

public void setup() {
  size(800, 300);
  center_x = 120;
  center_y = height/2;
  radius = 100;
}


public void draw() {
  background(255);

  float radians = radians(frameCount);

  ball_x = center_x + radius*sin(radians);
  ball_y = center_y + radius*cos(radians);

  //circle
  fill(255);
  stroke(0);
  ellipse(center_x, center_y, radius*2, radius*2);

  //line between center to rotating ball
  stroke(180);
  line(center_x, center_y, ball_x, ball_y);

  //rotating ball
  fill(255);
  stroke(0);
  ellipse(ball_x, ball_y, 10, 10);

  //centerPoint
  fill(0);
  ellipse(center_x, center_y, 5, 5);

  //sin and cos curve
  for (int i=0; i<360; i++) {
    float rad = radians(i);
    stroke(255, 0, 0);
    point(250 +i, height/2+radius*sin(rad) );
    stroke(0, 0, 255);
    point(250 +i, height/2+radius*cos(rad) );
  }

  int angleForSinCosBall = frameCount%360;
  int increaseForSinCosBall = angleForSinCosBall;
  float radiansForSinCosBall= radians(angleForSinCosBall);
  float sin_ball_x = 250 + increaseForSinCosBall;
  float sin_ball_y = height/2 + radius*sin(radiansForSinCosBall);
  float cos_ball_x = 250 + increaseForSinCosBall;
  float cos_ball_y = height/2 + radius*cos(radiansForSinCosBall);

  //ball moves on sine curve
  noStroke();
  fill(255, 0, 0);
  ellipse(sin_ball_x, sin_ball_y, 5, 5);
  //ball moves on cosin curve
  fill(0, 0, 255);
  ellipse(cos_ball_x, cos_ball_y, 5, 5);

  //
  fill(255, 0, 0);
  rect(635, height/2, 30, radius*sin(radiansForSinCosBall) );
  fill(0, 0, 255);
  rect(670, height/2, 30, radius*cos(radiansForSinCosBall) );
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w3_02_Trigonometric" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
