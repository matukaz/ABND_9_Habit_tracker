package com.teddydev.abnd_9_habit_tracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.teddydev.abnd_9_habit_tracker.Contract.HabitContract.HabitTable;

public class MainActivity extends AppCompatActivity {

    HabitsDBHelper mHabitsDBHelper = new HabitsDBHelper(this);
    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertMockData();
        readData();
    }

    public void readData() {

        SQLiteDatabase sqLiteDatabase = mHabitsDBHelper.getReadableDatabase();

        String[] projection = {
                HabitTable._ID,
                HabitTable.COLUMN_HABIT_NAME,
                HabitTable.COLUMN_HABIT_NR_OF_TIMES
        };

        Cursor cursor = sqLiteDatabase.query(
                HabitTable.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        try {
            StringBuilder stringBuilder = new StringBuilder();

            int idColumnIndex = cursor.getColumnIndex(HabitTable._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitTable.COLUMN_HABIT_NAME);
            int nrOfTimesColumnIndex = cursor.getColumnIndex(HabitTable.COLUMN_HABIT_NR_OF_TIMES);

            while (cursor.moveToNext()) {

                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                int nrOfATimes = cursor.getInt(nrOfTimesColumnIndex);
                stringBuilder.append(id).append("-").append(name).append("-").append(nrOfATimes).append("\n");
            }

            Log.d(TAG, stringBuilder.toString());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }

    /**
     * The row ID of the newly inserted row, or -1 if an error occurred
     * @return
     */
    public long insertMockData() {
        SQLiteDatabase sqLiteDatabase = mHabitsDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitTable.COLUMN_HABIT_NAME, "Learning ABND in Udacity");
        values.put(HabitTable.COLUMN_HABIT_NR_OF_TIMES, 10);

        // Insert the new row, returning the primary key value of the new row
        return sqLiteDatabase.insert(HabitTable.TABLE_NAME, null, values);
    }
}
