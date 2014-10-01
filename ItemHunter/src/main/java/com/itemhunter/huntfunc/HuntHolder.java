 	package com.itemhunter.huntfunc;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.itemhunter.objects.GenericHunt;
import com.itemhunter.sqlite.DatabaseHelper;

    /**
     * Huntholder provides the main functionality for firing hunts
     */

public class HuntHolder {
    protected ArrayList<GenericHunt> huntsHolder;
	protected ScheduledExecutorService scheduledThreadPool;
	
	protected DatabaseHelper dbAccessor;
	
	public HuntHolder(Context context){
        dbAccessor = new DatabaseHelper(context);
        //Loads the current pings to immediately start the thread scheduler
        this.huntsHolder = dbAccessor.getCurrentPings();
        System.out.println("Hunts loaded. huntsHolder contains "+huntsHolder.size()+" hunts");

        //Creates the scheduled thread pool with the pings retrieved from db
		scheduledThreadPool = Executors.newScheduledThreadPool(this.huntsHolder.size());

        //makes a call to method that sets each hunt's timer
		setHuntTimer(this.huntsHolder);
	}
	
	//use this one on initial load
	private void setHuntTimer(ArrayList<GenericHunt> hunts) {
		for(GenericHunt hunt: hunts){
            setHuntTimer(hunt);
		}
	}
	
	//use this to set new Pings
	public boolean setHuntTimer(GenericHunt hunt){
        huntsHolder.add(hunt);
		long huntFreq = hunt.getHuntFrequency();
		scheduledThreadPool.scheduleAtFixedRate(hunt, huntFreq, huntFreq, TimeUnit.SECONDS);
		return false; //true if works, false if not
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
