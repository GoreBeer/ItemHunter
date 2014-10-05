package com.itemhunter.sqlite;


/*
 * Use this class to store all the SQL strings we plan to use
 * This is done to know where to find all of the strings and to aid in code re-use
 *  and future-proofing (if there are more tables added)
 */
public class AppConstants {
	
	//Required general items
	public static final String LOG = "DatabaseHelper";
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "ITEM_HUNTER";
    public static final String TAG = "ItemHunter";
	
	//common column names
	public static final String KEY_ID = "id";
	
	//Profiles table
	public static final String PROFILES = "profiles";
	public static final String USERNAME = "username"; //PK
	public static final String PASSHASH = "pass_hash";
	public static final String EMAIL = "email";
	
	//Hunts table
	public static final String HUNTS = "hunts";
    public static final String TITLE = "title";
	public static final String QUERYNAME = "query";
	public static final String PRICEMIN = "price_min";
	public static final String PRICEMAX = "price_max";
	public static final String WEBSITESTRING = "websites";
	public static final String COUNTRIESTRING = "countries";
	public static final String SEARCHTYPE = "search_type";
	public static final String HUNTFREQUENCY = "hunt_frequency";
	public static final String NOTIFICATIONTYPE = "notification_type";
	public static final String HUNTCONNECTIONTYPE = "hunt_conn_type";

		
	//Listings table 
	public static final String LISTINGS = "listings";
	//TITLE is here but it's defined above in Hunts
	public static final String PRICE = "price";
	public static final String LOCATION = "location";
	public static final String WEBSITE = "website";
	public static final String PICTUREHASH = "picturehash";
    public static final String HUNTKEY = "huntkey";
	public static final String DATETIME = "datetime";

	
	public static final String TOTAL_COUNT_LISTINGS = "SELECT COUNT(*) FROM " + LISTINGS; //Used for capping
	
	//Table Creation
	public static final String CREATE_SQL_TABLE_PROFILE = "CREATE TABLE " + PROFILES + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ USERNAME + " TEXT UNIQUE, " + PASSHASH + " TEXT, " + EMAIL + " TEXT" + ")";
	
	public static final String CREATE_SQL_TABLE_HUNTS = "CREATE TABLE " + HUNTS + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ TITLE + "TEXT, " + QUERYNAME + " TEXT, " + PRICEMIN + " INTEGER, " + PRICEMAX + " INTEGER, " + WEBSITESTRING + " TEXT, "
			+ COUNTRIESTRING + " TEXT, " + SEARCHTYPE + " INTEGER, " + HUNTFREQUENCY + " INTEGER, " + NOTIFICATIONTYPE
			+ " INTEGER, " + HUNTCONNECTIONTYPE + " INTEGER" + ")";
	
	public static final String CREATE_SQL_TABLE_LISTINGS = "CREATE TABLE " + LISTINGS + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ TITLE + " TEXT, " + PRICE + " INTEGER, " + LOCATION + " TEXT, " + WEBSITE + " TEXT, " + PICTUREHASH + " TEXT, " +
            HUNTKEY + " TEXT, " + DATETIME + " TEXT" + ")";
	
	//Table Drops
	public static final String DROP_SQL_TABLE_PROFILE = "DROP TABLE IF EXISTS " + PROFILES;
	public static final String DROP_SQL_TABLE_HUNT = "DROP TABLE IF EXISTS " + HUNTS;
	public static final String DROP_SQL_TABLE_LISTINGS = "DROP IF EXISTS " + LISTINGS;
	
	//Table Inserts
	public static final String INSERT_NEW_PROFILE = "INSERT INTO TABLE " + PROFILES + " VALUES (?,?,?)";
	public static final String INSERT_NEW_HUNT = "INSERT INTO TABLE " + HUNTS + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_NEW_LISTING = "INSERT INTO TABLE " + LISTINGS + "VALUES (?,?,?,?,?,?,?,?)";
	
	//Table Updates
	public static final String UPDATE_PROFILE_EMAIL = "USERNAME = ?";
	public static final String UPDATE_PROFILE_PASSHASH = "PASSHASH = ?";
	public static final String UPDATE_ON_KEY_ID = KEY_ID + "=?";
	
	//Table Deletions
	public static final String DELETE_USER_PROFILE = "DELETE * FROM " + PROFILES + " WHERE " + USERNAME + "= ?";
	public static final String DELETE_HUNT_BY_ID = "DELETE * FROM " + HUNTS + " WHERE " +  UPDATE_ON_KEY_ID;
	public static final String DELETE_LISTING_BY_ID = "DELETE * FROM " + LISTINGS + " WHERE " + UPDATE_ON_KEY_ID;
	
	//Table Selects
	//profile
	public static final String SELECT_PROFILE_PASSHASH = "SELECT " + PASSHASH + " FROM " + PROFILES + " WHERE " + USERNAME +"= ?";
	public static final String SELECT_PROFILE_ALL = "SELECT * FROM " +  PROFILES + " WHERE " + KEY_ID + "=?";
	public static final String SELECT_PROFILE_EMAIL = "SELECT " + EMAIL + " FROM " + PROFILES + " WHERE " + USERNAME + "=?";
	//pings
	public static final String SELECT_ALL_HUNTS = "SELECT * FROM " + HUNTS;
	public static final String SELECT_HUNT_BY_ID = SELECT_ALL_HUNTS + " WHERE " + UPDATE_ON_KEY_ID;
	public static final String SELECT_HUNT_ATTR_BY_ID = "SELECT ? FROM " + HUNTS +" WHERE "+ UPDATE_ON_KEY_ID;
	//listings
	public static final String SELECT_ALL_LISTINGS = "SELECT * FROM " + LISTINGS;
	public static final String SELECT_LISTING_BY_ID = SELECT_ALL_LISTINGS + " WHERE " + UPDATE_ON_KEY_ID;
	public static final String SELECT_LISTINGS_ATTR_BY_ID = "SELECT ? FROM " + LISTINGS + " WHERE " + UPDATE_ON_KEY_ID;

    //SharedPreferences File Names
    public static final String USERPREFS = "User_Prefs";

    //SharedPreferences Keys
    public static final String LOCATIONS = "locations";
}
