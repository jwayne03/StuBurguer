package Persistence;

import Model.Dish;
import Model.Feedback;
import Worker.Worker;

import Enum.DishType;

import java.io.*;
import java.util.ArrayList;

public class FileManagement {

    private static final String SEPARATOR = File.separator;
    private static final String FOLDER_DATA = "datos";
    private static final String FILE_DISHES = "dishes";
    private static final String FILE_FEEDBACK = "feedback";

    public static String route() {
        String route = System.getProperty("user.dir") + SEPARATOR + FOLDER_DATA;
        File folder = new File(route);
        if (!folder.exists()) folder.mkdir();
        return route + SEPARATOR;
    }

    public static void getDefaultInfo(ArrayList<Dish> dishes) {
        readFileDish(dishes);
        readFileFeedback(dishes);
    }

    public static void readFileDish(ArrayList<Dish> dishes) {
        File file = new File(route() + FILE_DISHES + ".txt");
        if (file.exists()) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line;
                while ((line = read.readLine()) != null) {
                    if (line.isEmpty()) break;
                    String[] data = line.split(",");
                    Dish dish = new Dish(data[0], DishType.valueOf(data[1]), Double.parseDouble(data[2]));
                    dishes.add(dish);
                }
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file doesn't exist");
        }
    }

    public static void readFileFeedback(ArrayList<Dish> dishes) {
        File file = new File(route() + FILE_FEEDBACK + ".txt");
        if (file.exists()) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line;
                while ((line = read.readLine()) != null) {
                    if (line.isEmpty()) break;
                    String[] data = line.split(",");
                    Dish dish = Worker.getDishByName(dishes, data[0]);
                    Feedback feedback = new Feedback(data[0], Double.parseDouble(data[1]), data[2]);
                    if (dish == null) break;
                    dish.getFeedback().add(feedback);
                }
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file doesn't exist");
        }
    }

    public static void saveDataDishes(ArrayList<Dish> dish, String name) {
        File fileDish = new File(route() + FILE_DISHES + ".txt");

        try {
            BufferedWriter bwDish = new BufferedWriter(new FileWriter(fileDish, true));

            if (fileDish.exists()) {
                for (Dish d : dish) {
                    bwDish.write(d.toString());
                    bwDish.newLine();
                }
            }
            bwDish.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDataFeedbacks(ArrayList<Feedback> feedbacks, ArrayList<Dish> dishes) {
        File fileFeedback = new File(route() + FILE_FEEDBACK + ".txt");
        try {
            BufferedWriter bwFeedback = new BufferedWriter(new FileWriter(fileFeedback, true));

            if (fileFeedback.exists()) {
                bwFeedback.write(feedbacks.get(feedbacks.size() - 1).toString());
                bwFeedback.newLine();
            }
            bwFeedback.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDataDish(ArrayList<Dish> dishes) {
        File fileDish = new File(route() + FILE_DISHES + ".txt");
        try {
            BufferedWriter bwDish = new BufferedWriter(new FileWriter(fileDish));

            if (fileDish.exists()) {
                for (Dish d : dishes) {
                    bwDish.write(d.toString());
                    bwDish.newLine();
                }
                bwDish.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
