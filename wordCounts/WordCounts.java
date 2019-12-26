/**
 * 
 */
package wordCounts;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.IntDict;

public class WordCounts extends PApplet {
	// 
	// Setting definitions
	int display = 1;
	IntDict counts;
	int defaultColor = 255;
	int bkgColor = 0;
	int w = 1280;
	int h = 1024;
	ArrayList<Word> words;
	int threshold = 75;
	boolean isRecording = false;



	// Set PApplet handle name
	//
	public static void main(String[] args) {
		PApplet.main("wordCounts.WordCounts");
	}

	//
	// settings()
	//
	public void settings() {
		//		size(w,h);
		// Full screen mode
		fullScreen(display);

	}

	//
	// setup()
	//
	public void setup() {
		// Default background color
		background(bkgColor);
		stroke(defaultColor);
		reset();	
	}

	//
	// reset()
	//
	void reset()
	{
		counts = new IntDict();
		String[] lines = loadStrings("./res/bible.txt");
		String allwords = join(lines, "\n");
		words = new ArrayList<Word>();

		String[] tokens = splitTokens(allwords, "\n\" ,;.?!");

		// Or with a regex
		// String[] tokens = allwords.split("\\W+");

		//printArray(tokens);
		for (int i = 0; i <tokens.length; i++) {
			String word = tokens[i].toLowerCase();
			if (counts.hasKey(word)) {
				counts.increment(word);
			} else {
				counts.set(word, 1);
			}
		}

		String[] keys = counts.keyArray();


		for (String k : keys) { 
			int count = counts.get(k); 
			if (count>threshold)
			{
				float x = random(width); 
				float y = random(height);
				Word w = new Word(this, k, count, x, y, (int) random(200.0f,4000.0f));
				words.add(w);
			}
		}

	}

	//
	// draw()
	//
	public void draw() {
		background(bkgColor);
		stroke(defaultColor);

		//String[] keys = counts.keyArray();

		for (Word item : words)
		{
			if (!item.isAlive()) {
				words.remove(item);
				item = null;
			} else {
				if (item.count > threshold) {
					item.move();
					item.show();
				}
			}
		}

		if (isRecording) {
			saveFrame("./output/frames_#####.png");
		}
	}

	//
	// mousePressed() control event
	//
	public void mousePressed() {
		words = null;
		reset();
	}

	//
	// keyPressed() control event
	//
	public void keyPressed() {
		if (key == ' ') {
			reset();
		}

		if (key == 'r' || key == 'R') {
			isRecording = !isRecording;
		}
	}


	Boolean isForbiddenWord(String word)
	{
		return false; 
	}

}

