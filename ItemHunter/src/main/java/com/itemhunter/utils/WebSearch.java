package com.itemhunter.utils;

import java.util.ArrayList;

import com.itemhunter.objects.Listing;

public class WebSearch {
	
	//Possible hashmap <string, method()> usage where each website is a pre-determined number
	
	public WebSearch(){
		generateWebHashmap();
	}
	
	public void apiSelector(ArrayList<String> websites){
		for (String website : websites){
			System.out.println(website);
			//hashmap.get(equalsIgnoreCase(website)); //This runs the method attached
		}
	}
	
	private void generateWebHashmap(){
		/*
		 * A few options here:
		 * 1) Hard code all the values in
		 * 2) Use a properties or AppConstants file and read it in
		 * 3) Retrieve master copy from WebServer and read that in
		 * 4) Produce a local copy on app download and only update when required
		 * 5) ??? Somehow keep a live copy somewhere that can add functionality immediately ???
		 * 6) PROFIT
		 */
	}
	
	//Repeat for each API
	//Need to come up with a better way to futureproof this (ie if we get to 25+ APIs)
	public ArrayList<Listing> ebaySearch(){
		return null;
	}
	
	public ArrayList<Listing> amazonSearch(){
		return null;
	}
	
	public ArrayList<Listing> etsySearch(){
		return null;
	}

}
