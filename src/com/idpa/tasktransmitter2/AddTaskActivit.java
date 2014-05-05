package com.idpa.tasktransmitter2;

import java.io.FileNotFoundException;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivit extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_task, menu);
		return true;
	}
	public void addATask (View view) throws FileNotFoundException  {
		EditText edit_date = (EditText)findViewById(R.id.edit_date);
		String date = edit_date.getText().toString();
		
		EditText edit_subject = (EditText)findViewById(R.id.edit_subject);
		String subject = edit_subject.getText().toString();
		
		EditText edit_task = (EditText)findViewById(R.id.edit_task);
		String task = edit_task.getText().toString();
		
		if(date.equals("")||subject.equals("")||task.equals("")) {
			Toast.makeText(getApplicationContext(), "Please fill all fields with correct information!", Toast.LENGTH_SHORT).show();
		}
		else {
			MainActivity.Date.add(date);
			MainActivity.Subject.add(subject);
			MainActivity.Task.add(task);
			
			for(int i=0;i<MainActivity.Date.size();i++) {
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_date_"+i, MainActivity.Date.get(i));
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_subject_"+i, MainActivity.Subject.get(i));
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_task_"+i, MainActivity.Task.get(i));
			}
			HelperSharedPreferences.putSharedPreferencesInt(getApplicationContext(),"T_size", MainActivity.Task.size());
			edit_date.setText("");
			edit_subject.setText("");
			edit_task.setText("");
			Toast.makeText(getApplicationContext(), "Task added!", Toast.LENGTH_SHORT).show();
		}
	}
}