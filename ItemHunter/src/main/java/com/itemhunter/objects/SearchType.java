package com.itemhunter.objects;

public class SearchType {
	protected boolean auction;
	protected boolean buyNow;
	
	public SearchType(String i){
		if(i.equalsIgnoreCase("auction")){
			auction = true;
		}
		else if(i.equalsIgnoreCase("buynow")){
			buyNow = true;
		}
		else if(i.equalsIgnoreCase("both")){
			auction = true;
			buyNow = true;
		}
	}
	
	public boolean isBoth(){
		return auction == buyNow;
	}
}
