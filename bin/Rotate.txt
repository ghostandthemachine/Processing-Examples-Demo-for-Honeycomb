package com.android.processing.sketches;

import processing.core.PApplet;

public class Rotate extends PApplet{

	float angle;
	float jitter;

	public void setup() {
	  size(200, 200);
	  smooth();
	  noStroke();
	  fill(255);
	  rectMode(CENTER);
	  frameRate(30);
	}

	public void draw() {
	  background(0);

	  // during even-numbered seconds (0, 2, 4, 6...)
	  if (second() % 2 == 0) {  
	    jitter = random(-0.1f, 0.1f);
	  }
	  angle = angle + jitter;
	  float c = cos(angle);
	  translate(width/2, height/2);
	  rotate(c);
	  rect(0, 0, 250, 250);   
	}
}
