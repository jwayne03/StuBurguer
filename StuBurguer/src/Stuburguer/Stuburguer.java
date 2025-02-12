package Stuburguer;

import Model.Dish;
import Model.Feedback;
import Persistence.FileManagement;
import Worker.Worker;

import Enum.DishType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

public class Stuburguer {

    private FileWriter fileWriter;
    private FileReader fileReader;
    private PrintWriter printWriter;
    private BufferedReader read;
    private BufferedReader br;
    private Dish dish;
    private Feedback feedback;
    private ArrayList<Dish> dishes;
    private ArrayList<Feedback> feedbacks;

    private void init() {
        read = new BufferedReader(new InputStreamReader(System.in));
        feedback = new Feedback(feedback);
        fileWriter = null;
        fileReader = null;
        printWriter = null;
        dishes = new ArrayList<>();
        feedbacks = new ArrayList<>();
        FileManagement.getDefaultInfo(dishes);
    }

    public void run() {
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
                    System.out.print("\nIntroduce an option:");
                    option = Integer.parseInt(read.readLine());

                    if (option > 0 || option < 8) {
                        System.out.println("You need to introduce a number from 1 to 7");
                    }
                } while (option < 0 || option > 8);

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

    private void registerPlate() {
        System.out.println("CREATING DISH......");
        String name = Worker.askStringToUpperCase("NAME: ");

        for (Dish dish : dishes) {
            if (name.equalsIgnoreCase(dish.getName())) {
                System.out.println("This dish already exist, try again.");
                return;
            }
        }

        DishType type = Worker.askEnumDishType("TYPE (STARTER(1), MAIN(2), DESSERT(3)): ");
        Double price = Worker.askDouble("PRICE: ");
        Dish d = new Dish(name, type, price);
        dishes.add(d);
        FileManagement.saveDataDishes(dishes);
        System.out.println("DISH CREATED!!");
    }

    private void feedBack() throws NumberFormatException {
        int count = 1;

        for (Dish dish : dishes) {
            System.out.println(count + " - " + dish);
            count++;
        }

        int option = Worker.askInt("What dish do you want to give feedback?");
        dishes.get(option - 1).getName();
        double grade = Worker.askDouble("GRADE: ", 0, 10);
        String comment = Worker.askString("Comment the plate, it was good?");
        Feedback feedback = new Feedback(dishes.get(option - 1).getName(), grade, comment);
        feedbacks.add(feedback);
        FileManagement.saveDataFeedbacks(feedbacks, dishes);
        System.out.println("FEEDBACK CREATED!!");
    }

    private void consultPlates() {
        try {
            System.out.println("SHOWING DISHES......");
            int count = 1;
            for (int i = 0; i < dishes.size(); i++) {
                System.out.println(count + " - " + dishes.get(i).toString()
                        + " -----> " + Dish.getAverageFeedback(dishes.get(i).getName()));
                count++;
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletePlate() throws IOException {
        System.out.println("What plate do you want to delete?");
        int count = 1;
        int countBefore = 1;

        for (Dish dish : dishes) {
            System.out.println(count + " - " + dish);
            count++;
        }

        int option = Worker.askInt("What dish do you want to remove?");
        String nameOfDish = dishes.get(option - 1).getName();
        if (nameOfDish.equalsIgnoreCase(dishes.get(option - 1).getName())) {
            dishes.remove(option - 1);
        }
        FileManagement.deleteDataDish(dishes);
        System.out.println("YOU HAVE DELETED A DISH SUCCESSFULLY");

        for (Dish dish : dishes) {
            System.out.println(countBefore + " - " + dish);
            countBefore++;
        }
    }

    private void modifyPlate() {
        System.out.println("What dish do you want to modify?");
        int count = 1;

        for (Dish dish : dishes) {
            System.out.println(count + " - " + dish);
            count++;
        }

        int option = Worker.askInt("What dish do you want to modify the price?");
        String nameOfDish = dishes.get(option - 1).getName();

        if (nameOfDish.equalsIgnoreCase(dishes.get(option - 1).getName())) {
            System.out.println("You have chosen: " + dishes.get(option - 1).getName());

            double newPrice = Worker.askDouble("Choose the price of the dish: ");
            Dish dish = new Dish(dishes.get(option - 1).getName(), dishes.get(option - 1).getType(), newPrice);

            dishes.add(dish);
            dishes.remove(option - 1);
        }

        FileManagement.modifyPriceDish(dishes);
        System.out.println("YOU HAVE MODIFIED A DISH SUCCESSFULLY");
    }

    private void showAllData() {
        System.out.println("SHOWING ALL DATA......");
        for (Dish value : dishes) {
            System.out.println(value.toString());
            for (Feedback feedback : value.getFeedback()) {
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
                + "7 - Exit");
    }
}