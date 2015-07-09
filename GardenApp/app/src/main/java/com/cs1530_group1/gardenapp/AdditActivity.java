package com.cs1530_group1.gardenapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class AdditActivity extends ActionBarActivity {

    private static final String LOG_TAG = "AdditActivity";
    /**
     * editMode is needed so the multiple methods know whether it's a new species or edditing one
     */
    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addit);

        Intent intent = getIntent();
        String speciesName = intent.getStringExtra(Intent.EXTRA_TEXT);
        if(speciesName!=null){
            setupEditMode(speciesName);
        }
        Log.e(LOG_TAG, "this activity is not yet ready for release");



    }

    /**
     * seting up the edit mode involves filling the text fields with old data
     * @param speciesName the name of the species to be modified
     */
    protected void setupEditMode(String speciesName){
        editMode = true;
        Log.v(LOG_TAG, "we're editing" + speciesName);

    }

    /**
     *
     * @param id the id of the EditText
     * @param str the string to populate it with
     */
    protected void setText(int id, String str){
        EditText textbox = (EditText)findViewById(id);
        textbox.setText(str);
    }

    /**
     * disables a textbox
     * @param id the id to be disabled
     */
    protected void disableEditText(int id){
        EditText textbox = (EditText)findViewById(id);
        textbox.setEnabled(false);
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

    /**
     * called when the user presses cancel.
     * Should return to the list activity and make no changes
     * @param view unused
     */
    public void cancel(View view) {
        Log.e(LOG_TAG, "cancel is not yet implemented");
        Toast.makeText(getApplicationContext(), "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

    /**
     * makes the changes specified by the user.
     * sends user to the viewInfo activity
     * @param view unused
     */
    public void update(View view) {
        Log.e(LOG_TAG, "update is not yet implemented");
        Toast.makeText(getApplicationContext(),"This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

    /**
     * this should launch the color picker activity, so the user can pick the color to represent that plant on the gardenView
     * @param view unused
     */
    public void startColorPicker(View view) {
        Log.e(LOG_TAG, "colorPicker is not yet implemented");
        Toast.makeText(getApplicationContext(),"This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

    /**
     * let's the user pick the date used as a species plantdate
     * @param view unused
     */
    public void startDatePicker(View view) {
        Log.e(LOG_TAG, "datePicker is not yet implemented");
        Toast.makeText(getApplicationContext(),"This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
