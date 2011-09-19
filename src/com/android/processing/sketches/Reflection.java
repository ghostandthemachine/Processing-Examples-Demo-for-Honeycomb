package com.android.processing.sketches;

import processing.core.PApplet;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Reflection extends PApplet {
	/**
	 * Reflection 
	 * by Simon Greenwold. 
	 * 
	 * Vary the specular reflection component of a material
	 * with the horizontal position of the mouse. 
	 */

	public void setup() {
	  size(640, 360, P3D);
	  noStroke();
	  colorMode(RGB, 1);
	  fill(0.4f);
	}
	
	public String sketchRenderer() {
		return P3D;
	}

	public void draw() {
	  background(0);
	  translate(width / 2, height / 2);
	  // Set the specular color of lights that follow
	  lightSpecular(1, 1, 1);
	  directionalLight(0.8f, 0.8f, 0.8f, 0, 0, -1);
	  float s = motionX / width;
	  specular(s, s, s);
	  sphere(120);
	}

}
