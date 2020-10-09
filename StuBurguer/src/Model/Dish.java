package Model;

import java.util.ArrayList;
import Enum.DishType;

/**
 * @author John Wayne Carreon
 */

public class Dish {

    private String name;
    private DishType type;
    private Double price;
    private ArrayList<Feedback>feedbacks;


    public Dish(String name, DishType type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.feedbacks = new ArrayList<>();
    }

    public ArrayList<Feedback> getFeedBack() {
        return feedbacks;
    }
    public Dish() {

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

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", feedbacks=" + feedbacks +
                '}';
    }
}
