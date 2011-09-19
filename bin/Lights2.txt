package com.android.processing.sketches;

import processing.core.PApplet;

public class Lights2 extends PApplet {
	/**
	 * Lights 2 by Simon Greenwold.
	 * 
	 * Display a box with three different kinds of lights.
	 */

	public void setup() {
		noStroke();
	}
	
	public String sketchRenderer() {
		return P3D;
	}

	public void draw() {
		background(0);
		lights();
		translate(width / 2, height / 2);

		// Orange point light on the right
		pointLight(150, 100, 0, // Color
				100, -150, 0); // Position

		// Blue directional light from the left
		directionalLight(0, 102, 255, // Color
				1, 0, 0); // The x-, y-, z-axis direction

		// Yellow spotlight from the front
		spotLight(255, 255, 109, // Color
				0, 40, 100, // Position
				0, -0.5f, -0.5f, // Direction
				PI / 2, 2); // Angle, concentration

		rotateY(map(mouseX, 0, width, 0, PI));
		rotateX(map(mouseY, 0, height, 0, PI));
		fill(255);
		box(150);
	}
}
