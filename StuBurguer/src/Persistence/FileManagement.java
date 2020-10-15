package Persistence;

import Model.Dish;
import Model.Feedback;
import Worker.Worker;

import Enum.DishType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
                    System.out.println(Arrays.toString(data));
                    Dish dish = new Dish(data[0], DishType.valueOf(data[1]), Double.parseDouble(data[2]));
                    dishes.add(dish);
                }
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
            PrintWriter printWriter = new PrintWriter(fileDish);
            FileReader frDish = new FileReader(fileDish);
            BufferedReader br = new BufferedReader(frDish);

            String line;
            if (fileDish.exists()) {
//                 TODO: Do the part if the name of the dish exists don't append it.
//                while ((line = br.readLine()) != null) {
//                    if (line.isEmpty()) break;
//                    if (line.split(":")[1].split("; ")[0].equals(name)) {
//                        System.out.println("You can't create another plate, this plate already exists. Try again.");
//                        break;
//                    }
//                }

                for (Dish d : dish) {
                    bwDish.write(d.toString());
                    bwDish.newLine();
//                        for (Feedback f : d.getFeedback()) {
//                            bwFeedback.write(d.getName() + "," + f.toString());
//                            bwDish.newLine();
//
//                    }
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
//            BufferedWriter bwDish = new BufferedWriter(new FileWriter(fileDish, true));
//            PrintWriter printWriter = new PrintWriter(fileDish);
//            FileReader frDish = new FileReader(fileDish);
//            BufferedReader br = new BufferedReader(frDish);


            BufferedWriter bwFeedback = new BufferedWriter(new FileWriter(fileFeedback, true));
//            PrintWriter printWriter = new PrintWriter(fileFeedback);
            FileReader frFeedback = new FileReader(fileFeedback);
//            BufferedReader br = new BufferedReader(frFeedback);

            String line;
            if (fileFeedback.exists()) {
                for (Feedback f : feedbacks) {
                    bwFeedback.write(f.toString());
                    bwFeedback.newLine();
                }

//                for (Dish d : dishes) {
//                    bwDish.write(d.toString());
//                    bwDish.newLine();
//                    if (!d.getFeedback().isEmpty()) {
//                        for (Feedback f : d.getFeedback()) {
//                            bwFeedback.write(d.getName() + "," + f.toString());
//                            bwDish.newLine();
//                        }
//                    }
//                }
            }
            bwFeedback.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
