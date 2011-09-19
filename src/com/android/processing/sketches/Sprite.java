package com.android.processing.sketches;

import processing.core.PApplet;
import processing.core.PImage;

public class Sprite extends PApplet {
	/**
	 * Sprite (Teddy) by James Patterson.
	 * 
	 * Demonstrates loading and displaying a transparent GIF image.
	 */

	PImage teddy;

	float xpos;
	float ypos;
	float drag = 30;

	public void setup() {
		size(200, 200);
		teddy = loadImage("teddy.gif");
		xpos = width / 2;
		ypos = height / 2;
	}

	public void draw() {
		background(102);

		float difx = mouseX - xpos - teddy.width / 2;
		if (abs(difx) > 1) {
			xpos = xpos + difx / drag;
			xpos = constrain(xpos, 0, width - teddy.width);
		}

		float dify = mouseY - ypos - teddy.height / 2;
		if (abs(dify) > 1) {
			ypos = ypos + dify / drag;
			ypos = constrain(ypos, 0, height - teddy.height);
		}

		// Display the sprite at the position xpos, ypos
		image(teddy, xpos, ypos);
	}
}
