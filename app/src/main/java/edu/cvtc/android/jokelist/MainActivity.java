package edu.cvtc.android.jokelist;

import android.graphics.Color;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Data Structure for the List of Joke object the Activity will present to the user.
     */
    private ArrayList<Joke> jokeList = new ArrayList<>();

    /**
     * Layout container for a Joke.
     */
    private LinearLayout jokeLayout;

    /**
     * ScrollView used to hold the Jokes in jokeList.
     */
    private ScrollView jokeScrollView;

    /**
     * EditText used to enter text for new Joke to be added to jokeList.
     */
    private EditText jokeEditText;

    /**
     * Button used for creating and adding a new Joke to jokeList using
     * text entered in jokeEditText.
     */
    private Button addJokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initLayout();

    }

    /**
     * Method used to encapsulate code for initializing and
     * setting the Layout for this Activity.
     */
    private void initLayout() {

        final LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        final LinearLayout addJokeLayout = new LinearLayout(this);
        addJokeLayout.setOrientation(LinearLayout.HORIZONTAL);

        addJokeButton = new Button(this);
        addJokeButton.setText("Add Joke");
        addJokeButton.setTextColor(Color.WHITE);
        addJokeButton.setBackgroundColor(Color.DKGRAY);
        addJokeLayout.addView(addJokeButton);

        // Create our LayoutParams for EditText
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        jokeEditText = new EditText(this);
        jokeEditText.setLayoutParams(params);
        addJokeLayout.addView(jokeEditText);

        jokeLayout = new LinearLayout(this);
        jokeLayout.setOrientation(LinearLayout.VERTICAL);

        jokeScrollView = new ScrollView(this);
        jokeScrollView.addView(jokeLayout);

        container.addView(addJokeLayout);
        container.addView(jokeScrollView);

        setContentView(container);

    }
}
