package com.appChallenge.virtualTutor;

import com.appchalenge.tutorvirtual.R;
import com.appchalenge.tutorvirtual.R.layout;
import com.appchalenge.tutorvirtual.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SuggestedScheduleActivity extends Activity {

	public static final String EXTRA_JSON_TIME_WINDOWS = "jsonTimeWindows";
	String jsonTimeWindows;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggested_schedule);
		jsonTimeWindows = getIntent().getStringExtra(EXTRA_JSON_TIME_WINDOWS);		
	}


}
