package com.appchalenge.tutorvirtual;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VirtualTutorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_virtual_tutor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.virtual_tutor, menu);
		return true;
	}

}
