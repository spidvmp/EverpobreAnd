package com.nicatec.everpobre.model.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nicatec.everpobre.model.Notebook;
import com.nicatec.everpobre.model.Notebooks;
import com.nicatec.everpobre.model.db.DBConstants;
import com.nicatec.everpobre.model.db.DBHelper;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class NotebookDAO {

	//public static final String KEY_NOTEBOOK_DESCRIPTION = "description";


	private static final long INVALID_ID_DELETE_ALL_RECORDS = 0;


	private WeakReference<Context> context;
	private DBHelper db;
	// private Context context;

	public NotebookDAO() {
		db = DBHelper.getInstance();

	}


	public long insert(Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}

		// insert

		long id = db.getWritableDatabase().insert(DBConstants.TABLE_NOTEBOOK, null, this.getContentValues(notebook));
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
		//int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), KEY_NOTEBOOK_ID + "=" + id, null);
		int numberOfRowsUpdated = db.getWritableDatabase().update(DBConstants.TABLE_NOTEBOOK, this.getContentValues(notebook), DBConstants.KEY_NOTEBOOK_ID + "=?", new String[]{"" + id});

		db.close();
		db=null;
		return numberOfRowsUpdated;
	}

	public void delete(long id) {

		if (id == INVALID_ID_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(DBConstants.TABLE_NOTEBOOK,  null, null);
		} else {
			db.getWritableDatabase().delete(DBConstants.TABLE_NOTEBOOK, DBConstants.KEY_NOTEBOOK_ID + " = " + id, null);
		}
		db.close();
		db=null;
	}

	public void deleteAll() {
		delete(INVALID_ID_DELETE_ALL_RECORDS);
	}

	public static ContentValues getContentValues(Notebook notebook) {
		ContentValues content = new ContentValues();
		content.put(DBConstants.KEY_NOTEBOOK_NAME, notebook.getName());
		//content.put(KEY_RADAR_DESCRIPTION, radar.getDescription());

		return content;
	}


	// convenience method
	public static @NonNull Notebook elementFromCursor(final @NonNull Cursor c) {
		assert c != null;

		final String name = c.getString(c.getColumnIndex(DBConstants.KEY_NOTEBOOK_NAME));
		final long id = c.getLong(c.getColumnIndex(DBConstants.KEY_NOTEBOOK_ID));

		final Notebook notebook = new Notebook(id, name);

		return notebook;
	}


	/**
	 * Returns all radars in DB inside a Cursor
	 * @return cursor with all records
	 */

	public Cursor queryCursor() {
		// select
		Cursor c = db.getReadableDatabase().query(DBConstants.TABLE_NOTEBOOK, DBConstants.allColumns, null, null, null, null, DBConstants.KEY_NOTEBOOK_ID);

		return c;
	}


	public Notebooks query() {
		List<Notebook>notebooksList = new LinkedList<>();

		Cursor cursor = queryCursor();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Notebook notebook = elementFromCursor(cursor);
				notebooksList.add(notebook);
			} while (cursor.moveToNext());
		}

		Notebooks notebooks = Notebooks.createNotebooks(notebooksList);
		return notebooks;
	}


	/**
	 * Returns a Radar object from its id
	 * @param id - the radar id in db
	 * @return Radar object if found, null otherwise
	 */
	public @Nullable Notebook query(long id) {
		Notebook notebook = null;

		String where = DBConstants.KEY_NOTEBOOK_ID + "=" + id;
		Cursor c = db.getReadableDatabase().query(DBConstants.TABLE_NOTEBOOK, DBConstants.allColumns, where, null, null, null, null);
		if (c != null) {
			if (c.getCount() > 0) {
				c.moveToFirst();
				notebook = elementFromCursor(c);
			}
			c.close();
		}

		db.close();
		return notebook;
	}


}
