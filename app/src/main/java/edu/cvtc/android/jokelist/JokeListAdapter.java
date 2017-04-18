package edu.cvtc.android.jokelist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * This class binds the visual JokeViews and the data behind them (Jokes).
 *
 * Created by Greg on 4/17/2017.
 */

public class JokeListAdapter extends BaseAdapter{

    /**
     * The application context in which the adapter is used.
     */
    private Context context;

    /**
     * The data set to which the adapter is bound.
     */
    private List<Joke> jokeList;

    /**
     * Parameterized constructor that takes in the application context in which
     * it is being used and the Collection of Joke Objects to which it is bound.
     * @param context
     * @param jokeList
     */
    public JokeListAdapter(final Context context, List<Joke> jokeList) {

        this.context = context;
        this.jokeList = jokeList;

    }

    @Override
    public int getCount() {
        return null != jokeList ? jokeList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != jokeList ? jokeList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Joke joke = jokeList.get(position);

        convertView = new JokeView(context, joke);

        return convertView;
    }
}
