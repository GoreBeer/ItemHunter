package com.itemhunter.main;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Kyle on 9/09/2014.
 */
public class NewHunt extends ActionBarActivity {
    protected ArrayList<String> websites = new ArrayList<String>();
    protected ArrayList<String> locations = new ArrayList<String>();

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
        Spinner spin = (Spinner)findViewById(R.id.locationspin);
        //ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,)
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


}
