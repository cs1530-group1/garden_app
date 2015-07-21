package com.cs1530_group1.gardenapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;

/**
 * This activity handles both the adding of a new plant, and the editing of an existing plant.
 *
 * @author Charles Smith <cas275@pitt.edu>
 *
 */

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
        //if speciesName is not null, then we received a plant to edit
        if(speciesName!=null){
            setupEditMode(speciesName); //edit mode is slightly different than add mode
        }
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    /**
     * seting up the edit mode involves filling the text fields with old data
     * @param speciesName the name of the species to be modified
     */
    protected void setupEditMode(String speciesName){

        editMode = true;
        Log.v(LOG_TAG, "we're editing" + speciesName);

        App app = (App)getApplication();
        Garden garden = app.getGarden();

        disableEditText(R.id.species_name_box);  //can't change this if  you're editing

        setText(R.id.species_name_box, speciesName);  //sets the species name
        setText(R.id.description_box, garden.getDescription(speciesName));
        setText(R.id.size_box, garden.getSize(speciesName) + "");
        setText(R.id.sun_box, garden.getSunLevel(speciesName));

        String type = garden.getSpeciesType(speciesName);
        selectTypeRadio(type);


    }



    /**
     * checks the correct radio based on the string type
     * @param type the string to compare to the buttons
     */
    protected void selectTypeRadio(String type){
        if(type.equalsIgnoreCase("annual")){
            setRadioActive(R.id.radio_annual);
        }
        else if(type.equalsIgnoreCase("perennial")){
            setRadioActive(R.id.radio_perennial);
        }
        else if(type.equalsIgnoreCase("tree")){
            setRadioActive(R.id.radio_tree);
        }
        else{
            Log.w(LOG_TAG,"unexpected species type: " + type);
        }
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

    /**
     * set the radio button at id as checked
     * @param id the id to mark checked
     */
    protected void setRadioActive(int id){
        RadioButton button = (RadioButton)findViewById(id);
        button.setChecked(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return false;  //no menu
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
        Intent intent = new Intent(getApplicationContext(),SpeciesListActivity.class);
        startActivity(intent);
    }

    /**
     * makes the changes specified by the user.
     * sends user to the viewInfo activity
     * @param view unused
     */
    public void update(View view) {
        Garden garden = ((App)getApplication()).getGarden();
        String speciesName = getEditTextText(R.id.species_name_box);
        if(!editMode){
            Log.d(LOG_TAG,"added new species, " + speciesName);
            garden.addSpecies(speciesName);
        }
        garden.setDescription(speciesName, getEditTextText(R.id.description_box));
        garden.setSize(speciesName, Integer.parseInt(getEditTextText(R.id.size_box)));
        garden.setSunLevel(speciesName, getEditTextText(R.id.sun_box));
        garden.setSpeciesType(speciesName,evalTypeRadioButtons());

        setColor(getEditTextText(R.id.color_box),garden,speciesName);

        /* saving the garden */
        try {
            Log.v(LOG_TAG,"saving garden");
            Log.v(LOG_TAG,"Garden String: " + Garden.gardenToString(garden));
            FileOperation.save(App.SAVEFILE_NAME,Garden.gardenToString(garden));
        } catch (IOException e) {
            Log.e(LOG_TAG,"unambe to save garden", e);
            Toast.makeText(getApplicationContext(),"Unable to save garden",Toast.LENGTH_SHORT).show();
        }

        /* launching the next activity */
        Log.v(LOG_TAG, "Launching view info activity");
        Intent intent = new Intent(getApplicationContext(),ViewSpeciesInfoActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,speciesName);
        startActivity(intent);
    }

    /**
     * gets the text from an EditText
     * @param id the EditText id
     * @return the String of the text
     */
    protected String getEditTextText(int id){
        EditText editText = (EditText)findViewById(id);
        String str = editText.getText().toString();
        return removeSymbols(str);
    }

    /**
     *
     * @param str original string
     * @return String with no special char
     */
    protected String removeSymbols(String str){
        return str.replaceAll("[^A-Za-z0-9 ]", "");
    }

    protected String evalTypeRadioButtons(){
        RadioButton annual    = (RadioButton)findViewById(R.id.radio_annual);
        RadioButton perennial = (RadioButton)findViewById(R.id.radio_perennial);
        RadioButton tree      = (RadioButton)findViewById(R.id.radio_tree);
        if(annual.isChecked()){
            return "annual";
        }
        if(perennial.isChecked()){
            return "perennial";
        }
        if(tree.isChecked()){
            return "tree";
        }
        Log.wtf(LOG_TAG, "No radio buttons are checked");
        return null;
    }


    /**
     * this method takes the hex color given by the user, adds in the alpha and saves it to the garden.
     * @param hexColor the user provided hex String
     * @param garden the garden storing all this data
     * @param speciesName the name of the plant with the color
     */
   protected void setColor(String hexColor, Garden garden, String speciesName){
       int color = Integer.parseInt(hexColor,16);

       color += 0xff000000; //this sets the alpha
       Log.v(LOG_TAG, "color = " + color); //very verbose logging
       garden.setColor(speciesName,color); //actual setting of the color

   }


}
