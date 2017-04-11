package edu.cvtc.android.jokelist;

/**
 * Created by Greg on 4/11/2017.
 */

public class Joke {

    /**
     *Three possible ratings for a Joke.
     */
    public static final int UNRATED = 0;
    public static final int LIKE = 1;
    public static final int DISLIKE = 2;

    private String text;
    private int rating;

    public Joke() {

        text = "";
        rating = UNRATED;

    }

    public Joke(final String text) {

        this.text = text;
        rating = UNRATED;

    }

    public Joke(final String text, final int rating) {

        this.text = text;
        this.rating = rating;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return a String containing only the text of the Joke.
     */
    @Override
    public String toString() {
        return getText();
    }

    @Override
    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//
//        Joke joke = (Joke) object;
//
//        return text != null ? text.equals(joke.text) : joke.text == null;

        return object instanceof Joke
                && ((Joke) object).getText().equals(text);

    }
}
