package com.example.anuradha.app1.DBConnection;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravidu on 8/1/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "leaderboard.db";

    // Table names
    private static final String TABLE_NAME = "userstat";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MODE = "type";
    private static final String KEY_PLAYER = "player";
    private static final String KEY_DIFFICULTY = "difficulty";
    private static final String KEY_WINS = "wins";
    private static final String KEY_LOSSES = "losses";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + KEY_MODE + " INTEGER NOT NULL, " + KEY_PLAYER + " TEXT NOT NULL, " + KEY_DIFFICULTY + " TEXT NOT NULL, " + KEY_WINS + " INTEGER, " + KEY_LOSSES + " INTEGER);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);
        // Create tables again
        onCreate(db);
    }

    //this method is used to insert user statistics to the database
    public void addUserStat(UserStat stat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MODE, stat.getMode());
        values.put(KEY_PLAYER, stat.getPlayer());
        values.put(KEY_DIFFICULTY, stat.getDifficulty());
        values.put(KEY_WINS, stat.getWins());
        values.put(KEY_LOSSES, stat.getLosses());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //This method will return leaderboard starting from the player which has highest number of wins in the relevant mode
    public List<UserStat> getUserStatWins(int mode) {
        List<UserStat> recordList = new ArrayList<UserStat>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + KEY_MODE + " = " + mode + " ORDER BY " + KEY_WINS + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserStat userstat = new UserStat(mode, cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
                userstat.setId(Integer.parseInt(cursor.getString(0)));
                recordList.add(userstat);
            } while (cursor.moveToNext());
        }
        return recordList;
    }

    //This method will return leaderboard starting from the player which has highest number of losses in the relevant mode
    public List<UserStat> getUserStatLosses(int mode) {
        List<UserStat> recordList = new ArrayList<UserStat>();
        // Select  All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + KEY_MODE + " = " + mode + " ORDER BY " + KEY_LOSSES + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserStat userstat = new UserStat(mode, cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
                userstat.setId(Integer.parseInt(cursor.getString(0)));
                recordList.add(userstat);
            } while (cursor.moveToNext());
        }
        db.close();
        return recordList;
    }

    public boolean updatelosses(String player, int losses) {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + KEY_PLAYER + " = '" + player + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int numlosses = Integer.parseInt(cursor.getString(5)) + losses;
            String updatequery = "UPDATE " + TABLE_NAME + " SET " + KEY_LOSSES + "='" + numlosses + "' WHERE " + KEY_PLAYER + "='" + player + "'";
            db.execSQL(updatequery);
            db.close();
            return true;
        }
        db.close();
        return false;

    }

    public boolean updatewins(String player, int wins) {

        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + KEY_PLAYER + " = '" + player + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int numwins = Integer.parseInt(cursor.getString(4)) + wins;
            String updatequery = "UPDATE " + TABLE_NAME + " SET " + KEY_WINS + "='" + numwins + "' WHERE " + KEY_PLAYER + "='" + player + "'";
            db.execSQL(updatequery);
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public boolean searchPlayer(String player) {
        String searchquery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + KEY_PLAYER + " = '" + player + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(searchquery, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteReocrd(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}