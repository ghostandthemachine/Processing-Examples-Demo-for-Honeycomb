package com.android.processing.app;

//import com.droidgraph.scene.DGScene;
//
//import processing.core.PApplet;
//import android.view.MotionEvent;
//
//public class PFrag extends PApplet {
//
//
//	int pointerCount = 0;
//	DGScene scene;
//
//	@Override
//	public void setup() {
//
//		scene = new DGScene(this);
//		scene.setBackground(255, 255, 255, 255);
//		
//		XoomMenu menu = new XoomMenu(this);
//		
//		menu.addTab(new XoomTab(menu, "test one"));
//
//		menu.addTab(new XoomTab(menu, "test two"));
//
//		menu.addTab(new XoomTab(menu, "test three"));
//
//		menu.addTab(new XoomTab(menu, "test four"));
//		
//		menu.buildMenu();
//		menu.setTranslation(0, 0);
//		
//		scene.add(menu);
//
//		
//	}
//
//	@Override
//	public String sketchRenderer() {
//		return P3D;
//	}
//
//	@Override
//	public void draw() {
//		scene.draw();
//		
//	}
//
//	static public void main(String args[]) {
//		PApplet.main(new String[] { "XoomTabMenuExample" });
//	}
//
//	@Override
//	public boolean surfaceTouchEvent(MotionEvent event) {
//		scene.handleMotionEvent(event);
//		return super.surfaceTouchEvent(event);
//	}
//
//}


import processing.core.PApplet;
import android.os.Bundle;


public class PFrag extends PApplet {
	/**
	 * Rotate. 
	 * 
	 * Rotating a square around the Z axis. To get the results
	 * you expect, send the rotate function angle parameters that are
	 * values between 0 and PI*2 (TWO_PI which is roughly 6.28). If you prefer to 
	 * think about angles as degrees (0-360), you can use the radians() 
	 * method to convert your values. For example: scale(radians(90))
	 * is identical to the statement scale(PI/2). 
	 */

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
	  rect(0, 0, 220, 220);   
	}

    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static PFrag newInstance(int index) {
    	PFrag f = new PFrag();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return 0;
    }
}

