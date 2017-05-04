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
    public static final int SHOW_ALL = 3; // This value is for our filter

    private String text;
    private int rating;
    private long id;

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


    /**
     * Constructor for the CursorAdapter
     */
    public Joke(final String text, final int rating, final long id) {

        this.text = text;
        this.rating = rating;
        this.id = id;

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

    public long getId(){ return id; }

    public void setId(final long id) { this.id = id; }

    /**
     *
     * @return a String containing only the text of the Joke.
     */
    @Override
    public String toString() {
        return getText();
    }

    /**
     * An Object is equal to this Joke, if the object is a Joke, and
     * its id is the same as this Joke's id.
     */
    @Override
    public boolean equals(Object object) {
        //Notes:
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//
//        Joke joke = (Joke) object;
//
//        return text != null ? text.equals(joke.text) : joke.text == null;

        return (object instanceof Joke)
                && (((Joke) object).getId() == id);

    }
}
