package com.android.processing.sketches;

import processing.core.PApplet;

public class Lights1 extends PApplet {

	/**
	 * Lights 1.
	 * 
	 * Uses the default lights to show a simple box. The lights() function is
	 * used to turn on the default lighting.
	 */

	float spin = 0.0f;

	public void setup() {
		size(640, 360, P3D);
		noStroke();
	}
	
	public String sketchRenderer() {
		return P3D;
	}

	public void draw() {
		background(0);
		lights();

		spin += 0.01;

		pushMatrix();
		translate(width / 2, height / 2, 0);
		rotateX(PI / 9);
		rotateY(PI / 5 + spin);
		fill(200);
		box(150);
		popMatrix();
	}

}
