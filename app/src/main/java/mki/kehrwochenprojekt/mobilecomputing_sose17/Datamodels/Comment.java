package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;

/**
 * Created by Alex on 22.06.2017.
 */

public abstract class Comment {
    protected String authorName;
    protected String text;

    public Comment(String authorName, String text) {
        this.authorName = authorName;
        this.text = text;

    }


    public String getAuthorName() {
        return authorName;
    }

    public String getText() {
        return text;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setText(String text) {
        this.text = text;
    }
}
