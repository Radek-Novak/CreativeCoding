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

public class w3_01_Array_and_List extends PApplet {

/*
 * Creative Coding
 * Week 3, Foldout 01: Arrays and Lists
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program introduces the array and list collections
 * See the foldout associated with this sketch for more details
 * 
 */   
 
// array declaration
float[] grayValueForArray;
int num;

// list declaration
FloatList grayValueForList;


public void setup() {
  size(400, 300);

  num = 3;

  //Initialise Array 
  grayValueForArray = new float[num];

  //Input values into the Array
  for (int i=0; i<num; i++) {
    grayValueForArray[i] = 80*i;
  }

  //Initialise list
  grayValueForList = new FloatList();
  
  //Input values into the List
  for (int i=0; i<num; i++) {
    grayValueForList.append(80*i);
  }
}

public void draw() {
  background(255);
  
  noStroke();
  
  //Use values from array 
  for (int i=0; i<num; i++) {
    fill( grayValueForArray[i] );
    ellipse(100+100*i, 100, 40, 40);
  }

  //Use values from array 
  for (int i=0; i<num; i++) {
    fill( grayValueForList.get(i) );
    ellipse(100+100*i, 200, 40, 40);
  }
  
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w3_01_Array_and_List" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
