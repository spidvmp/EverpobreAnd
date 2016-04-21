package com.nicatec.everpobre.model.db;

/**
 * Created by vtx on 21/4/16.
 */
public class DBConstants {
	public static final String TABLE_NOTEBOOK = "NOTEBOOK";
	// Table field constants
	public static final String KEY_NOTEBOOK_ID = "_id";
	public static final String KEY_NOTEBOOK_NAME = "name";
	public static final String[] allColumns = {
			KEY_NOTEBOOK_ID,
			KEY_NOTEBOOK_NAME,
	};
	public static final String SQL_CREATE_NOTEBOOK_TABLE =
			"create table " + TABLE_NOTEBOOK
					+ "( " + KEY_NOTEBOOK_ID
					+ " integer primary key autoincrement, "
					+ KEY_NOTEBOOK_NAME + " text not null"
					//+ KEY_RADAR_DESCRIPTION + " text not null"
					+ ");";
}
