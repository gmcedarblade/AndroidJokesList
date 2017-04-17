package edu.cvtc.android.jokelist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Data Structure for the List of Joke object the Activity will present to the user.
     */
    private ArrayList<Joke> jokeList = new ArrayList<>();

    /**
     * Layout container for a Joke.
     */
    private ListView jokeListView;

    /**
     * Adapter for jokeLayout.
     */
    private JokeListAdapter jokeListAdapter;

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

        // initialize the layout
        initLayout();

        jokeListAdapter = new JokeListAdapter(this, jokeList);

        final String[] jokes = getResources().getStringArray(R.array.jokeList);
        for (final String jokeText : jokes) {

            addJoke(new Joke(jokeText));

        }

        // initialize the event listeners
        initListeners();

    }

    /**
     * Method used to encapsulate code for initializing and
     * setting the Layout for this Activity.
     */
    private void initLayout() {

        setContentView(R.layout.activity_main);

        addJokeButton = (Button) findViewById(R.id.addJokeButton);
        jokeEditText = (EditText) findViewById(R.id.jokeEditText);

        jokeListView = (ListView) findViewById(R.id.jokeListViewGroup);
        jokeListView.setAdapter(jokeListAdapter);

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
        
        if (null != jokeText && !jokeText.isEmpty()) {
            addJoke(new Joke(jokeText));
            jokeEditText.setText("");

            hideSoftKeyboard();
        } else {
            Toast.makeText(this, "You must enter text for new Joke...", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideSoftKeyboard() {

        final View view = getCurrentFocus();
        if (view != null) {

            final InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }

    /**
     * Method used for encapsulating the logic necessary to properly
     * add a new Joke to jokeList, and display it on screen.
     *
     * @param joke The Joke to add to the list of Jokes.
     */
    private void addJoke(final Joke joke) {

        jokeList.add(joke);
        jokeListAdapter.notifyDataSetChanged();

    }
}
