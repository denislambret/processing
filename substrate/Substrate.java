package substrate;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

//Substrate Watercolor
//j.tarbell   June, 2004
//Albuquerque, New Mexico
//complexification.net

//Processing 0085 Beta syntax update
//j.tarbell   April, 2005
public class Substrate extends PApplet {
	int dimx = 2550;
	int dimy = 1440;
	int num = 0;
	int maxnum = 80;

	//grid of cracks
	int[] cgrid;
	Crack[] cracks;

	public static void main(String[] args) {
		PApplet.main("substrate.Substrate");
	}

	//color parameters
	int maxpal = 255;
	int numpal = 0;
	int[] goodcolor = new int[maxpal];

	//sand painters
	SandPainter[] sands;

	//MAIN METHODS ---------------------------------------------
	public void settings()
	{
		size(dimx,dimy,P2D);
	}
	
	public void setup() {
		
		background(255);
		//takecolor("./res/pollockshimmering.gif");
		takecolor("./res/yellow_palette.gif");

		cgrid = new int[dimx*dimy];
		cracks = new Crack[maxnum];

		begin();  
	}

	public void draw() {
		// crack all cracks
		for (int n=0;n<num;n++) {
			cracks[n].move();
		}
	}

	public void mousePressed() {
		begin();
	}


	//METHODS --------------------------------------------------

	void makeCrack() {
		if (num<maxnum) {
			// make a new crack instance
			cracks[num] = new Crack(this);
			num++;
		}
	}


	void begin() {
		// erase crack grid
		for (int y=0;y<dimy;y++) {
			for (int x=0;x<dimx;x++) {
				cgrid[y*dimx+x] = 10001;
			}
		}
		// make random crack seeds
		for (int k=0;k<16;k++) {
			int i = (int) (random(dimx*dimy-1));
			cgrid[i] = (int) (random(360.0f));
		}

		// make just three cracks
		num=0;
		for (int k=0;k<5;k++) {
			makeCrack();
		}
		background(255);
	}



	//COLOR METHODS ----------------------------------------------------------------

	int somecolor() {
		// pick some random good color
		return goodcolor[(int) (random(numpal))];
	}

	void takecolor(String fn) {
		PImage b;
		b = loadImage(fn);
		image(b,0,0);

		for (int x=0;x<b.width;x++){
			for (int y=0;y<b.height;y++) {
				int c = get(x,y);
				boolean exists = false;
				for (int n=0;n<numpal;n++) {
					if (c==goodcolor[n]) {
						exists = true;
						break;
					}
				}
				if (!exists) {
					// add color to pal
					if (numpal<maxpal) {
						goodcolor[numpal] = c;
						numpal++;
					}
				}
			}
		}
	}


	class Crack {
		PApplet p;
		float x, y;
		float t;    // direction of travel in degrees
		
		// sand painter
		SandPainter sp;

		Crack(PApplet p) {
			// find placement along existing crack
			this.p = p;
			findStart();
			sp = new SandPainter(p);
		}

		void findStart() {
			// pick random point
			int px=0;
			int py=0;

			// shift until crack is found
			boolean found=false;
			int timeout = 0;
			while ((!found) || (timeout++ > 1000)) {
				px = (int) (p.random(dimx));
				py = (int) (p.random(dimy));
				if (cgrid[py*dimx+px]<10000) {
					found=true;
				}
			}

			if (found) {
				// start crack
				int a = cgrid[py*dimx+px];
				if (p.random(100)<50) {
					a-=90 + (int) (p.random(-5,5.1f));
				} else {
					a+=90 + (int) (p.random(-2,2.1f));
				}
				startCrack(px,py,a);
			}
		}

		void startCrack(int X, int Y, int T) {
			x=X;
			y=Y;
			t=T;//%360;
			x+=0.61*cos(t * PConstants.PI / 180);
			y+=0.61*sin(t * PConstants.PI / 180);  
		}


		void move() {
			// continue cracking
			x += 0.42f * cos(t * PConstants.PI / 180);
			y += 0.42f * sin(t * PConstants.PI / 180); 

			// bound check
			float z = 0.33f;
			int cx = (int) (x + p.random(-z,z));  // add fuzz
			int cy = (int) (y + p.random(-z,z));

			// draw sand painter
			regionColor();

			// draw black crack
			stroke(0,85);
			point(x+random(-z,z),y+random(-z,z));


			if ((cx>=0) && (cx<dimx) && (cy>=0) && (cy<dimy)) {
				// safe to check
				if ((cgrid[cy*dimx+cx]>10000) || (abs(cgrid[cy*dimx+cx]-t)<5)) {
					// continue cracking
					cgrid[cy*dimx+cx] = (int) (t);
				} else if (abs(cgrid[cy*dimx+cx]-t)>2) {
					// crack encountered (not self), stop cracking
					findStart();
					makeCrack();
				}
			} else {
				// out of bounds, stop cracking
				findStart();
				makeCrack();
			}
		}

		void regionColor() {
			// start checking one step away
			float rx=x;
			float ry=y;
			boolean openspace=true;

			// find extents of open space
			while (openspace) {
				// move perpendicular to crack
				rx += 0.81f * sin(t * PConstants.PI/180);
				ry -= 0.81f * cos(t * PConstants.PI/180);
				int cx = (int) (rx);
				int cy = (int) (ry);
				if ((cx>=0) && (cx<dimx) && (cy>=0) && (cy<dimy)) {
					// safe to check
					if (cgrid[cy*dimx+cx]>10000) {
						// space is open
					} else {
						openspace=false;
					}
				} else {
					openspace=false;
				}
			}
			// draw sand painter
			sp.render(rx,ry,x,y);
		}
	}

	class SandPainter {
		PApplet p;
		int c;
		float g;

		SandPainter(PApplet p) {
			this.p = p;
			c = somecolor();
			g = p.random(0.01f,0.1f);
		}

		void render(float x, float y, float ox, float oy) {
			// modulate gain
			g += p.random(-0.050f,0.050f);
			float maxg = 2.0f;
			if (g < 0) g = 0;
			if (g>maxg) g = maxg;

			// calculate grains by distance
			//int grains = int(sqrt((ox-x)*(ox-x)+(oy-y)*(oy-y)));
			int grains = 64;

			// lay down grains of sand (transparent pixels)
			float w = g/(grains-1);
			for (int i = 0;i < grains;i++) {
				float a = 0.1f - i / (grains*10.0f);
				p.stroke(p.red(c),p.green(c),p.blue(c),a * 256);
				p.point((float) (ox + (x-ox) * Math.sin( Math.sin(i * w))), (float) (oy + (y-oy) * Math.sin( Math.sin(i * w))));
			}
		}
	}
}