 	package com.itemhunter.huntfunc;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.itemhunter.objects.GenericHunt;

public class HuntHolder {
	
	protected ArrayList<GenericHunt> pingsHolder;	
	protected ScheduledExecutorService scheduledThreadPool;
	
	//protected CustomContentProvider dbAccessor;
	
	public HuntHolder(){
		this.pingsHolder = new ArrayList<GenericHunt>();

		//this is the initial loader.  May need to move to its own method if we want to load more stuff
		//pingsHolder.add(dbAccessor.retrieveCurrentPings());
		scheduledThreadPool = Executors.newScheduledThreadPool(this.pingsHolder.size());
		setPingTimer(this.pingsHolder);		
	}
	
	//use this one on initial load
	private void setPingTimer(ArrayList<GenericHunt> pings) {		
		for(GenericHunt ping: pings){
			setPingTimer(ping);
		}
	}
	
	//use this to set new Pings
	public boolean setPingTimer(GenericHunt ping){
		long pingFreq = ping.getPingFrequency();
		scheduledThreadPool.scheduleAtFixedRate(ping, pingFreq, pingFreq, TimeUnit.SECONDS);
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

}
