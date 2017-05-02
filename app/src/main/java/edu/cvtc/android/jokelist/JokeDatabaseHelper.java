package edu.cvtc.android.jokelist;

/**
 * Created by Greg on 4/27/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class hooks up to a ContentProvider for initialization and maintenance.
 */

public class JokeDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "jokes.db";

    public static final int DATABASE_VERSION = 1;

    public JokeDatabaseHelper(final Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        JokeTable.onCreate(database);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        JokeTable.onUpgrade(database, oldVersion, newVersion);

    }
}
