package com.teddydev.abnd_9_habit_tracker;

import android.content.Context;
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
}
