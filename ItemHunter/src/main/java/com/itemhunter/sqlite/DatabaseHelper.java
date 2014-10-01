package com.itemhunter.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.itemhunter.objects.GenericHunt;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context){
		super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(AppConstants.CREATE_SQL_TABLE_PROFILE);
		db.execSQL(AppConstants.CREATE_SQL_TABLE_HUNTS);
		db.execSQL(AppConstants.CREATE_SQL_TABLE_LISTINGS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

    /*
     * Created as a getter method for the pings list, used at startup only atm
     */
    public ArrayList<GenericHunt> getCurrentPings(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GenericHunt> hunts = new ArrayList<GenericHunt>();
        Cursor cursor = db.rawQuery(AppConstants.SELECT_ALL_HUNTS, null);

        if(cursor.moveToFirst()){
            do {
               GenericHunt hunt = new GenericHunt();
                hunt.setQuery(cursor.getString(cursor.getColumnIndex(AppConstants.QUERYNAME)));
                hunt.setPriceMin(cursor.getDouble(cursor.getColumnIndex(AppConstants.PRICEMIN)));
                hunt.setPriceMax(cursor.getDouble(cursor.getColumnIndex(AppConstants.PRICEMAX)));
                //TODO - Better way to store a list of countries and websites in db (string manipulation seems wrong)
                String webs = cursor.getString(cursor.getColumnIndex(AppConstants.WEBSITESTRING));
                hunt.setWebsites(new ArrayList<String>(Arrays.asList(webs.split("\\|"))));
                String locs = cursor.getString(cursor.getColumnIndex(AppConstants.COUNTRIESTRING));
                hunt.setLocations(new ArrayList<String>(Arrays.asList(locs.split("\\|"))));
                hunt.setSearchType(cursor.getInt(cursor.getColumnIndex(AppConstants.SEARCHTYPE)) != 0);
                hunt.setHuntFrequency(cursor.getLong(cursor.getColumnIndex(AppConstants.HUNTFREQUENCY)));
                hunt.setNotificationType(cursor.getInt(cursor.getColumnIndex(AppConstants.NOTIFICATIONTYPE)) != 0);
                hunt.setHuntConnectionType(cursor.getInt(cursor.getColumnIndex(AppConstants.HUNTCONNECTIONTYPE)) != 0);
                //String huntKey = cursor.getString(cursor.getColumnIndex(AppConstants.LISTINGKEY));
                //TODO - Call sql using FK here to get all listings in Listings table with same FK.  Set to listings arraylist

                hunts.add(hunt);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return hunts;
    }



}
