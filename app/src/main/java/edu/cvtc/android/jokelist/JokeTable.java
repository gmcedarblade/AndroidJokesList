package edu.cvtc.android.jokelist;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Greg on 4/27/2017.
 *
 * Class provides helpful database table accessor variables.
 *
 * Manages basic required database functionality.
 *
 */

public class JokeTable {

    public static final String TABLE_NAME = "joke_table";

    // Column names and IDs for accessing the database table.
    public static final String KEY_ID = "_id"; // Underscore used when using a CursorAdapter.
    public static final int INDEX_ID = 0;

    public static final String KEY_TEXT = "text";
    public static final int INDEX_TEXT = 1;

    public static final String KEY_RATING = "rating";
    public static final int INDEX_RATING = 2;

    // SQL statement for creating and dropping the "database"
    public static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" +
                        KEY_ID + " integer primary key autoincrement, " +
                        KEY_TEXT + " text not null, " +
                        KEY_RATING + " integer not null);";

    public static final String DATABASE_DROP = "drop table if exists " + TABLE_NAME;

    /**
     * Initialize the database.
     *
     * @param database - the database to initialize.
     *
     */
    public static void onCreate(final SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE);

    }

    /**
     *
     * Upgrades the database to a new version.
     *
     * @param database - database to upgrade.
     * @param oldVersion - old version of the database.
     * @param newVersion - new version of the database.
     */
    public static void onUpgrade(final SQLiteDatabase database, int oldVersion, int newVersion) {

        database.execSQL(DATABASE_DROP);
        onCreate(database);

    }

}
