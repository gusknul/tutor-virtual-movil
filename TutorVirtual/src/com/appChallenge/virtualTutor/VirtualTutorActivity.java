package com.appChallenge.virtualTutor;

import java.sql.Time;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appChallenge.virtualTutor.model.TimeWindow;
import com.appchalenge.tutorvirtual.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class VirtualTutorActivity extends Activity {

	private Spinner daySpinner;
	
	private TimePicker startTimePicker;
	
	private TimePicker endTimePicker;
	
	private Button generateSuggestedScheduleButton;
	
	private ListView timeWindowListView;
	
	public static final String EXTRA_ENROLLMENT = "enrollment";
	
	private String enrollment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_virtual_tutor);
		initComponents();
	}
	
	private void initComponents() {
		daySpinner = (Spinner) findViewById(R.id.spinner_day);
		startTimePicker = (TimePicker)findViewById(R.id.time_picker_start_time);
		endTimePicker = (TimePicker)findViewById(R.id.time_picker_end_time);
		
		final TimeWindowAdapter adapter = new TimeWindowAdapter(this, new ArrayList<TimeWindow>());
		
		timeWindowListView = (ListView) findViewById(R.id.list_view_time_window);
		timeWindowListView.setAdapter(adapter);
		timeWindowListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.remove(adapter.getItem(position));
				 return true;
			}
		});

		enrollment = getIntent().getStringExtra(EXTRA_ENROLLMENT);
	}
	
	public void insertTimeWindow(View view){
		int startHour = startTimePicker.getCurrentHour();
		int startMinutes = startTimePicker.getCurrentMinute();
		
		Time startTime = new Time(startHour, startMinutes, 0);
		
		int endHour = endTimePicker.getCurrentHour();
		int endMinutes = endTimePicker.getCurrentMinute();
		
		Time endTime = new Time(endHour, endMinutes, 0);
		
		String day = daySpinner.getSelectedItem().toString();
		TimeWindow timeWindow = new TimeWindow(day, startTime, endTime);
		
		TimeWindowAdapter adapter = (TimeWindowAdapter) timeWindowListView.getAdapter();
		
		for (int i = 0; i < adapter.getCount(); i++) {
			TimeWindow tw = adapter.getItem(i);
			
			if (timeWindow.getDay().equals(tw.getDay()) && timeWindow.overlap(tw)) {
				Toast.makeText(this, "El intervalo se traslapa con uno existente", Toast.LENGTH_LONG).show();
				return;
			}
		}
		
		adapter.add(timeWindow);
	}
	
	public void generateSuggestedSchedule(View view){
		
		JSONArray timeWindows = new JSONArray();
		TimeWindowAdapter adapter = (TimeWindowAdapter) timeWindowListView.getAdapter();
		
		for(int i = 0; i < adapter.getCount() ; i++){
			
			 try {
				JSONObject timeWindow = new JSONObject();
				timeWindow.put("day", adapter.getItem(i).getDay());
				timeWindow.put("start_time", adapter.getItem(i).getStartTime().getTime() / 1000);
				timeWindow.put("end_time", adapter.getItem(i).getEndTime().getTime() / 1000);
				timeWindows.put(timeWindow);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		
		String jsonTimeWindows = timeWindows.toString();
		Intent intent = new Intent(VirtualTutorActivity.this,SuggestedScheduleActivity.class);
		intent.putExtra(SuggestedScheduleActivity.EXTRA_JSON_TIME_WINDOWS, jsonTimeWindows);
		startActivity(intent);
	}

}
