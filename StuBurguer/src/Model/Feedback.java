package Model;

import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

public class Feedback {

    private double punctuation;
    private String comment;

    public Feedback(double punctuation, String comment) {
        this.punctuation = punctuation;
        this.comment = comment;
    }


    public Feedback(Feedback feedback) {
    }

    public void valoration() {

    }

    public double getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(double punctuation) {
        this.punctuation = punctuation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
