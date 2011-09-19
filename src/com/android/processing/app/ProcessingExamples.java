/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.processing.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import processing.core.PApplet;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.processing.sketches.BackgroundImage;
import com.android.processing.sketches.BezierEllipse;
import com.android.processing.sketches.Brightness;
import com.android.processing.sketches.ColorWheel;
import com.android.processing.sketches.Lights1;
import com.android.processing.sketches.Lights2;
import com.android.processing.sketches.Metaball;
import com.android.processing.sketches.Plasma;
import com.android.processing.sketches.Pointilism;
import com.android.processing.sketches.Reflection;
import com.android.processing.sketches.Rotate;
import com.android.processing.sketches.Spot;
import com.android.processing.sketches.Sprite;
import com.android.processing.sketches.Transparency;
import com.android.processing.sketches.TriangleStrip;

/**
 * Demonstration of using fragments to implement different activity layouts.
 * This sample provides a different layout (and activity flow) when run in
 * landscape.
 */
public class ProcessingExamples extends Activity {

	private final static String TAG = "ProcessingExamples: ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.processing_examples_layout);
	}

	public static class TitlesFragment extends ListFragment {
		boolean mDualPane;
		int mCurCheckPosition = 0;
		int mShownCheckPosition = -1;
		private int lastPosition = -1;
		TogglePair tp = new TogglePair(0, 0);

		PApplet currentSketch;

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			// Populate list with our static array of titles.
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_activated_1,
					Titles.TITLES));

			// Check to see if we have a frame in which to embed the details
			// fragment directly in the containing UI.
			View detailsFrame = getActivity().findViewById(R.id.processing);
			mDualPane = detailsFrame != null
					&& detailsFrame.getVisibility() == View.VISIBLE;

			if (savedInstanceState != null) {
				// Restore last state for checked position.
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
				mShownCheckPosition = savedInstanceState.getInt("shownChoice",
						-1);
			}

			if (mDualPane) {
				// In dual-pane mode, the list view highlights the selected
				// item.
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				// Make sure our UI is in the correct state.

				showSketch(mCurCheckPosition);
			}
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("curChoice", mCurCheckPosition);
			outState.putInt("shownChoice", mShownCheckPosition);
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			Log.d(TAG, "pos:" + tp.pos + " hits:" + tp.hits);
			if (tp.pos == position) {
				tp.hits++;
				switch (tp.hits) {
				case 2:
					Log.d(TAG, "case 1:    pos:" + tp.pos + " hits:" + tp.hits);
					showSketchText(position, false);
					break;
				case 3:
					Log.d(TAG, "case 2:    pos:" + tp.pos + " hits:" + tp.hits);
					showSketch(position);
					tp.hits = 1;
					break;
				}
			} else {
				Log.d(TAG, "else:    pos:" + tp.pos + " hits:" + tp.hits);
				tp.pos = position;
				tp.hits = 1;
				showSketch(position);
			}
			lastPosition = position;
		}

		private void showSketchText(int index, boolean transparent) {
			String sketchName = Titles.TITLES[index] + ".txt";
			StringBuilder sketchToText = new StringBuilder();
			try {
				InputStream f = this.getActivity().getAssets().open(sketchName);
				BufferedReader buf = new BufferedReader(
						new InputStreamReader(f));

				String s = new String();
				// just reading each line and pass it on the debugger
				while ((s = buf.readLine()) != null) {
					sketchToText.append("\n");
					sketchToText.append(s);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SketchText st = new SketchText(sketchToText.toString(), transparent);
			// Execute a transaction, replacing any existing fragment
			// with this one inside the frame.
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.processing, st);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.commit();
			mShownCheckPosition = index;
		}

		/**
		 * Helper function to show the details of a selected item, either by
		 * displaying a fragment in-place in the current UI, or starting a whole
		 * new activity in which it is displayed.
		 */
		void showSketch(int index) {
			mCurCheckPosition = index;

			if (mDualPane) {
				// We can display everything in-place with fragments, so update
				// the list to highlight the selected item and show the data.
				getListView().setItemChecked(index, true);

				// if (mShownCheckPosition != mCurCheckPosition) {
				// If we are not currently showing a fragment for the new
				// position, we need to create and install a new one.
				PApplet pa = getProcessingSketchByIndex(index);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.processing, pa);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.commit();
				mShownCheckPosition = index;
				// }

			} else {
				// Otherwise we need to launch a new activity to display
				// the dialog fragment with selected text.
				// Intent intent = new Intent();
				// intent.setClass(getActivity(), DetailsActivity.class);
				// intent.putExtra("index", index);
				// startActivity(intent);
			}
		}

		private PApplet getProcessingSketchByIndex(int index) {
			PApplet pa = null;
			switch (index) {
			case 0:
				pa = new Lights1();
				break;
			case 1:
				pa = new Lights2();
				break;
			case 2:
				pa = new Reflection();
				break;
			case 3:
				pa = new Rotate();
				break;
			case 4:
				pa = new Spot();
				break;
			case 5:
				pa = new Brightness();
				break;
			case 6:
				pa = new ColorWheel();
				break;
			case 7:
				pa = new BezierEllipse();
				break;
			case 8:
				pa = new TriangleStrip();
				break;
			case 9:
				pa = new BackgroundImage();
				break;
			case 10:
				pa = new Pointilism();
				break;
			case 11:
				pa = new Sprite();
				break;
			case 12:
				pa = new Transparency();
				break;
			case 13:
				pa = new Metaball();
				break;
			case 14:
				pa = new Plasma();
				break;
			default:
				break;
			}
			currentSketch = pa;
			return currentSketch;
		}

		class TogglePair {
			public int pos = 0;
			public int hits = 0;

			public TogglePair(int i, int h) {
				pos = i;
				hits = h;
			}
		}
	}

}
