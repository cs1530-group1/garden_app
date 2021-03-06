package com.cs1530_group1.gardenapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.graphics.Point;
import android.view.Display;
import android.widget.RelativeLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;

/**
 * GardenDrawingActivity : the graphical front end for drawing plants on top of the
 * garden layout picture.
 */
@SuppressWarnings("deprecation")
public class GardenDrawingActivity extends ActionBarActivity {
    //Log tag, for logging errors
    private static final String LOG_TAG = "GardenDrawingActivity";

    Garden g; // The garden being drawn
    String speciesName; // The species of the new plant to be added

    GardenView gardenView; // The view that does all the drawing
    RelativeLayout buttonPanel; // The panel that holds all the buttons for adding,removing, cancelling, etc
    TextView plantInfo;

    /**
     * onCreate : creates a new GardenView
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        // Get the garden so that it can be passed to the GardenView
        App app = (App)getApplication();
        g = app.getGarden();

        // Retrieve the species name of the plant being added if
        // this activity is being started from Add Plant
        speciesName = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        
        super.onCreate(savedInstanceState);

        // Set the layout to the garden drawing activity xml layout
        setContentView(R.layout.activity_garden_drawing);

        // Get the GardenView class instance and RelativeLayout button panel for modifications
        gardenView = (GardenView) findViewById(R.id.GardenView);
        buttonPanel = (RelativeLayout) findViewById(R.id.ButtonPanel);

        // Get the plant info TextView upfront
        plantInfo = (TextView) findViewById(R.id.PlantDescription);


        // If the speciesName is not null, then this activity is being passed in the species name
        // of a plant to be added -- We need to set the Button Panel to visible and decrease the
        // width of the Garden View
        if (speciesName != null) {

            // Reveal button panel
            // The text of the TextView will be set to speciesName
            showButtonPanel(speciesName);

            // Set the mode to ADD so the GardenView knows to render the temporary plant
            gardenView.setMode(GardenMode.ADD);

            // Set the species to be added
            gardenView.setNewPlantSpecies(speciesName);


        } // Else: Set the mode to VIEW so that the temporary plant is not rendered
        else gardenView.setMode(GardenMode.VIEW);
    }

    // Refactored from onCreate()
    // Does everything necessary to show the Button Panel in the layout, which
    // is invisible from the start
    public void showButtonPanel(String speciesInfo)
    {
        // Get the screen width
        int width = getScreenWidth();

        // Set the panel to visible
        buttonPanel.setVisibility(View.VISIBLE);

        // Get the layout parameters for the GardenView and for the Button Panel
        LayoutParams params = gardenView.getLayoutParams();
        LayoutParams panel_params = buttonPanel.getLayoutParams();

        // Set the GardenView width to the screen width - the Button Panel width
        params.width = width - panel_params.width;
        gardenView.setLayoutParams(params);

        // Set the plant info text
        setPlantInfo(speciesInfo);
    }

    /**
     * Expands and shows the remove button
     * Should be used when going to EDIT mode
     */
    public void showRemoveButton()
    {
        // Get the Remove Button and the Confirm Button
        Button removeButton = (Button) findViewById(R.id.Remove);

        // Set the Remove Button to visible
        removeButton.setVisibility(View.VISIBLE);
    }

    /**
     * Compresses and hides the Remove Button
     * Should be used when leaving CANCEL mode
     */
    public void hideRemoveButton()
    {
        // Get the Remove Button and the Confirm Button
        Button removeButton = (Button) findViewById(R.id.Remove);

        // Set the Remove Button to invisible
        removeButton.setVisibility(View.GONE);
    }

    // Set the string in the Plant Info TextView
    public void setPlantInfo(String s)
    {
        plantInfo.setText(s);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_garden_drawing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    // Acquires the width of the device screen in pixels
    private int getScreenWidth()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    // Acquires the height of the device screen in pixels
    private int getScreenHeight()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    // Services the Confirm button on the panel of buttons -- will cause the temporary plant
    // that has been moved around to be firmly planted in the Garden
    public void confirmClicked(View view)
    {
        //Toast.makeText(this, "Confirm not yet implemented", Toast.LENGTH_SHORT).show();
        gardenView.confirmNewPlantLocation();

        // Save the garden
        try {
            FileOperation.save(App.SAVEFILE_NAME, Garden.gardenToString(g));
        }catch(Exception e){e.printStackTrace();}

    }

    // Services the Add Another button on the panel of buttons -- will cause a new temporary plant
    // to be created so that the user can move it around and ultimately plant it.
    public void addAnotherClicked(View view)
    {
        //Toast.makeText(this, "Add Another not yet implemented", Toast.LENGTH_SHORT).show();
        hideRemoveButton();
        setPlantInfo(speciesName);
        gardenView.addAnotherPlant();
    }

    // Services the Cancel button on the panel of buttons -- the functionality has not yet been
    // implemented
    public void cancelClicked(View view)
    {
        // Set the panel to visible
        buttonPanel.setVisibility(View.INVISIBLE);

        // Get the layout parameters for the GardenView and for the Button Panel
        LayoutParams params = gardenView.getLayoutParams();
        LayoutParams panel_params = buttonPanel.getLayoutParams();

        // Set the GardenView width to the screen width
        params.width = getScreenWidth();
        gardenView.setLayoutParams(params);

        // Let the GardenView know that cancel was clicked
        gardenView.cancel();
    }

    // Services the Remove button on the panel of buttons -- the functionality has not yet been
    // implemented
    public void removeClicked(View view)
    {
        //Toast.makeText(this, "Remove not yet implemented", Toast.LENGTH_SHORT).show();
        gardenView.remove();

        // Save the garden
        /*try {
            FileOperation.save(App.SAVEFILE_NAME, Garden.gardenToString(g));
        }catch(Exception e){e.printStackTrace();}*/
    }
    

}
