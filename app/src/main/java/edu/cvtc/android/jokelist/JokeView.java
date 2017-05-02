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

    private OnJokeChangeListener onJokeChangeListener;

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
        ratingRadioGroup = (RadioGroup) findViewById(R.id.ratingRadioGroup);
        likeButton = (RadioButton) findViewById(R.id.likeButton);
        dislikeButton = (RadioButton) findViewById(R.id.dislikeButton);

        ratingRadioGroup.setOnCheckedChangeListener(this);

        setJoke(joke);
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(final Joke joke) {

        this.joke = joke;

        jokeTextView.setText(joke.getText());

        switch (joke.getRating()) {

            case Joke.LIKE:
                likeButton.setChecked(true);
                break;
            case Joke.DISLIKE:
                dislikeButton.setChecked(true);
                break;
            default:
                ratingRadioGroup.clearCheck();
                break;

        }

        requestLayout();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {
            case R.id.likeButton:
                joke.setRating(Joke.LIKE);
                break;
            //FIXME: set rating dislike and set rating for default switch case to unrated
        }

    }

    public interface OnJokeChangeListener {
        void onJokeChanged(JokeView view, Joke joke);
    }
}
