/*
 * Creative Coding
 * Week 4, 01 - an interactive colour wheel picker
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This program draws an interactive colour selection wheel
 * Drag the colour circle around the hue wheel to change hue, change the distance
 * from the wheel to control brightness.
 *
 * Another colour circle is displayed showing the colour 180 degrees from the current colour
 * 
 */

// modified radek-novak.com


// colourHandle: the user interface element to changing colours over the wheel
// It has a postion and a size
//
float colorHandleX;
float colorHandleY;
float handleSize = 60;

float comHandX;
float comHandY;
// boolean isLocked
// the state of handle: when the color handle is pressed, 
// color hand is locked–released as the left mouse button is released 
//
boolean isLocked = false;

// Wheel radius: inner and outer
//
float innerR = 100; // inner
float outerR = 200; // outer
float outerR2 = outerR * 1.5; // limit of the handle's "pull" range

// current and complementry colour
float hueValue = 270;
float brightValue = 100;
float complementryHue = 90;
float satValue = 100;

// color showcase
int current = 0;
int MAX_COLORS = 10;
int clsz;

void setup() {

  size(800, 800);
  colorMode(HSB, 360, 100, 100); // use HSB colour mode, H=0->360, S=0->100, B=0->100
  strokeWeight(1);

  //colorHandleX = width/2+300;
  //colorHandleY = height/2;
  colorHandleX = 400;
  colorHandleY = 200;
  comHandX = 400;
  comHandY = 600;
  clsz =  width / MAX_COLORS;


}


void draw() {
  //println(comHandX, comHandY);
  //Since were using HSB colour mode this clears the display window to white
  //         H  S  B
  //background(0, 0, 100);
  fill(0, 0, 100);
  rect(0, 0, width, height - height / MAX_COLORS);

  // draw reference line at the 0/360 hue boundary
  stroke(0, 40);
  line(width/2 + innerR, height/2, width/2 + outerR * 1.5, height/2);

  //draw itten's color wheel - we'll use a QUAD_STRIP for this
  noStroke();
  for (int circ_i = 100; circ_i < 200; circ_i+=5) {

    beginShape(QUAD_STRIP);
    for (int i=0; i<=120; i++) {
      float angle = radians(3*i+90); // 10 x 36 degree steps
      fill(3*i, circ_i-100, brightValue);
      //stroke(3*i, circ_i-100, 100);

      //outside(top)
      vertex(width/2 + (circ_i+10)*sin(angle), height/2 + (circ_i+10)*cos(angle) );
      //inside(down)
      vertex(width/2 + circ_i*sin(angle), height/2 + circ_i*cos(angle) );
    }
    endShape(CLOSE);
  }


  // colour handle Position Update
  colorHandleUpdate();
  complementryHue = calculateCompHue(hueValue);

  //draw dotted line from center to colorhandle
  //dotLine(width/2, height/2, colorHandleX, colorHandleY, 40); 

  // draw brightness pick-wheel
  for (int i = (int) innerR; i > 0; i-=5) {
    //strokeWeight(3);
    //stroke(255/i);
    fill(0,0, innerR-i);
    ellipse(width/2, height/2, i, i);

  }
  // circle around
  noStroke(); 
  fill(0, 0, 75);
  ellipse(colorHandleX, colorHandleY, handleSize+10, handleSize+10);
  // color itself
  fill(complementryHue, satValue, brightValue);
  ellipse(colorHandleX, colorHandleY, handleSize, handleSize );


  //dotline from center to comHand
  //dotLine(width/2, height/2, comHandX, comHandY, 20); 


  //println("hueValue: "+hueValue + " + "+"comhue: "+complementryHue);
  // circle around
  noStroke(); 
  fill(0, 0, 75);
  ellipse(comHandX, comHandY, handleSize, handleSize);
  // color itself
  fill( hueValue, satValue, brightValue );
  ellipse(comHandX, comHandY, handleSize - 10, handleSize - 10);

  if (mousePressed && isWithinCircle(width/2, height/2, innerR )) {
    //println(dist(width/2, height/2, mouseX, mouseY));
    brightValue = 100 - dist(width/2, height/2, mouseX, mouseY);
  }
  // brightness picker
  stroke(0, 80, 100-brightValue);
  noFill();
  ellipse(width/2, height/2, 100 - brightValue, 100 - brightValue);

  noStroke();
}

