package com.teddydev.abnd_9_habit_tracker.Contract;

import android.provider.BaseColumns;

/**
 * Created by Matu on 17.02.2017.
 */

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitContract() {}

    /**
     * SQLite does not support dates and/or times
     */
    public static class HabitTable implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_NR_OF_TIMES = "nr_of_times"; // Nr of times habit has been done
    }
}
