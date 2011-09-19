package com.android.processing.sketches;

import processing.core.PApplet;
import processing.core.PImage;

public class BackgroundImage extends PApplet {

	/**
	 * Background Image.
	 * 
	 * This example presents the fastest way to load a background image into
	 * Processing. To load an image as the background, it must be the same width
	 * and height as the program.
	 */

	PImage bg;
	int a;

	public void setup() {
		frameRate(30);
		// The background image must be the same size as the parameters
		// into the size() method. In this program, the size of
		// "milan_rubbish.jpg"
		// is 200 x 200 pixels.
		bg = loadImage("milan_rubbish.jpg");
		bg.resize(width, height);
	}

	public void draw() {
		background(bg);

		a = (a + 1) % (width + 32);
		stroke(226, 204, 0);
		line(0, a, width, a - 26);
		line(0, a - 6, width, a - 32);
	}

}