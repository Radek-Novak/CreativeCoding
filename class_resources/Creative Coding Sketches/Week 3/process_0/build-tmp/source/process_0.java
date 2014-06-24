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

public class process_0 extends PApplet {

float[] elx;
float[] ely;
//float[] elr;
float[] dx;
float[] dy;
int num;
int sz; // size of circle


public void setup() {
	
	size(800, 800);
	
	//set bg
	fill(5, 7, 11);
	rect(0, 0, width, height);

	// max speed in pixels/frame/coordinate
	int speed = 2;

	// size of (invisible) circle
	sz = 50;

	// number of circles
	num = 19;
	
	// init arrays
	elx = new float[num];
	ely = new float[num];
	dx = new float[num];
	dy = new float[num];

	// init momenta & coordinates
	for (int i = 0; i < num; i++) {
		elx[i] = random(0, width);
		ely[i] = random(0, height);
		dx[i] = random(-speed, speed);
		dy[i] = random(-speed, speed);
	}
}

public void draw() {

	for (int i = 0; i < num; i++) {
		//ellipse(elx[i], ely[i], sz, sz);

		dx[i] = elx[i] < 0+sz ? -dx[i] : dx[i];
		dy[i] = ely[i] < 0+sz ? -dy[i] : dy[i];
		dx[i] = elx[i] > width-sz ? -dx[i] : dx[i];
		dy[i] = ely[i] > height-sz ? -dy[i] : dy[i];

		elx[i] += dx[i];
		ely[i] += dy[i];

		for (int j = i + 1; j < num; j++) {
			if (dist(elx[i], ely[i], elx[j], ely[j]) < 2*sz) {
				if (i % 2 == 0) {
					stroke(100,90,150, 100);
				} else {
					stroke(20,30,99, 100);
				}
				line(elx[i], ely[i], elx[j], ely[j]);
			}
		}
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "process_0" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
