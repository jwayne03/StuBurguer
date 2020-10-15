package Stuburguer;

import Model.Dish;
import Model.Feedback;
import Persistence.FileManagement;
import Worker.Worker;

import Enum.DishType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author John Wayne Carreon
 */

public class Stuburguer {

    private final File file = new File("plate.txt");
    private FileWriter fileWriter;
    private FileReader fileReader;
    private PrintWriter printWriter;
    private BufferedReader read;
    private BufferedReader br;
    private Dish dish;
    private Feedback feedback;
    private ArrayList<Dish> dishes;
    private ArrayList<Feedback> feedbacks;

    public void run() {
        read = new BufferedReader(new InputStreamReader(System.in));
        feedback = new Feedback(feedback);
        fileWriter = null;
        fileReader = null;
        printWriter = null;
        init();
        mainMenu();
    }

    private void mainMenu() {
        try {
            boolean exit = false;
            while (!exit) {
                showMenu();
                int option;
                do {
                    option = Integer.parseInt(read.readLine());
                } while (option < 0 && option > 8);


                switch (option) {
                    case 1:
                        registerPlate();
                        break;
                    case 2:
                        feedBack();
                        break;
                    case 3:
                        consultPlates();
                        break;
                    case 4:
                        deletePlate();
                        break;
                    case 5:
                        modifyPlate();
                        break;
                    case 6:
                        showAllData();
                        break;
                    case 7:
                        System.out.println("You chosed exit, Goodbye!");
                        exit = true;
                    default:
                        System.out.println("You need to choose an option");
                        break;
                }
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        dishes = new ArrayList<>();
        feedbacks = new ArrayList<>();
        FileManagement.getDefaultInfo(dishes);
    }

    private void registerPlate() {
        System.out.println("CREATING DISH......");
        String name = Worker.askStringToUpperCase("NAME: ");
        DishType type = Worker.askEnumDishType("TYPE (STARTER(1), MAIN(2), DESSERT(3)): ");
        Double price = Worker.askDouble("PRICE: ");
        Dish d = new Dish(name, type, price);
        dishes.add(d);
        FileManagement.saveDataDishes(dishes, name);
        System.out.println("DISH CREATED!!");
    }

    private void feedBack() throws IOException, NumberFormatException {
        int count = 1;

        for (Dish dish : dishes) {
            System.out.println(count + " - " + dish);
            count++;
        }
        System.out.println("What dish do you want to give feedback?");

        int option = Integer.parseInt(read.readLine());

        dishes.get(option - 1).getName();

        double grade = Worker.askDouble("GRADE: ", 0, 10);
        String comment = Worker.askString("Comment the plate, it was good?");
        Feedback feedback = new Feedback(dishes.get(option - 1).getName(), grade, comment);
        feedbacks.add(feedback);
        FileManagement.saveDataFeedbacks(feedbacks, dishes);
        System.out.println("FEEDBACK CREATED!!");
    }

    private void consultPlates() {
        System.out.println("SHOWING DISHES......");
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println((i + 1)
                    + ". " + dishes.get(i).toString() +
                    " --> " + dishes.get(i).getAverageFeedback(dishes.get(i).getName()) +
                    "(" + dishes.get(i).getFeedback().size() + ")");
        }
    }

//    private void showDishes() {
//        System.out.println("SHOWING DISHES......");
//        for (int i = 0; i < dishes.size(); i++) {
//            System.out.println((i + 1) + ". " +
//                    dishes.get(i).toString() + " --> " +
//                    dishes.get(i).getAverageFeedback() + "(" +
//                    dishes.get(i).getFeedback().size() + ")");
//        }
//    }

    private void deletePlate() {

    }

    private void modifyPlate() {

    }

    private void showAllData() {
        System.out.println("SHOWING ALL DATA......");
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println(dishes.toString());
            System.out.println((i + 1) + ". " + dishes.get(i).toString());
            for (Feedback feedback : dishes.get(i).getFeedback()) {
                System.out.println("\t" + feedback.toString());
            }
        }
    }

    private void showMenu() {
        System.out.println("Welcome to Stuburguer");
        System.out.println("What do you want to do?");
        System.out.println("1 - Register a plate\n"
                + "2 - Give feed back to a plate\n"
                + "3 - Consult the list of plates\n"
                + "4 - Delete a plate\n"
                + "5 - Modify the plate\n"
                + "6 - Show all data\n"
                + "7 - Exit\n");
    }
}
