package com.idpa.tasktransmitter2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddNumber_Activity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_number);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_number_, menu);
		return true;
	}
	public void addNumber(View view) {
		EditText textfeld_name = (EditText)findViewById(R.id.text_name);
		String name_to_add = textfeld_name.getText().toString();
		
		EditText textfeld_number = (EditText)findViewById(R.id.text_add_number);
		String number_to_add = textfeld_number.getText().toString();
		
		if(name_to_add.equals("") || number_to_add.equals("")) {
			Toast.makeText(getApplicationContext(), "Please insert name and number!", Toast.LENGTH_SHORT).show();
		}
		else {
			String putTogether = name_to_add + " / " + number_to_add;
			MainActivity.NamesAndNumbers.add(putTogether);
			MainActivity.Numbers.add(number_to_add);
			textfeld_name.setText("");
			textfeld_number.setText("");
			Toast.makeText(getApplicationContext(), "Number sucessfully added! ", Toast.LENGTH_SHORT).show();
			HelperSharedPreferences.clearSharedPreferences(getApplicationContext());
			for (int i = 0; i < MainActivity.Numbers.size(); i++) {
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_N_"+i, MainActivity.Numbers.get(i));
			}
			
			for (int i = 0; i < MainActivity.NamesAndNumbers.size(); i++) {
				HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_NAN_"+i, MainActivity.NamesAndNumbers.get(i));
			}
			HelperSharedPreferences.putSharedPreferencesInt(getApplicationContext(),"size", MainActivity.Numbers.size());
		}
	}
	public void showContacts(View view) {
        Intent intent = new Intent(this, Show_Contacts_Activity.class);
        startActivity(intent);
	}
	public void deleteContact(View view) {
		EditText number_to_delete = (EditText)findViewById(R.id.number_to_delete);
		if(number_to_delete.getText().toString().equals("0")||number_to_delete.getText().toString().equals("") ||number_to_delete.getText().toString().equals(null)){
			Toast.makeText(getApplicationContext(), "Please insert a contact Index-Number!", Toast.LENGTH_SHORT).show();
		}
		else {
			int i = Integer.parseInt(number_to_delete.getText().toString())-1;
			MainActivity.NamesAndNumbers.remove(i);
			MainActivity.Numbers.remove(i);
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
			Toast.makeText(getApplicationContext(), "Contact deleted...", Toast.LENGTH_SHORT).show();
		}
		number_to_delete.setText("");
	}
	public void deleteAllContacts(View view) {
		MainActivity.NamesAndNumbers.clear();
		MainActivity.Numbers.clear();
		HelperSharedPreferences.clearSharedPreferences(getApplicationContext());
		for(int i=0;i<MainActivity.Date.size();i++) {
			HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_date_"+i, MainActivity.Date.get(i));
			HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_subject_"+i, MainActivity.Subject.get(i));
			HelperSharedPreferences.putSharedPreferencesString(getApplicationContext(),"index_task_"+i, MainActivity.Task.get(i));
		}
		HelperSharedPreferences.putSharedPreferencesInt(getApplicationContext(),"T_size", MainActivity.Task.size());
		Toast.makeText(getApplicationContext(), "Deleting all contacts...", Toast.LENGTH_SHORT).show();
	}
}
