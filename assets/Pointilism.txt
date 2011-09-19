package com.android.processing.sketches;

import processing.core.PApplet;
import processing.core.PImage;

public class Pointilism extends PApplet {
	/**
	 * Pointillism by Daniel Shiffman.
	 * 
	 * Mouse horizontal location controls size of dots. Creates a simple
	 * pointillist effect using ellipses colored according to pixels in an
	 * image.
	 * 
	 * Updated 27 February 2010.
	 */

	PImage img;

	int smallPoint = 2;
	int largePoint;
	int top, left;

	public void setup() {
		img = loadImage("eames.jpg");
		img.resize(width, height);
		// img = loadImage("sunflower.jpg"); // an alternative image
		noStroke();
		background(0);
		smooth();
		largePoint = min(width, height) / 10;
		// center the image on the screen
		left = (width - img.width) / 4;
		top = (height - img.height) / 4;
	}

	public void draw() { 
	  float pointillize = map(mouseX, 0, width, smallPoint, largePoint);
	  int x = (int)(random(img.width));
	  int y = (int)(random(img.height));
	  int pix = img.get(x, y);
	  fill(pix, 128);
	  ellipse(left + x, top + y, pointillize, pointillize);
	}
}
