package com.idpa.tasktransmitter2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Show_Contacts_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_contacts_);
		
		TextView list = (TextView) findViewById(R.id.name_number_list);
		
		int size = MainActivity.NamesAndNumbers.size();
		for (int i = 0; i < size; i++) {
			int j = i+1;
			list.setText(list.getText()+Integer.toString(j)+". " + HelperSharedPreferences.getSharedPreferencesString(getApplicationContext(),"index_NAN_"+i, "Not exists")+"\n");
		}
		if(list.getText().equals("")) {
			Toast.makeText(getApplicationContext(), "You don't have any contacts...", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show__contacts_, menu);
		return true;
	}
}