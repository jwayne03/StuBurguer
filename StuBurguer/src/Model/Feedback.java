package Model;

import java.util.ArrayList;

/**
 *
 * @author John Wayne Carreon
 */

public class Feedback {
    private String name;
    private double grade;
    private String comment;

    public Feedback(String name, double grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Feedback(Feedback feedback) {
    }

    public void valoration() {

    }

    public double getGrade() {
        return grade;
    }

    public void setPunctuation(double punctuation) {
        this.grade = punctuation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Feedback: " +
                " Punctuation: " + grade +
                " Comment: " + comment;
    }
}
