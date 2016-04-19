package com.nicatec.everpobre.model.db;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nicatec.everpobre.model.db.dao.NotebookDAO;


public class DBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;

	public static final String[] CREATE_DATABASE_SCRIPTS = {
			NotebookDAO.SQL_CREATE_NOTEBOOK_TABLE
	};
	
	public static final String DROP_DATABASE = "";

	private static DBHelper sInstance;
	
	private  DBHelper(String databaseName, Context context) {
		super(context, databaseName, null, DATABASE_VERSION);
	}

	public static DBHelper getInstance(String databaseName, Context context) {
	    // Use the application context, which will ensure that you 
	    // don't accidentally leak an Activity's context.
	    // See this article for more information: http://bit.ly/6LRzfx
	    if (sInstance == null) {
	      sInstance = new DBHelper(databaseName, context.getApplicationContext());
	    }
	    return sInstance;
	 }
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		
		// called everytime a DB connection is opened. We activate foreing keys to have ON_CASCADE deletion
		db.execSQL("PRAGMA foreign_keys = ON");
		
		// if API LEVEL > 16, use this
		// db.setForeignKeyConstraintsEnabled(true);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		createDB(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
		// upgrades for version 1->2
			Log.i("DBHelper", "Migrating from V1 to V2");
			/*
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_LATITUDE + " real;");
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_LONGITUDE + " real;");
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_HAS_COORDINATES + " integer;");
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_ADDRESS + " text;");
*/
		case 2:
		// upgrades for version 2->3

		case 3:
		// upgrades for version 3->4
		}
		
	}
	
	// utility method to create DB
	private void createDB(SQLiteDatabase db) {
		for (String sql: CREATE_DATABASE_SCRIPTS) {
			db.execSQL(sql);
		}
	}
	
	// convenience methods to convert types
	
	public static int convertBooleanToInt(boolean b) {
		return b ? 1 : 0;
	}
	
	public static boolean convertIntToBoolean(int b) {
		return b == 0 ? false : true;
	}
	
	
	public static Long convertDateToLong(Date date) {
	    if (date != null) {
	        return date.getTime();
	    }
	    return null;
	}
	
	public static Date convertLongToDate(Long dateAsLong) {
	    if (dateAsLong == null) {
	        return null;
	    }
	    return new Date(dateAsLong);
	}

}
