package com.cs1530_group1.gardenapp;

import android.graphics.Color;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

/**
 * @author Charles Smith
 */
public class AdditActivityTest extends TestCase {

    @SmallTest
    public void testSetColor() throws Exception {
        Garden garden = new Garden();
        garden.addSpecies("Test Plant");
        AdditActivity activity = new AdditActivity();
        activity.setColor("0000FF",garden,"Test Plant");
        assertEquals(Color.BLUE,garden.getColor("Test Plant"));
    }
    @SmallTest
    public void testSetColor2() throws Exception {
        Garden garden = new Garden();
        garden.addSpecies("Test Plant");
        AdditActivity activity = new AdditActivity();
        activity.setColor("00FF00",garden,"Test Plant");
        assertEquals(Color.GREEN,garden.getColor("Test Plant"));
    }
    @SmallTest
    public void testSetColor3() throws Exception {
        Garden garden = new Garden();
        garden.addSpecies("Test Plant");
        AdditActivity activity = new AdditActivity();
        activity.setColor("FF0000",garden,"Test Plant");
        assertEquals(Color.RED,garden.getColor("Test Plant"));
    }

    @SmallTest
    public void testSetColor4() throws Exception {
        Garden garden = new Garden();
        garden.addSpecies("Test Plant");
        AdditActivity activity = new AdditActivity();
        activity.setColor("000000",garden,"Test Plant");
        assertEquals(Color.BLACK,garden.getColor("Test Plant"));
    }

}