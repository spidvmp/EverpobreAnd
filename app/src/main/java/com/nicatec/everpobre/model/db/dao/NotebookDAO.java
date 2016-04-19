package com.nicatec.everpobre.model.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nicatec.everpobre.model.Notebook;
import com.nicatec.everpobre.model.db.DBHelper;

import java.lang.ref.WeakReference;

public class NotebookDAO {
	public static final String TABLE_NOTEBOOK = "NOTEBOOK";

	// Table field constants 
	public static final String KEY_NOTEBOOK_ID = "_id";
	public static final String KEY_NOTEBOOK_NAME = "name";
	//public static final String KEY_NOTEBOOK_DESCRIPTION = "description";

	public static final String SQL_CREATE_NOTEBOOK_TABLE =
			"create table " + TABLE_NOTEBOOK
					+ "( " + KEY_NOTEBOOK_ID
					+ " integer primary key autoincrement, "
					+ KEY_NOTEBOOK_NAME + " text not null"
					//+ KEY_RADAR_DESCRIPTION + " text not null"
					+ ");";


	public static final String[] allColumns = {
			KEY_NOTEBOOK_ID,
			KEY_NOTEBOOK_NAME,
	};
	private static final long INVALID_ID_DELETE_ALL_RECORDS = 0;
	private final String databaseName;

	private WeakReference<Context> context;
	// private Context context;

	public NotebookDAO(Context context) {
		this("Notebooks.sqlite", context);
	}

	public NotebookDAO(String databaseName, Context context) {
		this.context = new WeakReference<>(context);
		this.databaseName = databaseName;
	}

	public long insert(Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}

		if (context.get() == null) {
			throw new IllegalStateException("Context NULL");
		}

		// insert
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		long id = db.getWritableDatabase().insert(TABLE_NOTEBOOK, null, this.getContentValues(notebook));
		notebook.setId(id);
		db.close();
		db=null;

		return id;
	}

	public int update(long id, Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}
		if ( id < 1) {
			throw new IllegalArgumentException("Passing id invalid");
		}
		if (context.get() == null) {
			throw new IllegalStateException("Context NULL");
		}

		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());
		//int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), KEY_NOTEBOOK_ID + "=" + id, null);
		int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), KEY_NOTEBOOK_ID + "=?", new String[]{"" + id});

		db.close();
		db=null;
		return numberOfRowsUpdated;
	}

	public void delete(long id) {
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		if (id == INVALID_ID_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(TABLE_NOTEBOOK,  null, null);
		} else {
			db.getWritableDatabase().delete(TABLE_NOTEBOOK, KEY_NOTEBOOK_ID + " = " + id, null);
		}
		db.close();
		db=null;
	}

	public void deleteAll() {
		delete(INVALID_ID_DELETE_ALL_RECORDS);
	}

	public static ContentValues getContentValues(Notebook notebook) {
		ContentValues content = new ContentValues();
		content.put(KEY_NOTEBOOK_NAME, notebook.getName());
		//content.put(KEY_RADAR_DESCRIPTION, radar.getDescription());

		return content;
	}


	// convenience method
/*
	public static Radar radarFromCursor(Cursor c) {
		assert c != null;

		String title = c.getString(c.getColumnIndex(KEY_RADAR_TITLE));
		String description = c.getString(c.getColumnIndex(KEY_RADAR_DESCRIPTION));
		long id = c.getLong(c.getColumnIndex(KEY_RADAR_ID));

		Radar radar = new Radar(title, description);
		radar.setId(id);

		return radar;
	}

*/
	/**
	 * Returns all radars in DB inside a Cursor
	 * @return cursor with all records
	 */
	/*
	public Cursor queryCursor() {
		// select
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		Cursor c = db.getReadableDatabase().query(TABLE_RADAR, allColumns, null, null, null, null, null);

		return c;
	}
/*
	public Radars query() {
		Radars radars = new Radars();

		Cursor cursor = queryCursor();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Radar radar = radarFromCursor(cursor);
				radars.add(radar);
			} while (cursor.moveToNext());
		}
		return radars;
	}
*/

	/**
	 * Returns a Radar object from its id
	 * @param id - the radar id in db
	 * @return Radar object if found, null otherwise
	 */
	/*
	public Radar query(long id) {
		Radar radar = null;

		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		String where = KEY_RADAR_ID + "=" + id;
		Cursor c = db.getReadableDatabase().query(TABLE_RADAR, allColumns, where, null, null, null, null);
		if (c != null) {
			if (c.getCount() > 0) {
				c.moveToFirst();
				radar = radarFromCursor(c);
			}
		}
		c.close();
		db.close();
		return radar;
	}

*/
}
