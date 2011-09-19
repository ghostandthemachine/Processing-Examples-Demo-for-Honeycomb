package com.android.processing.sketches;

import processing.core.PApplet;

public class TriangleStrip extends PApplet {
	/**
	 * TRIANGLE_STRIP Mode by Ira Greenberg.
	 * 
	 * Generate a closed ring using vertex() function and
	 * beginShape(TRIANGLE_STRIP) mode. outerRad and innerRad variables control
	 * ring's outer/inner radii respectively. Trig functions generate ring.
	 */
	public void setup() {
		background(0);
		smooth();

		int x = width / 2;
		int y = height / 2;
		float outerRad = min(width, height) * 0.4f;
		float innerRad = outerRad * 0.6f;
		float px = 0, py = 0, angle = 0;
		float pts = 36;
		float rot = 360.0f / pts;

		beginShape(TRIANGLE_STRIP);
		for (int i = 0; i < pts; i++) {
			px = x + cos(radians(angle)) * outerRad;
			py = y + sin(radians(angle)) * outerRad;
			angle += rot;
			vertex(px, py);
			px = x + cos(radians(angle)) * innerRad;
			py = y + sin(radians(angle)) * innerRad;
			vertex(px, py);
			angle += rot;
		}
		endShape();
	}
}
