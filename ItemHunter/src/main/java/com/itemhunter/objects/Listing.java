package com.itemhunter.objects;

import android.graphics.Bitmap;

public class Listing {
	
	protected long id;
	
	protected String title;
	
	protected double price;
	
	protected String location;
	
	protected String website;
	//TODO - Might need to store this as a Drawable (convert from and to Bitmap before putting in db)
	protected Bitmap picture;
	
	public Listing(){
		//empty constructor
	}
	
	public Listing(String title, double price, String location, String website, Bitmap picture){
		this.title = title;
		this.price = price;
		this.location = location;
		this.website = website;
		this.picture = picture;
	}
	
	/*GETTERS AND SETTERS BELOW */

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public String getLocation() {
		return location;
	}

	public String getWebsite() {
		return website;
	}

	public Bitmap getPicture() {
		return picture;
	}

    @Override
    public String toString(){
        return "Title: "+ this.title +" | Price: " + this.price + " | Location: " + this.location + " | Website: " + this.website;
    }
}