/*
 * calculateCompHue
 *
 * Calculates the complimentary hue from the hue supplied
 */
float calculateCompHue(float hueValue) {

  // Calculate complimentary color with hueValue
  // The complimentary colour should be 180 degrees opposite the selected colour
  if (hueValue >= 180 && hueValue < 360) {
    return hueValue-180;
  }
  else 
    return hueValue+180;
}


/*
 * colorHandleUpdate
 *
 * Updates the position and orientation of the colour handle based on
 * mouse position when left mouse button is pressed.
 */
void colorHandleUpdate() {

  // isLocked will be true if we pressed the mouse down while over the handle
  if (isLocked) {

    // calculate angle of handle based on mouse position
    // atan2 value is in the range from pi to -pi
    float angle = atan2(mouseY-height/2, mouseX-width/2  );
    float distance = dist(mouseX, mouseY, width/2, height/2);
    float radius = constrain(distance, innerR, outerR); 
    colorHandleX = width/2  + radius * cos(angle);
    colorHandleY = height/2 + radius * sin(angle);

    hueValue = map (degrees(angle), -180, 180, 360, 0);

    // map distance from outer edge of the wheel to brightness
    //brightValue = map(radius, innerR, outerR, 0, 100);
    satValue = map(radius, innerR, outerR, 0, 100);

  //complementry color for colorHandle (comHand)
  float angleComHand = map( atan2(colorHandleX-width/2, colorHandleY-height/2), -PI, PI, TWO_PI, 0) + HALF_PI;
  float radiusComeHand = constrain(distance, innerR, outerR);;
  comHandX = width/2  + radiusComeHand * cos(angleComHand);
  comHandY = height/2 + radiusComeHand * sin(angleComHand);
  }
}


/*
 * isWithinCircle
 * boolean function that returns true if the mouse is within the circle with centre (x,y) radius r
 */
boolean isWithinCircle(float x, float y, float r) {
  float dista = dist(mouseX, mouseY, x, y);
  return (dista <= r);
}

/*
 * dotLine
 * draw a dotted line from (x1,y1) to (x2,y2)
 */
void dotLine(float x1, float y1, float x2, float y2, int dotDetail) {

  for (int i=0; i<=dotDetail; i++) {
    float dotDetailFloat = (float) dotDetail;
    float dotX = lerp(x1, x2, i/dotDetailFloat);
    float dotY = lerp(y1, y2, i/dotDetailFloat);
    strokeWeight(2);
    stroke(0, 0, 40, 10);
    point(dotX, dotY);
  }
}

/*
 * mousePressed
 * When mouse button is first pressed, check if the user has pressed over the colour handle
 * If so, set isLocked to true to lock manipulation of the handle
 *
 */
void mousePressed() {
  if (isWithinCircle(colorHandleX, colorHandleY, handleSize)) {
    isLocked = true;
  }
}

/*
 * mouseReleased
 * Unlock control of the handle
 *
 */
void mouseReleased() {
  isLocked = false;
  if (isWithinCircle(colorHandleX, colorHandleY, handleSize)) {
    saveColor();
  }
}

// save color
void saveColor() {
  fill(complementryHue, satValue, brightValue);
  rect(clsz * current, height - clsz, clsz, clsz);

  fill(hueValue, satValue, brightValue);
  rect(clsz * current + clsz / 4, height - clsz + clsz / 4, clsz/2, clsz/2);
  
//println(clsz * current, height - clsz, clsz * current + clsz, height);
  current = ++current >= MAX_COLORS ? 0 : current++;

}