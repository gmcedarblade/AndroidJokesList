package edu.cvtc.android.jokelist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Class provides content from a SQLite database to the application using JokeDatabaseHelper.
 * Provide joke info to a ListView via a CursorAdapter. The JokeDatabaseHelper stores
 * jokes in a two-dimensional table, where each row is a joke and each column is a property
 * of a joke (_id, text, rating).
 *
 * Note: CursorLoaders require a ContentProvider, which is why this class includes a
 * SQLite database helper instead of managing transactions manually.
 *
 * Created by Greg on 5/2/2017.
 */

public class JokeContentProvider extends ContentProvider {

    // Joke database helper
    private JokeDatabaseHelper jokeDatabaseHelper;

    // Values for a URIMatcher
    private static final int UPDATE = 1;
    private static final int QUERY = 2;

    private static final String AUTHORITY = "edu.cvtc.android.jokelist.provider";

    /**
     * The root path for using the URI matcher.
     * This is a label to a two-dimensional array in the database helper
     * filled with rows whose columns contain joke data.
     */
    private static final String BASE_PATH = "joke_table";

    /**
     *This provider's content location. Used by accessing
     * applications to interact with this provider
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {

        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/joke/#", UPDATE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/filter/#", QUERY);

    }

    @Override
    public boolean onCreate() {

        jokeDatabaseHelper = new JokeDatabaseHelper(getContext());

        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
