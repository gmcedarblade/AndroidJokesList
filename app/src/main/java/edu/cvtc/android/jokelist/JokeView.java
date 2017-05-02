package edu.cvtc.android.jokelist;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by Greg on 4/17/2017.
 */

public class JokeView extends LinearLayout implements RadioGroup.OnCheckedChangeListener {

    private TextView jokeTextView;

    private  Joke joke;

    private RadioGroup ratingRadioGroup;
    private RadioButton likeButton;
    private RadioButton dislikeButton;

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

        this.joke = joke;
    }

    public Joke getJoke() {
        return joke;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {
            case R.id.likeButton:
                joke.setRating(Joke.LIKE);
        }

    }

    public static interface OnJokeChangeListener {
        public void onJokeChanged(JokeView view, Joke joke);
    }
}
