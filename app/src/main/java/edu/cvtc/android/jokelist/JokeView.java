package edu.cvtc.android.jokelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Greg on 4/17/2017.
 */

public class JokeView extends LinearLayout{

    private TextView jokeTextView;

    /**
     * Basic Constructor that takes an Application Context
     * and a Joke object.
     *
     * @param context
     *          The application context in which the view is added.
     * @param joke
     *          The Joke this View is responsible for displaying.
     */
    public JokeView(final Context context, final Joke joke) {

        super(context);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.joke_view, this, true);
        jokeTextView = (TextView) findViewById(R.id.jokeTextView);
        jokeTextView.setText(joke.getText());
    }

}
