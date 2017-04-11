package edu.cvtc.android.jokelist;

import android.content.Context;
import android.graphics.Color;
import android.hardware.input.InputManager;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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

        final String[] jokes = getResources().getStringArray(R.array.jokeList);
        for (final String jokeText : jokes) {

            addJoke(jokeText);

        }

        initListeners();

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

    /**
     * Method used to provide Event Listeners to our Views.
     */
    private void initListeners() {

        addJokeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addJokeFromEditText();

            }
        });

        jokeEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_UP){

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_NUMPAD_ENTER:
                            case KeyEvent.KEYCODE_ENTER:
                                addJokeFromEditText();
                                return true;
                            default:
                                break;
                    }
                }

                return false;

            }
        });
    }

    private void addJokeFromEditText() {

        final String jokeText = jokeEditText.getText().toString().trim();
        addJoke(jokeText);
        jokeEditText.setText("");

        hideSoftKeyboard();
        
    }

    private void hideSoftKeyboard() {

        final View view = getCurrentFocus();
        if (view != null) {

            final InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }

    /**
     * Method used to provide the logic for initializing a new Joke,
     * adding it to jokeList, and displaying it on screen.
     *
     * @param jokeText a String containing the text of the Joke to add.
     */
    private void addJoke(final String jokeText) {

        if (null != jokeText && !jokeText.isEmpty()) {

            Log.d("joke_list", "Adding a new joke to the list: " + jokeText); //FIXME: Remove before shipping

            final Joke joke = new Joke(jokeText);
            jokeList.add(joke);

            final TextView textView = new TextView(this);
            textView.setText(joke.getText());

            if (jokeList.size() % 2 == 0) {
                textView.setBackgroundColor(Color.DKGRAY);
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setBackgroundColor(Color.LTGRAY);
                textView.setTextColor(Color.BLACK);
            }

            jokeLayout.addView(textView);

        }

    }
}
