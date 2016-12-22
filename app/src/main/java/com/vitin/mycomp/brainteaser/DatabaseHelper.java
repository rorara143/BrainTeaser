package com.vitin.mycomp.brainteaser;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MyComp on 12/16/2016.
 */
    public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Player.db";
    public static final String TABLE_NAME = "player_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "NAME";
    public static final String COLUMN_3 = "LASTNAME";
    public static final String COLUMN_4 = "SCORE";






    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, " + "LASTNAME TEXT, " + "SCORE INTEGER)";
        database.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(database);
    }

    public Integer deleteData(String score) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, "SCORE = ?", new String[]{score});

    }

   // public boolean dataInsert(String ID, String NAME, String LASTNAME, String SCORE) {

   // }



        public Cursor getList () {
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery("Select * From" + TABLE_NAME, null);
            return cursor;
        }

    public boolean dataInsert(String ID, String NAME, String LASTNAME, String SCORE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, ID);
        contentValues.put(COLUMN_2, NAME);
        contentValues.put(COLUMN_3, LASTNAME);
        contentValues.put(COLUMN_4,SCORE);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

}

