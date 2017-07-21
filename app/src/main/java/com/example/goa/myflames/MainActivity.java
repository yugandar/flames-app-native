package com.example.goa.myflames;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private String getFlameType() {
		String result;
		String name1 = getStringFromEditText((TextView) findViewById(R.id.editText1));
		String name2 = getStringFromEditText((TextView) findViewById(R.id.editText2));
		Flames flames = new Flames();
		result = flames.evaluateFlames(name1, name2);
		
		StringBuilder builder = new StringBuilder();
		if (result != null && result.length() > 0){
			result = builder.append("Relationship Type: ").append(result).toString();
		}
		return result;
	}

	private String getStringFromEditText(TextView et) {
		String value = "";
		if (et != null) {
			value = et.getText().toString();
		}
		return value;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void doFlames(View view) {
		String result = getFlameType();
		TextView resultView = (TextView) findViewById(R.id.textView3);
		resultView.setText(result);
	}

	public void clearAll(View view) {
		EditText et = (EditText) findViewById(R.id.editText1);
		et.setText("");
		et = (EditText) findViewById(R.id.editText2);
		et.setText("");

		TextView tv = (TextView) findViewById(R.id.textView3);
		tv.setText("");

	}
}
