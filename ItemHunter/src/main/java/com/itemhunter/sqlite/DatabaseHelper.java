package com.itemhunter.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context){
		super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(AppConstants.CREATE_SQL_TABLE_PROFILE);
		db.execSQL(AppConstants.CREATE_SQL_TABLE_PINGS);
		db.execSQL(AppConstants.CREATE_SQL_TABLE_LISTINGS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
