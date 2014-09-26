package com.yemyatthu.notepadapp;

import android.app.Application;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

public class NoteApplication extends Application {

    private static final String APPLICATION_ID = "MwDg2lKIi66w4YCqBtEKe2NC7EQe7wcwG7HOVHVG";
    private static final String CLIENT_KEY = "6SQMMPOQQAr3jJIkOFfYfZ2YVIIcL33aQ4XDinkm";


    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(this,APPLICATION_ID,CLIENT_KEY);
    }

}