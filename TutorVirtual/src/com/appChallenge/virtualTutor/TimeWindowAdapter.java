package com.appChallenge.virtualTutor;

import java.util.ArrayList;
import java.util.List;

import com.appChallenge.virtualTutor.model.TimeWindow;
import com.appchalenge.tutorvirtual.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * 
 * @author Maxima cohesión
 * Adapter de list view para estampas de tiempo disponibles
 *
 */
public class TimeWindowAdapter extends ArrayAdapter<TimeWindow> {

	private Context context;
	
	private ArrayList<TimeWindow> list;
	
	/**
	 * Constructor de la clase TimeWindowAdapter
	 * @param context
	 * @param list
	 */
	public TimeWindowAdapter(Context context, ArrayList<TimeWindow> list) {
		super(context, R.layout.item_time_window, list);
		this.context = context;
		this.list = list;
	}
	

	/**
	 * Metodo heredado de ArrayAdapter para lenar el listview
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.item_time_window, parent, false);
		
		TextView dayTextView = (TextView) rowView.findViewById(R.id.text_day);
		TextView startTimeTextView = (TextView) rowView.findViewById(R.id.text_start_time);
		TextView endTimeTextView = (TextView) rowView.findViewById(R.id.text_end_time);
		
		TimeWindow tw = list.get(position);
		
		dayTextView.setText(tw.getDay());
		startTimeTextView.setText(tw.getStartTime().toString());
		endTimeTextView.setText(tw.getEndTime().toString());

		return rowView;
	}
	
}
