package com.itemhunter.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.itemhunter.huntfunc.HuntHolder;
import com.itemhunter.objects.GenericHunt;
import com.itemhunter.objects.Listing;
import com.itemhunter.sqlite.AppConstants;
import com.itemhunter.utils.ListingAdapter;

import java.util.ArrayList;


public class Home extends ActionBarActivity {

    ListingAdapter ad;
    private HuntHolder newHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO - Future release investigate moving all these start up calls into their own thread
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //TODO - Remove to remove this, it's for testing
        Listing listing = new Listing("16GB IPOD", 106.59, "Adelaide, South Australia", "EBAY", BitmapFactory.decodeResource(getResources(), R.drawable.closesign));
        Listing listing1 = new Listing("64GB IPAD Mini", 799.93, "Ocean Grove, Victoria", "EBAY", BitmapFactory.decodeResource(getResources(), R.drawable.plussign));
        GenericHunt huntTest = new GenericHunt();
        huntTest.getListings().add(listing);
        huntTest.getListings().add(listing1);
        huntTest.getListings().add(listing);
        huntTest.getListings().add(listing1);
        huntTest.getListings().add(listing);
        huntTest.getListings().add(listing1);
        huntTest.getListings().add(listing);
        huntTest.getListings().add(listing1);

        //This sets up the item hunter main
        newHolder = new HuntHolder(getApplicationContext());
        newHolder.getHuntsHolder().add(huntTest);

        ArrayList<String> huntNames = new ArrayList<String>();
        for(GenericHunt hunt : newHolder.getHuntsHolder()){
            huntNames.add(hunt.getTitle());
        }
        //If there are no saved hunts in the db, then we just display this dropdown
        if(huntNames.isEmpty()){
            huntNames.add("No Hunts Created");
        }

        //Set the hunts spinner to display saved hunt names
        Spinner spinner = (Spinner)findViewById(R.id.HuntSpinner);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, huntNames);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapt);


        //TODO - Call method to add listing data to scrollList
        ListView list = (ListView)findViewById(R.id.itemsList);
        ad = new ListingAdapter(this, newHolder.getHuntsHolder().get(0).getListings(), getResources());
        list.setAdapter(ad);

        checkLocations();
        Log.v(AppConstants.TAG, "Home page instantiated");
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

        //If sharedpreferences doesn't have a key (aka it's the first time using the app) then
        //ask for location of country by user.  How to present the country list?
        if(!userProf.contains(AppConstants.LOCATIONS)) {
            SharedPreferences.Editor editor = userProf.edit();
            //Temporarily setting static for testing
            editor.putString(AppConstants.LOCATIONS, "World|Australia|New Zealand");
            //TODO - Call to run a popup asking user for location and change to dynamic values
            editor.apply();
        }
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
