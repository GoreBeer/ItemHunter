package com.itemhunter.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.itemhunter.objects.GenericHunt;
import com.itemhunter.utils.Parser;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context){
		super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(AppConstants.CREATE_SQL_TABLE_PROFILE);
		db.execSQL(AppConstants.CREATE_SQL_TABLE_HUNTS);
		db.execSQL(AppConstants.CREATE_SQL_TABLE_LISTINGS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

    /*
     * Created as a getter method for the pings list, used at startup only atm
     */
    public ArrayList<GenericHunt> getCurrentHunts(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GenericHunt> hunts = new ArrayList<GenericHunt>();
        Cursor cursor = db.rawQuery(AppConstants.SELECT_ALL_HUNTS, null);

        if(cursor.moveToFirst()){
            do {
               GenericHunt hunt = new GenericHunt();
                hunt.setQuery(cursor.getString(cursor.getColumnIndex(AppConstants.QUERYNAME)));
                hunt.setPriceMin(cursor.getDouble(cursor.getColumnIndex(AppConstants.PRICEMIN)));
                hunt.setPriceMax(cursor.getDouble(cursor.getColumnIndex(AppConstants.PRICEMAX)));
                //TODO - Store lists in db as JSON objects
                String webs = cursor.getString(cursor.getColumnIndex(AppConstants.WEBSITESTRING));
                hunt.setWebsites(Parser.deTokifier(webs));
                String locs = cursor.getString(cursor.getColumnIndex(AppConstants.COUNTRIESTRING));
                hunt.setLocations(Parser.deTokifier(locs));
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

    public void addHunt(GenericHunt hunt){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AppConstants.TITLE, hunt.getTitle());
        values.put(AppConstants.QUERYNAME, hunt.getQuery());
        values.put(AppConstants.PRICEMIN, Double.toString(hunt.getPriceMin()));
        values.put(AppConstants.PRICEMAX, Double.toString(hunt.getPriceMax()));
        values.put(AppConstants.WEBSITESTRING, Parser.reTokifier(hunt.getWebsites()));
        values.put(AppConstants.COUNTRIESTRING, Parser.reTokifier(hunt.getLocations()));
        values.put(AppConstants.SEARCHTYPE, hunt.getSearchType() == false ? 0 : 1);
        values.put(AppConstants.HUNTFREQUENCY, hunt.getHuntFrequency());
        values.put(AppConstants.NOTIFICATIONTYPE, hunt.getNotificationType() == false ? 0 : 1);
        values.put(AppConstants.HUNTCONNECTIONTYPE, hunt.getHuntConnectionType() == false ? 0 : 1);

        Log.i(AppConstants.TAG, "Inserting new hunt into db");
        db.insert(AppConstants.HUNTS, null, values);
    }

}
