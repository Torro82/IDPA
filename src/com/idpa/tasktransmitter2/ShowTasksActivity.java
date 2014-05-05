package com.idpa.tasktransmitter2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTasksActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_tasks);
		
		TextView list = (TextView) findViewById(R.id.tasks);
		
		int size = MainActivity.Task.size();
		for (int i = 0; i < size; i++) {
			int j = i+1;
			list.setText(list.getText()+Integer.toString(j)+". " + HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_date_"+i, "Nox exists")+" / "+ HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_subject_"+i, "Nox exists")+"\n"+"     "+ HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_task_"+i, "Nox exists")+"\n"+"\n");
		}
		if(list.getText().equals("")) {
			Toast.makeText(getApplicationContext(), "You don't have any tasks...", Toast.LENGTH_SHORT).show();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_tasks, menu);
		return true;
	}

}
