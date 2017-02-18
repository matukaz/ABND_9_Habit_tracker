package com.teddydev.abnd_9_habit_tracker;

import android.database.Cursor;
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
        mHabitsDBHelper.insertMockData();
        DisplayDatabaseInfo();
    }

    public void DisplayDatabaseInfo() {

        StringBuilder stringBuilder = new StringBuilder();

        Cursor cursor = mHabitsDBHelper.readEntireHabitsData();

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
    }
}
