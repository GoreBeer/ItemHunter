package com.itemhunter.objects;

import java.util.ArrayList;

import com.itemhunter.utils.WebSearch;

public class GenericHunt implements Runnable {

	protected long id; //This will be used when the db is implemented to allow changes to the db	
	protected String title;	
	protected String query;	
	protected double priceMin;	
	protected double priceMax;	
	protected ArrayList<String> websites;	
	protected ArrayList<String> locations;
	//This value will only be passed into the APIs that require/can use it.  Else default to false
	protected boolean searchType; // 0 = fixed, 1 = other/all/auction	
	protected long pingFrequency; //All values converted to minutes so long is probably best	
	protected boolean notificationType; // 0 = push, 1 = email	
	protected boolean pingConnectionType; // 0 = wifi only, 1 = all	
	protected ArrayList<Listing> listings;	
	protected WebSearch searchEngine;
	
	public GenericHunt(){
		//empty constructor
	}	
	
	public GenericHunt(String title, String query, double priceMin, double priceMax, ArrayList<String> websites, 
	ArrayList<String> locations, boolean searchType, long pingFrequency, boolean notificationType, 
	boolean pingConnectionType)
	{
		//Required - Will be checked in the UI for null/empty values
		this.title = title;
		this.query = query;
		this.priceMax = priceMax;
		this.websites = websites;
		this.locations = locations;
		this.pingFrequency = pingFrequency;
		
		//Optional or has a default value
		this.priceMin = priceMin;
		this.searchType = searchType; //defaults to 1
		this.pingConnectionType = pingConnectionType; //defaults to 1
		
		//Initialise the listings array here
		this.listings = new ArrayList<Listing>();
		
		searchEngine = new WebSearch();
	}
	
	//run method goes here + others
	
	//getters and setters below.  All are accessible except for id and listings
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public double getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(double priceMin) {
		this.priceMin = priceMin;
	}

	public double getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(double priceMax) {
		this.priceMax = priceMax;
	}

	public ArrayList<String> getWebsites() {
		return websites;
	}

	public void setWebsites(ArrayList<String> websites) {
		this.websites = websites;
	}

	public ArrayList<String> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<String> locations) {
		this.locations = locations;
	}

	public boolean isSearchType() {
		return searchType;
	}

	public void setSearchType(boolean searchType) {
		this.searchType = searchType;
	}

	public long getPingFrequency() {
		return pingFrequency;
	}

	public void setPingFrequency(long pingFrequency) {
		this.pingFrequency = pingFrequency;
	}

	public boolean isNotificationType() {
		return notificationType;
	}

	public void setNotificationType(boolean notificationType) {
		this.notificationType = notificationType;
	}

	public boolean isPingConnectionType() {
		return pingConnectionType;
	}

	public void setPingConnectionType(boolean pingConnectionType) {
		this.pingConnectionType = pingConnectionType;
	}

	public ArrayList<Listing> getListings() {
		return listings;
	}

	@Override
	public void run() {
		// TODO Add in code here to call WebSearch object API reader
		
	}
	
	
	
}