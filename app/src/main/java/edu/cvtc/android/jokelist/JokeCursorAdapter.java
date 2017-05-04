package edu.cvtc.android.jokelist;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Greg on 5/2/2017.
 */

public class JokeCursorAdapter extends CursorAdapter {

    /**
     * Listener that is notified when internal state changes
     * for a Joke contained in a JokeView that is
     * created/managed by this Adapter.
     */
    private JokeView.OnJokeChangeListener onJokeChangeListener;

    public JokeCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public void setOnJokeChangeListener(final JokeView.OnJokeChangeListener onJokeChangeListener) {

        this.onJokeChangeListener = onJokeChangeListener;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        final Joke joke = new Joke(
                cursor.getString(JokeTable.INDEX_TEXT),
                cursor.getInt(JokeTable.INDEX_RATING),
                cursor.getLong(JokeTable.INDEX_ID));

        final JokeView jokeView = new JokeView(context, joke);

        jokeView.setOnJokeChangeListener(onJokeChangeListener);

        return jokeView;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final Joke joke = new Joke(
                cursor.getString(JokeTable.INDEX_TEXT),
                cursor.getInt(JokeTable.INDEX_RATING),
                cursor.getLong(JokeTable.INDEX_ID));

        ((JokeView) view).setOnJokeChangeListener(null); // stop recursive 'out of memory' issue from happening...
        ((JokeView) view).setJoke(joke);
        ((JokeView) view).setOnJokeChangeListener(onJokeChangeListener);

    }
}
