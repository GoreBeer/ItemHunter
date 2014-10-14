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
import android.widget.Toast;

import com.itemhunter.huntfunc.HuntHolder;
import com.itemhunter.objects.GenericHunt;
import com.itemhunter.objects.Listing;
import com.itemhunter.sqlite.AppConstants;
import com.itemhunter.utils.ListingAdapter;

import java.util.ArrayList;


public class Home extends ActionBarActivity {

    ListingAdapter ad;
    protected HuntHolder newHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO - FUTURE CHANGE: Investigate moving calls into separate thread and doing 'lazy loading'
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //TODO - remove this, it's for testing
        Listing listing = new Listing("16GB IPOD", 106.59, "Adelaide, South Australia", "EBAY", BitmapFactory.decodeResource(getResources(), R.drawable.closesign));
        Listing listing1 = new Listing("64GB IPAD Mini", 799.93, "Ocean Grove, Victoria", "EBAY", BitmapFactory.decodeResource(getResources(), R.drawable.plussign));
        GenericHunt huntTest = new GenericHunt();
        huntTest.getListings().add(listing);
        huntTest.getListings().add(listing1);

        //Set up the main hunt holder here
        newHolder = HuntHolder.getInstance();
        newHolder.startHuntHolder(getApplicationContext());
        newHolder.getHuntsHolder().add(huntTest);

        //This adds the hunt titles to the filter spinner
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

        //Creates custom listing adapter and displays in the ListView
        ListView list = (ListView)findViewById(R.id.itemsList);
        ad = new ListingAdapter(this, newHolder.getHuntsHolder().get(0).getListings(), getResources());
        list.setAdapter(ad);

        //Checks if user has set locations
        checkLocations();
        Log.i(AppConstants.TAG, "Home page ready");
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

        /*
         * This method will work as follows:
         *  - On start up, check sharedprefs for haveAsked
         *  - If no or if haveAsked is false (aka they said remind me later) we prompt them with the dialog
         *  - Dialog will simply ask them to pick one country from the Country Picker
         *  - Once a country is picked, close the dialog
         *  //TODO - VERY FUTURE: Re-work the CountryPicker plugin to not display already chosen items
         */

        /*
        if(!userProf.contains(AppConstants.HAVEASKED) || userProf.getString(AppConstants.HAVEASKED, "N").equalsIgnoreCase("N")){
            //Spin up instance of CountryPicker
         */


        if(!userProf.contains(AppConstants.LOCATIONS)) {
            //TODO - Remove this section, it's for testing
            SharedPreferences.Editor editor = userProf.edit();
            editor.putString(AppConstants.LOCATIONS, "Australia|New Zealand");
            editor.apply();

            //TODO - FUTURE CHANGE: Make this a proper pop-up where you can select a country
            Toast toast =  Toast.makeText(getApplicationContext(),
                    "You haven't picked a location, change in the Profile menu", Toast.LENGTH_LONG);
            toast.show();
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