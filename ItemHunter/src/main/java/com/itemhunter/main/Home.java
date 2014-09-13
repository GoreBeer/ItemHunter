package com.itemhunter.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.itemhunter.sqlite.AppConstants;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        checkLocations();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
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

    //This checks to see if the user has set any locations
    private void checkLocations(){

        SharedPreferences userProf = getSharedPreferences(AppConstants.USERPREFS, 0);
        SharedPreferences.Editor editor = userProf.edit();
        //If sharedpreferences doesn't have a key (aka it's the first time using the app) then
        //ask for location of country by user.  How to present the country list?
        if(!userProf.contains(AppConstants.LOCATIONS))
            //Temporarily setting static for testing
            editor.putString(AppConstants.LOCATIONS, "World|Australia|New Zealand");
            //TODO - Call to run a popup asking user for location

        editor.commit();
    }

    public void goOptions(View view){
        Intent proIntent = new Intent(this, Profile.class);
        startActivity(proIntent);
    }

    public void goNewHunt(View view){
        Intent hunIntent = new Intent(this, NewHunt.class);
        startActivity(hunIntent);
    }



}
