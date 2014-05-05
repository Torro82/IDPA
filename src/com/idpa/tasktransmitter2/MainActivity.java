package com.idpa.tasktransmitter2;

import java.util.ArrayList;
import com.idpa.tasktransmitter2.R;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
	
    static public ArrayList<String> NamesAndNumbers = new ArrayList<String>();
    static public ArrayList<String> Numbers = new ArrayList<String>();
    static public ArrayList<String> Date = new ArrayList<String>();
    static public ArrayList<String> Subject = new ArrayList<String>();
    static public ArrayList<String> Task = new ArrayList<String>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int size = HelperSharedPreferences.getSharedPreferencesInt(getApplicationContext(), "size", 0);
		int t_size = HelperSharedPreferences.getSharedPreferencesInt(getApplicationContext(), "T_size", 0);
		
		for(int i = 0; i < t_size; i++){
			Date.add(HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_date_"+i  , "Not exists"));
			Subject.add(HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_subject_"+i  , "Not exists"));
			Task.add(HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_task_"+i  , "Not exists"));
		}
		
		for(int i = 0; i < size; i++){
			NamesAndNumbers.add(HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_NAN_"+i  , "Not exists"));
			Numbers.add(HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_N_"+i  , "Not exists"));
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void sendMessage(View view) {
		if(Task.size()!=0 && Numbers.size()!=0) {
			String allTasks = ""; 
			for (int i = 0; i < Task.size(); i++) {
				int j = i+1;
				allTasks= (allTasks+Integer.toString(j)+". " + HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_date_"+i, "Nox exists")+" / "+ HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_subject_"+i, "Nox exists")+"\n"+ HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_task_"+i, "Nox exists")+"\n"+"\n");
			}
			SmsManager manager = SmsManager.getDefault();
			
			int array_size=Numbers.size();
			String[] numbers = new String[array_size];
			numbers = Numbers.toArray(numbers);
			for (int i = 0; i < array_size; i++) {
			manager.sendTextMessage(numbers[i], null, allTasks, null, null);
	        }
			Toast.makeText(getApplicationContext(), "sending "+array_size+" SMS...", Toast.LENGTH_SHORT).show();
		} 
		else {
			Toast.makeText(getApplicationContext(), "You don't have any tasks or contacts...", Toast.LENGTH_SHORT).show();
		}
	}
    public void addNumber(View view) {
        Intent intent = new Intent(this, AddNumber_Activity.class);
        startActivity(intent);
    }
    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivit.class);
        startActivity(intent);
    }
    public void showTasks(View view) {
        Intent intent = new Intent(this, ShowTasksActivity.class);
        startActivity(intent);
    }
	public void deleteTask(View view) {
		EditText number_to_delete = (EditText)findViewById(R.id.number_to_delete);
		if(number_to_delete.getText().toString().equals("0")||number_to_delete.getText().toString().equals("") ||number_to_delete.getText().toString().equals(null)){
			Toast.makeText(getApplicationContext(), "Please insert a task Index-Number!", Toast.LENGTH_SHORT).show();
		}
		else {
			int i = Integer.parseInt(number_to_delete.getText().toString())-1;
			MainActivity.Date.remove(i);
			MainActivity.Subject.remove(i);
			MainActivity.Task.remove(i);
			HelperSharedPreferences.clearSharedPreferences(getApplicationContext());
			for(int j=0;j<MainActivity.Date.size();j++) {
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_date_"+j, MainActivity.Date.get(j));
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_subject_"+j, MainActivity.Subject.get(j));
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_task_"+j, MainActivity.Task.get(j));
			}
			for (int j = 0; j < MainActivity.Numbers.size(); j++) {
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_N_"+j, MainActivity.Numbers.get(j));
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_NAN_"+j, MainActivity.NamesAndNumbers.get(j));
			}
			HelperSharedPreferences.putSharedPreferencesInt(getApplicationContext(),"size", MainActivity.Numbers.size());
			HelperSharedPreferences.putSharedPreferencesInt(getApplicationContext(),"T_size", MainActivity.Task.size());
			Toast.makeText(getApplicationContext(), "Task deleted...", Toast.LENGTH_SHORT).show();
		}
		number_to_delete.setText("");
	}
	public void deleteAllTasks(View view) {
		MainActivity.Date.clear();
		MainActivity.Subject.clear();
		MainActivity.Task.clear();
		HelperSharedPreferences.clearSharedPreferences(getApplicationContext());
		for(int i=0;i<MainActivity.Numbers.size();i++) {
			HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_N_"+i, MainActivity.Numbers.get(i));
			HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_NAN_"+i, MainActivity.NamesAndNumbers.get(i));
		}
		HelperSharedPreferences.putSharedPreferencesInt(getApplicationContext(),"size", MainActivity.Task.size());
		Toast.makeText(getApplicationContext(), "Deleting all tasks...", Toast.LENGTH_SHORT).show();
	}
}

