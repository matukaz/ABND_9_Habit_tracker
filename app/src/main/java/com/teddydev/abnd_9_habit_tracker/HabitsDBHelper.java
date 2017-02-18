package com.teddydev.abnd_9_habit_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teddydev.abnd_9_habit_tracker.Contract.HabitContract.HabitTable;

/**
 * Created by Matu on 18.02.2017.
 */

public class HabitsDBHelper extends SQLiteOpenHelper {

    private static final int HABIT_TABLE_VERSION = 1;

    public HabitsDBHelper(Context context) {
        super(context, HabitTable.TABLE_NAME, null, HABIT_TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // If table does not exist
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitTable.TABLE_NAME + " ("
                + HabitTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitTable.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitTable.COLUMN_HABIT_NR_OF_TIMES + " INTEGER NOT NULL);";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor readEntireHabitsData() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {
                HabitTable._ID,
                HabitTable.COLUMN_HABIT_NAME,
                HabitTable.COLUMN_HABIT_NR_OF_TIMES
        };

        Cursor cursor = sqLiteDatabase.query(
                HabitTable.TABLE_NAME,           // The table to query
                projection,                      // The columns to return
                null,                            // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                            // don't group the rows
                null,                            // don't filter by row groups
                null                             // The sort order
        );
        return cursor;
    }


    /**
     * The row ID of the newly inserted row, or -1 if an error occurred
     *
     * @return
     */
    public long insertMockData() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitTable.COLUMN_HABIT_NAME, "Learning ABND in Udacity");
        values.put(HabitTable.COLUMN_HABIT_NR_OF_TIMES, 10);

        // Insert the new row, returning the primary key value of the new row
        return sqLiteDatabase.insert(HabitTable.TABLE_NAME, null, values);
    }
}
