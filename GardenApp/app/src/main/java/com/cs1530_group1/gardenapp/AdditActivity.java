package com.cs1530_group1.gardenapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class AdditActivity extends ActionBarActivity {

    private static final String LOG_TAG = "AdditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cancel(View view) {
        Log.e(LOG_TAG, "cancel is not yet implemented");
        Toast.makeText(getApplicationContext(), "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        Log.e(LOG_TAG, "update is not yet implemented");
        Toast.makeText(getApplicationContext(),"This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

    public void startColorPicker(View view) {
        Log.e(LOG_TAG, "colorPicker is not yet implemented");
        Toast.makeText(getApplicationContext(),"This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

    public void startDatePicker(View view) {
        Log.e(LOG_TAG, "datePicker is not yet implemented");
        Toast.makeText(getApplicationContext(),"This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}