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
                    String[] data = line.split(", ");
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
                    String[] data = line.split(";");
                    Dish dish = Worker.getDishByName(dishes, data[0]);
                    Feedback feedback = new Feedback(data[1], Double.parseDouble(data[2]), data[3]);
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

    public static void saveData(ArrayList<Dish> dish, String name) {
        File fileDish = new File(route() + FILE_DISHES + ".txt");
        File fileFeedback = new File(route() + FILE_FEEDBACK + ".txt");
        try {
            BufferedWriter bwDish = new BufferedWriter(new FileWriter(fileDish, true));
            BufferedWriter bwFeedback = new BufferedWriter(new FileWriter(fileFeedback, true));
            PrintWriter printWriter = new PrintWriter(fileDish);
            FileReader frDish = new FileReader(fileDish);
            BufferedReader br = new BufferedReader(frDish);

            String line;
            if (fileDish.exists()) {
                // TODO: Do the part if the name of the dish exists don't append it.
                //while ((line = br.readLine()) != null) {
                //    if (line.isEmpty()) break;
                //    if (line.split(":")[1].split("; ")[0].equals(name)) {
                //        System.out.println("You can't create another plate, this plate already exists. Try again.");
                //        break;
                //    }
                //}

                for (Dish d : dish) {
                    bwDish.write(d.toString());
                    bwDish.newLine();
                    if (!d.getFeedback().isEmpty()) {
                        for (Feedback f : d.getFeedback()) {
                            printWriter.println(d.getName() + "," + f.toString());
                            bwDish.newLine();
                        }
                    }
                }
            }
            bwDish.close();
            bwFeedback.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
