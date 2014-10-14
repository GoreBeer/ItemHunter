package com.itemhunter.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.itemhunter.sqlite.AppConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kyle on 18/09/2014.
 */
public class CreateHuntName extends Activity {
    protected String suggestedName = "hunt_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_hunt_name);

        //Construct a datetime object to get today's date in a certain format
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmmss");
        Date today = Calendar.getInstance().getTime();
        String strDate = df.format(today);
        suggestedName+=strDate;

        //Set the hint to be today's date
        EditText titleText = (EditText) findViewById(R.id.hunt_name);
        titleText.setHint(suggestedName);

        Log.v(AppConstants.TAG, "CreateHuntName Dialog instantiated successfully");
    }

    //Called by the Cancel button
    public void cancelDialog(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);

        Log.v(AppConstants.TAG, "CreateHuntName Dialog cancelled");
        finish();
    }

    //Called by the Create button
    public void createHunt(View view){
        EditText titleText = (EditText) findViewById(R.id.hunt_name);

        //If no name has been chosen, we set it to the placeholder text
        if(titleText.getText().toString().equalsIgnoreCase("")){
            titleText.setText(suggestedName);
        }

        //Create a new intent, set the title and return back to new_hunt activity
        Intent intent = new Intent();
        intent.putExtra(AppConstants.TITLE, titleText.getText().toString());
        setResult(RESULT_OK, intent);

        Log.v(AppConstants.TAG, "CreateHuntName Dialog finished successfully");
        finish();
    }
}
