package edu.cvtc.android.jokelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

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
//        setContentView(R.layout.activity_main);
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



        setContentView(container);

    }
}
