package com.cs1530_group1.gardenapp;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import java.util.Date;

/**
 * PlantDrawable : and extension of ShapeDrawable used so that useful plant information can be
 * stored with the shape representing the plant
 */
public class PlantDrawable extends ShapeDrawable{
    private String species;
    private Date plantDate;

    // This should be the only constructor ever used for a PlantDrawable
    // Do not use the default constructor
    public PlantDrawable(Shape s, String species, Date plantDate){
        super(s);
        this.species = species;
        this.plantDate = plantDate;
    }

    public String getSpecies()
    {
        return species;
    }

    public Date getPlantDate()
    {
        return plantDate;
    }
}
