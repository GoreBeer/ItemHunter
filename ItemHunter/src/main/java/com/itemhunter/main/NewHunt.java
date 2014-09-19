package com.itemhunter.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;


import com.countrypicker.CountryPickerListener;
import com.countrypicker.CountryPicker;
import com.itemhunter.huntfunc.HuntHolder;
import com.itemhunter.objects.GenericHunt;
import com.itemhunter.sqlite.AppConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Kyle on 9/09/2014.
 */
public class NewHunt extends ActionBarActivity {
    //TODO - Maybe change this into a SortedSet or similar so that duplicate values won't be added.  Always save the list on app shutdown
    protected ArrayList<String> locations;
    protected Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_hunt);

        //Sets the statically set list for the website spinner - See strings.xml for list name=websites
        Spinner spinner = (Spinner)findViewById(R.id.websitespin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.websites, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Gets the user preferences and sets the location automatically, also allows worldwide selection
        SharedPreferences userpref = getSharedPreferences(AppConstants.USERPREFS, 0);
        String locs = userpref.getString(AppConstants.LOCATIONS, "none");

        //Add user chosen locations to the spinner.  User location defaults to World if none are chosen.
        locations = new ArrayList<String>(Arrays.asList(locs.split("\\|")));
        System.out.println("Locations successfully split");
        Spinner spin = (Spinner)findViewById(R.id.locationspin);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);
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
    //Toggles the optional extras on the new hunt screen
    public void showMore(View view){
        ScrollView moreOp = (ScrollView)findViewById(R.id.moreOptsScroll);
        if(moreOp.isShown()){
            moreOp.setVisibility(View.GONE);
            view.setBackgroundResource(R.drawable.plussign);
        }
        else{
            moreOp.setVisibility(View.VISIBLE);
            view.setBackgroundResource(R.drawable.negsign);
        }
    }

    //Sends user back to the home screen
    public void goHome(View view){
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }

    //Builds a CountryPicker object which show users a scrollable interface with a search bar to
    //help reduce the number of countries displayed
    public void editLocations(View view){
        //Create the country picker dialog
        CountryPicker country = CountryPicker.newInstance("Choose your locations");
        country.show(getSupportFragmentManager(), "COUNTRY_PICKER");

        country.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code) {
                //Grab userprefs for updating
                SharedPreferences userpref = getSharedPreferences(AppConstants.USERPREFS, 0);
                String locs = userpref.getString(AppConstants.LOCATIONS, "none");
                //TODO - Check here for duplicates and stop the process if found
                //Append the new country to the list
                String newLocs = locs + "|"+name;
                SharedPreferences.Editor edit = userpref.edit();
                edit.putString(AppConstants.LOCATIONS, newLocs);
                edit.apply();

                //Create a toast to notify user of list addition
                CharSequence text = name+" added to locations";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();

                //add to locations easy access list
                locations.add(name);

                //Refresh the spinner to show new user selection
                Spinner spin = (Spinner)findViewById(R.id.locationspin);
                ArrayAdapter<String> adapt = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, locations);
                adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapt);
            }
        });
    }

    //Used to build the dialog to name the new hunt, this calls a custom interface to do the job
    public void callHuntNamer(View view){
        Intent cHNIntent = new Intent(this, CreateHuntName.class);
        startActivityForResult(cHNIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

            }
        }
    }

    public void createHunt(String huntName){
        //create vars
        //required vars
        EditText searchText = (EditText)findViewById(R.id.searchText);
        String search = searchText.getText().toString();
        EditText maxPriceText = (EditText)findViewById(R.id.maxPrice);
        String maxPrice = maxPriceText.getText().toString();
        Spinner websiteSpin = (Spinner)findViewById(R.id.websitespin);
        String website = websiteSpin.getSelectedItem().toString();

        //check if optionals haves been selected
        EditText minPriceText = (EditText)findViewById(R.id.minPrice);
        String minPrice = minPriceText.getText().toString();
        if(!minPrice.trim().equalsIgnoreCase("")){

        }
        ArrayList<String> bang = new ArrayList<String>();
        bang.add("Both");
        long pingFreq = 300;
        //create new object
        GenericHunt newHunt = new GenericHunt("newhunt", search, 0, Double.parseDouble(maxPrice), bang,locations, true, pingFreq,
                true, true);

        //Add to main holder and set ping timer
        HuntHolder hunt = new HuntHolder();
        hunt.setPingTimer(newHunt);
        dialog.hide();
        //TODO - Send user back to home screen after?
    }
}