package Model;

import java.util.ArrayList;

import Enum.DishType;

/**
 * @author John Wayne Carreon
 */

public class Dish {

    private String name;
    private Double price;
    private DishType type;
    private ArrayList<Feedback> feedback;

    public Dish(String name, DishType type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.feedback = new ArrayList<>();
    }

    public ArrayList<Feedback> getFeedback() {
        return feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public double getAverageFeedback() {
        if (!this.feedback.isEmpty()) {
            double total = 0;
            for (Feedback feedback : this.feedback) {
                total += feedback.getGrade();
            }
            return total / this.feedback.size();
        } else {
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return getName() + "," + getType().toString() + "," + getPrice();
    }
}
