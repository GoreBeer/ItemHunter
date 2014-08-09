package com.itemhunter.objects;

public class Profile {
    //TODO - May not need this class, could store values in DB and SharedPreferences file
	
	protected String userName;	
	protected String email;	
	protected String defaultCountry;	
	protected Achievements cheevoList;
	protected String hashWord;
	
	//The general idea behind this is to use something like a PBKDF2 or bcrypt library to hash+salt and save it in the db.
	//Then whenever a user enters their password, we hash+salt that password and compare in the db.  If match then A++
 	
	public Profile(){
		//empty constructor
	}
	
	public Profile(String userName, String email, String defaultCountry, String hashWord){
		this.userName = userName;
		this.email = email;
		this.defaultCountry = defaultCountry;
		this.hashWord = hashWord;
	}
	
	/* GETTERS & SETTERS BELOW */	
	public String getUserName(){
		return userName;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getCountry(){
		return defaultCountry;
	}
	
	public void setCountry(String defaultCountry){
		this.defaultCountry = defaultCountry;
	}
	
	public Achievements getCheevoList(){
		return cheevoList;
	}
	
	public String getPassword(){
		return hashWord;
	}
	
	public void setPassword(String hashWord){
		this.hashWord = hashWord;
	}
	
	
}
