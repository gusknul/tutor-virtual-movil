package com.appChallenge.virtualTutor;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.appchalenge.tutorvirtual.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 
 * @author Maxima cohesion
 *
 */
public class Login extends Activity {

	private Button login;
	private EditText enrollment;
	private EditText password;
	private static final String MESSAGE_EMPTY = "Es necesario llenar todos los campos";
	private static final String URL = "http://192.168.229.26/Aplicaciones/tutor_virtual/api_v1/login";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initComponents();
		login();
	}

	private void initComponents() {
		login = (Button) findViewById(R.id.button_login);
		enrollment = (EditText) findViewById(R.id.edit_text_enrollment);
		password = (EditText) findViewById(R.id.edit_text_password);
	}


	private void login() {
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if( enrollment.getText().toString().equals("") || password.getText().toString().equals(""))
					Toast.makeText(Login.this,MESSAGE_EMPTY , Toast.LENGTH_SHORT).show();
				else  validateUser();
			}
		});
	}
	
	
	private void validateUser(){
		JSONObject data = new JSONObject();
		try {
			data.put("enrollment", enrollment.getText().toString());
			data.put("password", password.getText().toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        StringEntity entity = null;
		try {
			entity = new StringEntity(data.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(this, URL, entity, "application/json", new AsyncHttpResponseHandler(){
			@Override
			 public void onSuccess(String response){
				
						Intent intent = new Intent(Login.this,VirtualTutorActivity.class);
						intent.putExtra(VirtualTutorActivity.EXTRA_ENROLLMENT, enrollment.getText().toString());
						startActivity(intent);
				
			 }
			
			@Override
			public void onFailure(int statusCode, Throwable error,
					String content) {
				// TODO Auto-generated method stub
				if(statusCode == 401){
					Toast.makeText(Login.this, "Matricula o contraseña incorrecta, intentelo de nuevo", Toast.LENGTH_SHORT).show();
				}
				if(statusCode == 0){
					Toast.makeText(Login.this, "El servidor no responde", Toast.LENGTH_SHORT).show();
					Log.i("status code", String.valueOf(statusCode));
				}
					
			}
		});
	}
}
