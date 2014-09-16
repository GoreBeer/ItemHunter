package com.itemhunter.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;


import com.countrypicker.CountryPickerListener;
import com.countrypicker.CountryPicker;
import com.itemhunter.sqlite.AppConstants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kyle on 9/09/2014.
 */
public class NewHunt extends ActionBarActivity {
    //TODO - Maybe change this into a SortedSet or similar so that duplicate values won't be added.  Always save the list on app shutdown
    protected ArrayList<String> locations;

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

    public void goHome(View view){
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }

    public void editLocations(View view){
        CountryPicker country = CountryPicker.newInstance("Choose your locations");
        country.show(getSupportFragmentManager(), "COUNTRY_PICKER");

        country.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code) {
                SharedPreferences userpref = getSharedPreferences(AppConstants.USERPREFS, 0);
                String locs = userpref.getString(AppConstants.LOCATIONS, "none");

                String newLocs = locs + "|"+name;
                SharedPreferences.Editor edit = userpref.edit();
                edit.putString(AppConstants.LOCATIONS, newLocs);

                Context context = getApplicationContext();
                CharSequence text = name+" added to locations";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                locations.add(name);

                Spinner spin = (Spinner)findViewById(R.id.locationspin);
                ArrayAdapter<String> adapt = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, locations);
                adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapt);
            }
        });
    }
}