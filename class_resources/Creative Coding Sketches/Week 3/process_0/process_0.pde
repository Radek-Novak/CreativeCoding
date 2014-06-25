float[] elx;
float[] ely;
float[] dx;
float[] dy;
int num;
int sz; // size of circle


void setup() {
	
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

void draw() {

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