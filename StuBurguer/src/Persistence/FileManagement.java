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
        //readFileFeedback(dishes);
    }

    private static void readFileDish(ArrayList<Dish> dishes) {
    }


    public static void readFileDish(ArrayList<Dish> dishes, String namePlate) {
        File file = new File(route() + FILE_DISHES + ".txt");
        if (file.exists()) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line;
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(";");

                    if (line.isEmpty()) break;
                    System.out.println(line);
                    if (line.split(": ")[1].split("; ")[0].equals(namePlate)) {
                        System.out.println("You can't create another plate, this plate already exists. Try again.");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file doesn't exist");
        }
    }

    /*public static void readFileFeedback(ArrayList<Dish> dishes) {
        File file = new File(route() + FILE_FEEDBACK + ".txt");
        if (file.exists()) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line;
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(";");
                    Dish dish = Worker.getDishByName(dishes, data[0]);
                    Feedback feedback = new Feedback(Double.parseDouble(data[0]), data[1]);
                    dish.getFeedBack().add(feedback);
                }
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file doesn't exist");
        }
    }*/

    public static void saveData(ArrayList<Dish> dishes, String nameDish) {
        File fileDish = new File(route() + FILE_DISHES + ".txt");
        File fileFeedback = new File(route() + FILE_FEEDBACK + ".txt");


        try {
            FileWriter fwDish = new FileWriter(fileDish);
            FileWriter fwFeedBack = new FileWriter(fileFeedback);
            FileReader frDish = new FileReader(fileDish);
            FileReader frFeedback = new FileReader(fileFeedback);
            BufferedReader br = new BufferedReader(frDish);
            PrintWriter printWriter = new PrintWriter(fwDish);


            if (fileDish.exists()) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) break;

                    printWriter.println(dishes);
                    //TODO: BORRAR CHIVATO
                    System.out.println(dishes.toString());

                    if (line.split(": ")[1].split("; ")[0].equals(nameDish)) {
                        System.out.println("You can't create another plate, this plate already exists. Try again.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
