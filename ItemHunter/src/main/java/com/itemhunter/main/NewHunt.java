package com.itemhunter.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.itemhunter.sqlite.AppConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kyle on 9/09/2014.
 */
public class NewHunt extends ActionBarActivity {
    protected List<String> locations;

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
        System.out.println("Checking user preferences");
        SharedPreferences userpref = getSharedPreferences(AppConstants.USERPREFS, 0);
        String locs = userpref.getString("locations", "none");
        System.out.println("Sharedprefs open, locations are: " + locs);
        if(!locs.equalsIgnoreCase("none")){
            locations = Arrays.asList(locs.split("|"));
            System.out.println("I am not none");
        }
        else {
            locations = new ArrayList<String>();
        }
        locations.add("World");

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
        RelativeLayout moreOp = (RelativeLayout)findViewById(R.id.moreOpts);
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
}
