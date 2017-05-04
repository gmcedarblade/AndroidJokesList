package edu.cvtc.android.jokelist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.Arrays;
import java.util.HashSet;

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

    /**
     * Fetch rows from joke table given a specified URI that contains a filter value.
     * Return a cursor (rows of jokes) from joke table matching that filter.
     * @return cursor
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        checkColumns(projection);

        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(JokeTable.TABLE_NAME);

        final int uriCode = uriMatcher.match(uri);

        switch (uriCode) {
            case QUERY:
                final String filter = uri.getLastPathSegment();

                if (!filter.equals("" + Joke.SHOW_ALL)) {
                    queryBuilder.appendWhere(JokeTable.KEY_RATING + "=" + filter);
                } else {
                    selection = null;
                }

                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        final SQLiteDatabase database = jokeDatabaseHelper.getReadableDatabase();
        final Cursor cursor = queryBuilder.query(database, projection, selection, null, null, null, null);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    /**
     * We don't care about this method (for this application).
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * Inserts a new joke into joke_table given a specific Uri and
     * the values of the joke. It will insert a new row into the table
     * filled with that joke's data and will give the joke a new _id,
     * then returns a Uri containing the _id of the newly inserted joke.
     *
     * @return uri - include ID of the joke that was inserted.
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long id = 0; // _id for inserted joke

        final int uriCode = uriMatcher.match(uri);

        switch (uriCode) {

            case UPDATE:
                final SQLiteDatabase database = jokeDatabaseHelper.getWritableDatabase();
                id = database.insert(JokeTable.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);

        }

        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(BASE_PATH + "/" + id);
    }

    /**
     * Removes row(s) from joke_table given a specific Uri containing a joke _id,
     * and returns the number of rows removed.
     *
     * @return rowsDeleted = number of rows deleted
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int rowsDeleted = 0;
        final int uriCode = uriMatcher.match(uri);

        switch (uriCode) {

            case UPDATE:
                final String id = uri.getLastPathSegment();
                final SQLiteDatabase database = jokeDatabaseHelper.getWritableDatabase();
                rowsDeleted = database.delete(JokeTable.TABLE_NAME, JokeTable.KEY_ID + "=" + id, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        if (rowsDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    /**
     * Updates a row in joke_table given a specific Uri containing the _id
     * of the joke to be updated and the new joke values.
     *
     * @return rowsUpdated = number of rows updated.
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int rowsUpdated = 0;

        int uriCode = uriMatcher.match(uri);

        switch (uriCode) {

            case UPDATE:
                final String id = uri.getLastPathSegment();
                final SQLiteDatabase database = jokeDatabaseHelper.getWritableDatabase();
                rowsUpdated = database.update(JokeTable.TABLE_NAME, values,JokeTable.KEY_ID + "=" + id, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        if (rowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {

        final String[] available = { JokeTable.KEY_ID, JokeTable.KEY_TEXT, JokeTable.KEY_RATING };

        if(projection != null) {

            final HashSet<String> requestedColumns = new HashSet<>(Arrays.asList(projection));
            final HashSet<String> availableColumns = new HashSet<>(Arrays.asList(available));

            if (!availableColumns.containsAll(requestedColumns)) {

                throw new IllegalArgumentException("Unknown columns in projection.");

            }

        }

    }
}
