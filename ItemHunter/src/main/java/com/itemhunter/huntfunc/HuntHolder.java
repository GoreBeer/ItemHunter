 	package com.itemhunter.huntfunc;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.itemhunter.objects.GenericHunt;
import com.itemhunter.sqlite.AppConstants;
import com.itemhunter.sqlite.DatabaseHelper;

    /**
     * Huntholder provides the main functionality for firing hunts
     */

public class HuntHolder {
    //List of all saved hunts
    protected ArrayList<GenericHunt> huntsHolder;
        //hunt firer
	protected ScheduledExecutorService scheduledThreadPool;
    //access to the db
	protected DatabaseHelper dbAccessor;
	
	public HuntHolder(Context context){
        dbAccessor = new DatabaseHelper(context);
        //Loads the current pings to immediately start the thread scheduler
        this.huntsHolder = dbAccessor.getCurrentPings();
        Log.v(AppConstants.TAG, "Hunts loaded. huntsHolder contains " + this.huntsHolder.size() + " hunts");

        //Creates the scheduled thread pool with the pings retrieved from db
		scheduledThreadPool = Executors.newScheduledThreadPool(this.huntsHolder.size());

        //makes a call to method that sets each hunt's timer
		setHuntTimer(this.huntsHolder);
        Log.v(AppConstants.TAG, "Huntholder has been initialized");
	}
	
	//use this one on initial load, convenience method
	private void setHuntTimer(ArrayList<GenericHunt> hunts) {
        //go through all hunt and set timer of each
		for(GenericHunt hunt: hunts){
            setHuntTimer(hunt);
		}
	}
	
	//use this to set new Pings
	public void setHuntTimer(GenericHunt hunt){
        //First add the hunt to the list of hunts
        huntsHolder.add(hunt);
        //TODO - Add hunt to database here
		long huntFreq = hunt.getHuntFrequency();
		scheduledThreadPool.scheduleAtFixedRate(hunt, huntFreq, huntFreq, TimeUnit.SECONDS);
	}
	
	/*	this is used to save the list to the db.  Such scenarios include:
		new ping is created
		ping is modified
		ping is deleted 
	*/
	public boolean save(){
		//dbaccessor.save(pingsholder);
		//In dbaccessor, it could be a good idea to create a flag boolean for each ping so if a change is made it's changed
		//to true.  Thus when the db is reading through the pingsList, it will only make the necessary changes.
		return false; //true if works, false if not
	}

    public ArrayList<GenericHunt> getHuntsHolder(){
        return this.huntsHolder;
    }
}
