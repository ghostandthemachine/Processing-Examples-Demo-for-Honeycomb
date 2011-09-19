package com.android.processing.sketches;

import processing.core.PApplet;

public class Brightness extends PApplet {
	/**
	 * Brightness by Rusty Robison.
	 * 
	 * Brightness is the relative lightness or darkness of a color. Move the
	 * cursor vertically over each bar to alter its brightness.
	 * 
	 * Updated 28 February 2010.
	 */

	int barWidth = 5;
	int lastBar = -1;

	public void setup() {
		colorMode(HSB, 360, 100, height);
		noStroke();
		background(0);
	}

	public void draw() {
		int whichBar = (int) (motionX / barWidth);
		if (whichBar != lastBar) {
			int barX = whichBar * barWidth;
			fill(barX, 100, motionY);
			rect(barX, 0, barWidth, height);
			lastBar = whichBar;
		}
	}
}
