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
        this.huntsHolder = dbAccessor.getCurrentHunts();
        //Creates the scheduled thread pool with the pings retrieved from db
		scheduledThreadPool = Executors.newScheduledThreadPool(this.huntsHolder.size());
        //makes a call to method that sets each hunt's timer
		setHuntTimer(this.huntsHolder);
        Log.i(AppConstants.TAG, "HuntHolder initialized");
	}
	
	//Convenience method, use on initial load
	private void setHuntTimer(ArrayList<GenericHunt> hunts) {
        //set the timer of each hunt
		for(GenericHunt hunt: hunts){
            setHuntTimer(hunt);
		}
	}

	//This sets the timer for the provided hunt
	public void setHuntTimer(GenericHunt hunt){
        //Set the timer of the hunt
		long huntFreq = hunt.getHuntFrequency();
		scheduledThreadPool.scheduleAtFixedRate(hunt, huntFreq, huntFreq, TimeUnit.SECONDS);
	}

    //Convenience method - use this when creating a new hunt
    public void createHunt(GenericHunt hunt){
        //Add hunt to the HuntHolder
        huntsHolder.add(hunt);
        //Add hunt to database
        dbAccessor.addHunt(hunt);
        //Set timer for the hunt
        setHuntTimer(hunt);
    }
	
    public ArrayList<GenericHunt> getHuntsHolder(){
        return this.huntsHolder;
    }
}