package com.itemhunter.objects;

import java.util.ArrayList;

import com.itemhunter.utils.WebSearch;

public class GenericHunt implements Runnable {

	protected long id; //This will be used when the db is implemented to allow changes to the db	
	protected String title;	
	protected String query;	
	protected double priceMin; //Default is 0
	protected double priceMax;	
	protected String website; //TODO - in the future this can be multiple websites
	protected String location; //TODO - in the future this can be multiple countries

	protected boolean searchType; // 0(false) = fixed, 1(true) = auction. Default is fixed.
	protected long huntFrequency; //Default is 0 and measured in minutes
	protected boolean notificationType; // 0 = push, 1 = email	//TODO - FUTURE this should be multi-choice
	protected boolean huntConnectionType; // 0 = wifi only, 1 = all
	protected ArrayList<Listing> listings;	
	protected WebSearch searchEngine;
	
	public GenericHunt(){
        //TODO - For testing, remove when done
		listings = new ArrayList<Listing>();
        title = "Test Hunt";
	}	
	
	public GenericHunt(String title, String query, double priceMin, double priceMax, String website, 
	String location, boolean searchType, long huntFrequency, boolean notificationType,
	boolean huntConnectionType)
	{
		//Required - Will be checked in the UI for null/empty values
		this.title = title;
		this.query = query;
		this.priceMax = priceMax;
		this.website = website;
		this.location = location;
		this.huntFrequency = huntFrequency;
		
		//Optional or has a default value
		this.priceMin = priceMin; //defaults to 0
		this.searchType = searchType; //defaults to 1
        this.notificationType = notificationType; //defaults to true
		this.huntConnectionType = huntConnectionType; //defaults to 1
		
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean getSearchType() {
		return searchType;
	}

	public void setSearchType(boolean searchType) {
		this.searchType = searchType;
	}

	public long getHuntFrequency() {
		return huntFrequency;
	}

	public void setHuntFrequency(long huntFrequency) {
		this.huntFrequency = huntFrequency;
	}

	public boolean getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(boolean notificationType) {
		this.notificationType = notificationType;
	}

	public boolean getHuntConnectionType() {
		return huntConnectionType;
	}

	public void setHuntConnectionType(boolean huntConnectionType) {
		this.huntConnectionType = huntConnectionType;
	}

	public ArrayList<Listing> getListings() {
		return listings;
	}

	@Override
	public void run() {
		// TODO Add in code here to call WebSearch object API reader
		
	}
	
	
	
}