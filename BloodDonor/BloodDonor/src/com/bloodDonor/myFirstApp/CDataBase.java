package com.bloodDonor.myFirstApp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CDataBase {
	
	// Database fields
			private SQLiteDatabase database;
			private MySQLiteHelper dbHelper;
			private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
					MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_BLD_TYP,MySQLiteHelper.COLUMN_PLACE, 
					MySQLiteHelper.COLUMN_CONTACT};
			
			
			public CDataBase(Context context) {
				dbHelper = new MySQLiteHelper(context);
			}
			
			public void open() throws SQLException {
			database = dbHelper.getWritableDatabase();
			}
			
			public void close() {
				dbHelper.close();
			}
			
			public CProperty createComment(String Name,String bloodType,String place,String contact) {
				ContentValues values = new ContentValues();
				values.put(MySQLiteHelper.COLUMN_NAME,Name);
				values.put(MySQLiteHelper.COLUMN_BLD_TYP, bloodType);
				values.put(MySQLiteHelper.COLUMN_PLACE,place);
				values.put(MySQLiteHelper.COLUMN_CONTACT,contact);
				
				long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
						values);
				Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
						allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
						null, null, null);
				cursor.moveToFirst();
				CProperty newProperty = cursorToComment(cursor);
				cursor.close();
				return newProperty;
			}
			
			private CProperty cursorToComment(Cursor cursor) {
				CProperty comment = new CProperty();
				comment.setId(cursor.getLong(0));
				comment.setName(cursor.getString(1));
				comment.setbloodType(cursor.getString(2));
				comment.setplace(cursor.getString(3));
				comment.setcontact(cursor.getString(4));
				return comment;
			}
			
			public Cursor getAllComments(String type) {
				List<CProperty> comments = new ArrayList<CProperty>();

				Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
						allColumns, MySQLiteHelper.COLUMN_BLD_TYP + " = '"+type+"'", null, null, null, null);

				/*	cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					CProperty comment = cursorToComment(cursor);
					comments.add(comment);
					cursor.moveToNext();
				}*/
				// Make sure to close the cursor
				/*cursor.close();*/
				return cursor;
			}


}