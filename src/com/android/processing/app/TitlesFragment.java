package com.android.processing.app;

import processing.core.PApplet;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.processing.sketches.Lights1;
import com.android.processing.sketches.Lights2;
import com.android.processing.sketches.Reflection;
import com.android.processing.sketches.Rotate;
import com.android.processing.sketches.Spot;

public class TitlesFragment extends ListFragment {
	boolean mDualPane;
	int mCurCheckPosition = 0;
	int mShownCheckPosition = -1;

	private String currentSketchName = new String("Lights 1");

	TextView currentSketchTitle;
	TextView title;

	private String getCurrentSketchName() {
		return currentSketchName;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Populate list with our static array of titles.
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, Titles.TITLES));
		

		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View detailsFrame = getActivity().findViewById(R.id.processing);
		mDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
			mShownCheckPosition = savedInstanceState.getInt("shownChoice", -1);
		}

		if (mDualPane) {
			// In dual-pane mode, the list view highlights the selected item.
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// Make sure our UI is in the correct state.
			showDetails(mCurCheckPosition);
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

		showDetails(position);
	}

	/**
	 * Helper function to show the details of a selected item, either by
	 * displaying a fragment in-place in the current UI, or starting a whole new
	 * activity in which it is displayed.
	 */
	void showDetails(int index) {
		mCurCheckPosition = index;

		if (mDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			getListView().setItemChecked(index, true);

			if (mShownCheckPosition != mCurCheckPosition) {
				// If we are not currently showing a fragment for the new
				// position, we need to create and install a new one.
				PApplet pa = getProcessingSketchByIndex(index);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.processing, pa);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				mShownCheckPosition = index;
			}

		}
	}

	private PApplet getProcessingSketchByIndex(int index) {
		PApplet pa = null;
		switch (index) {
		case 0:
			pa = new Lights1();
			currentSketchName = "Lights 1";
			break;
		case 1:
			pa = new Lights2();
			currentSketchName = "Lights 2";
			break;
		case 2:
			pa = new Reflection();
			currentSketchName = "Reflection";
			break;
		case 3:
			pa = new Rotate();
			currentSketchName = "Rotate";
			break;
		case 4:
			pa = new Spot();
			currentSketchName = "Spot";
			break;
		case 5:
			break;
		default:
			break;
		}

		currentSketchTitle.setText("Current Sketch: " + getCurrentSketchName());

		return pa;
	}
}
