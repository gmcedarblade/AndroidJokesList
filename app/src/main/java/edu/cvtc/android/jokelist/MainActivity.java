package edu.cvtc.android.jokelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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

    // This is the JokeView object that was long clicked...
    private JokeView selectView;

    /**
     * Reference variables for keys used to store and retrieve from SharedPreferences.
     */
    private static final String SAVED_TEXT_KEY = "jokeText";

    // ActionMode and Callback for the action bar menu that presents
    // when a user Long Clicks on a JokeView item.
    private ActionMode actionMode;
    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            actionMode = mode;
            final MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu_remove, menu);
            return true;

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Don't need to do anything so leaving false...
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_remove:
                    jokeList.remove(selectView.getJoke());
                    jokeListAdapter.notifyDataSetChanged();
                    mode.finish();
                    break;
                default:
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            actionMode = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        jokeListAdapter = new JokeListAdapter(this, jokeList);


        // initialize the layout
        initLayout();


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

        jokeListView.setLongClickable(true);
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

        jokeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                selectView = (JokeView) view;
                startSupportActionMode(callback);

                return true;
            }
        });
    }

    private void addJokeFromEditText() {

        final String jokeText = jokeEditText.getText().toString().trim();
        
        if (null != jokeText && !jokeText.isEmpty()) {
            addJoke(new Joke(jokeText));
            jokeEditText.setText("");


        } else {
            Toast.makeText(this, "You must enter text for new Joke...", Toast.LENGTH_SHORT).show();
        }

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
     * Method used for encapsulating the logic necessary to properly
     * add a new Joke to jokeList, and display it on screen.
     *
     * @param joke The Joke to add to the list of Jokes.
     */
    private void addJoke(final Joke joke) {

        jokeList.add(joke);
        jokeListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {

        super.onResume();

        /**
         * check SharedPreferences for saved text.
         * ---If there is no text, use empty String.
         */
//        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        jokeEditText.setText(preferences.getString(SAVED_TEXT_KEY, ""));

    }

    /**
     * when the app pauses, save the text from the EditText widget.
     */

    @Override
    protected void onPause() {

        super.onPause();

        /**
         * Use SharedPreferences to save the text from jokeEditText
         */
//        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//
//        preferences.edit().putString(SAVED_TEXT_KEY, jokeEditText.getText().toString()).commit();

    }

    /**
     * Saving instane state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(SAVED_TEXT_KEY, jokeEditText.getText().toString());
    }

    /**
     * Restoring instance state.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final String jokeText = savedInstanceState.getString(SAVED_TEXT_KEY, "");
        jokeEditText.setText(jokeText);
    }
}
