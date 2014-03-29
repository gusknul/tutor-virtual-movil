package com.appChallenge.virtualTutor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.appchalenge.tutorvirtual.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SuggestedScheduleActivity extends Activity {

	public static final String EXTRA_JSON_TIME_WINDOWS = "jsonTimeWindows";
	private String jsonTimeWindows;
	private static final String URL = "http://192.168.229.118/subject.json";
	private ListView listViewOrdinary;
	private ListView listViewExtra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggested_schedule);
		jsonTimeWindows = getIntent().getStringExtra(EXTRA_JSON_TIME_WINDOWS);
		listViewExtra = (ListView) findViewById(R.id.list_view_extra);
		listViewOrdinary = (ListView) findViewById(R.id.list_view_ordinary);
		suggestedSchedule(jsonTimeWindows);
	}
	
	private void suggestedSchedule(String jsonTimeWindows){
		AsyncHttpClient client = new AsyncHttpClient();
		StringEntity entity = null;
		try {
			entity = new StringEntity(jsonTimeWindows);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.post(this, URL, entity, "application/json", new AsyncHttpResponseHandler(){
			@Override
			 public void onSuccess(String response){   
				try {
					fillSuggested(response);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			
			@Override
			public void onFailure(int statusCode, Throwable error,
					String content) {
				// TODO Auto-generated method stub
				if(statusCode == 0){
					Toast.makeText(SuggestedScheduleActivity.this, "El servidor no responde", Toast.LENGTH_SHORT).show();;
				}
					
			}
		});
		
	}
	
	
	public void fillSuggested (String response) throws JSONException{
		JSONObject suggestedSchedule = new JSONObject(response);
		JSONArray extraordinary = new JSONArray(suggestedSchedule.get("extraordinarios").toString());
		JSONArray ordinary = new JSONArray(suggestedSchedule.get("ordinarios").toString());
		ArrayList<String> subjectsExtra = new ArrayList<String>();
		ArrayList<String> subjectsOrdinary = new ArrayList<String>();
		
		for(int i = 0; i < extraordinary.length();i++){
			subjectsExtra.add(extraordinary.getString(i));
		}
		
		for(int j = 0; j < ordinary.length(); j++){
			String suggestedScheduleItem = "";
			JSONObject subjectOrdinary = new JSONObject(ordinary.getString(j));
			suggestedScheduleItem = suggestedScheduleItem + subjectOrdinary.getString("subject") + "\n" + subjectOrdinary.getString("teacher") + "\n";
			JSONArray schedule = new JSONArray(subjectOrdinary.get("schedule").toString());
			for(int k = 0 ; k < schedule.length(); k++){
				suggestedScheduleItem += schedule.getString(k)+ "\n";
			}
			
			subjectsOrdinary.add(suggestedScheduleItem);
		}
		
		ArrayAdapter<String> adapterExtra = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjectsExtra);
		ArrayAdapter<String> adapterOrdinary = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjectsOrdinary);
		
		listViewExtra.setAdapter(adapterExtra);
		listViewOrdinary.setAdapter(adapterOrdinary);
		
	}
	


}
