/*
Copyright (c) 2014 Radek Novák
radek-novak.com
-------------------------

hell is other cells
--

element Introvert
shape:      circle
behavior:   move in line at constant speed
behavior:   bounce from canvas edges
behavior:   grow when not overlapped with another element
behavior:   shrink when overlapping

process
a picture is loaded to become a background
every element is positioned randomly and sent in random direction
color under the center of each element is shown in form of vertical stripe
width of each stripe is proportional to the radius of element

*/
PImage bgImg;
color[] pixelColors;
int scanLine;  // vertical position

float[] elX;
float[] elY;
float[] elDX;
float[] elDY;
float[] elR;

//float[] strpW;
color[] strpC;

float sum = 0;
int N = 20;

boolean showImg = false;
boolean showBalls = false;

float lastX = 0;

void setup() {
  size(800, 800);
  //background(0);
  bgImg = loadImage("img/starry_night.jpg");
  
  rectMode(CENTER);
  smooth(4);
  noStroke();
  //frameRate(20);

  elX = new float[N];
  elY = new float[N];
  elDX = new float[N];
  elDY = new float[N];
  elR = new float[N];

  strpC = new color[N];

  // init 
  for (int i = 0; i < N; i++) {
    elX[i] = random(0, width);
    elY[i] = random(0, height);
    elDX[i] = random(-1, 1);
    elDY[i] = random(-1, 1);
    elR[i] = random(-1, 1);
  }
}

void draw() {
  //background(0);
  if (showImg) {
    image(bgImg, 0, 0, width, height);
  }

  // count sum
  sum = 0;
  for (int i = 0; i < N; i++) {
    sum += elR[i];
  }

  // for each element:
  for (int i = 0; i < N; i++) {
    // draw circle
    if (showBalls) {
      fill(strpC[i]);
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
    //if (frameCount % 10 == 0) {    }
      elR[i]++;

    // shrink
    for (int j = i+1; j < N; j++) {
      if (dist(elX[i], elY[i], elX[j], elY[j]) <= (elR[i] + elR[j])/2 
          && elR[i] > 0 
          && elR[j] > 0){

          elR[i]-=2;
          elR[j]-=2;
      }
    }

    // get color
    strpC[i] = bgImg.get( (int) map(elX[i], 0, width, 0, bgImg.width),
                          (int) map(elY[i], 0, height, 0, bgImg.height));

    // compute strip width
    float strpW = ( elR[i] / (sum+1) ) * width;

    // paint strip 
    rectMode(CORNER);
    fill(strpC[i], 2);
    rect(lastX, 0, lastX + strpW, height);
    lastX = i < N - 1 ? lastX + strpW : 0;
    //rect(0, 0, width, height);
    rectMode(CENTER);
  }

}

void keyPressed() {
  if ( key == 'c') {
    showBalls = showBalls ? false : true;
  } 
}
