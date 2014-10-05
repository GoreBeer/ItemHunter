package com.itemhunter.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.itemhunter.sqlite.AppConstants;

/**
 * Created by Kyle on 18/09/2014.
 */
public class CreateHuntName extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_hunt_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Cancel dialog
    public void cancelDialog(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    //Create button new hunt dialog
    public void createHunt(View view){
        Intent intent = new Intent();
        EditText titleText = (EditText) findViewById(R.id.hunt_name);
        Log.i(AppConstants.TAG, "Title text is: " + titleText.getText().toString());
        intent.putExtra(AppConstants.TITLE, titleText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
