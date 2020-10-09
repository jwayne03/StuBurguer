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
                int option = Integer.parseInt(read.readLine());

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
                    default:
                        System.out.println("You need to choose an option");
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        dishes = new ArrayList<>();
        FileManagement.getDefaultInfo(dishes);
    }

    private void registerPlate() {

           /* fileWriter = new FileWriter("plate.txt", true);
            printWriter = new PrintWriter(fileWriter);
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            boolean exit = false;*/

            System.out.println("Create dish:");
            String name = Worker.askString("Name of the plate: ");
            DishType type = Worker.askEnumDishType("TYPE (STARTER(1), MAIN(2), DESSERT(3))");
            Double price = Worker.askDouble("Price of the plate: ");
            Dish dish = new Dish(name,type,price);
            dishes.add(dish);
            FileManagement.saveData(dishes,name);
            /*System.out.println("- Register a plate");
            System.out.println("Introduce the name of the plate");
            String namePlate = read.readLine();

            if (file.exists()) {
                String line;

                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) break;

                    System.out.println(line);

                    if (line.split(": ")[1].split("; ")[0].equals(namePlate)) {
                        System.out.println("You can't create another plate, this plate already exists. Try again.");
                    }
                }
            } else {
                System.out.println("The file doesn't exist");
            }

            System.out.println("Introduce the price of the plate");
            double price = Double.parseDouble(read.readLine());

            System.out.println("Introduce the type:\n"
                    + "1 - Starter\n"
                    + "2 - Principal\n"
                    + "3 - Dessert\n");

            int typeOfPlate = Integer.parseInt(read.readLine());

            switch (typeOfPlate) {
                case 1:
                    String starter = "Starter";
                    printWriter.println("Name: " + namePlate + "; Type of Plate: " + starter + "; Price: " + price);
                    break;
                case 2:
                    String principal = "Principal";
                    printWriter.println("Name: " + namePlate + "; Type of Plate: " + principal + "; Price: " + price);
                    break;
                case 3:
                    String dessert = "Dessert";
                    printWriter.println("Name: " + namePlate + "; Type of Plate: " + dessert + "; Price: " + price);
                    break;
                default:
                    System.out.println("You need to introduce a Number option. Try Again.");
                    break;
            }

            feedback.valoration();*/


    }

    private void feedBack() {
        try {
            fileWriter = new FileWriter("valoration.txt",true);
            printWriter = new PrintWriter(fileWriter);

            System.out.println("Punctuation of the plate:");
            double punctuation = Double.parseDouble(read.readLine());

            System.out.println("Comment the plate, it was good?");
            String comment = read.readLine();

            printWriter.println("Punctuation: " + punctuation + " Comment: " + comment);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void consultPlates() {

    }

    private void deletePlate() {

    }

    private void modifyPlate() {

    }

    private void showAllData() {

    }

    private void showMenu() {
        System.out.println("Welcome to Stuburguer");
        System.out.println("What do you want to do?");
        System.out.println("1 - Register a plate\n"
                + "2 - Give feed back to a plate\n"
                + "3 - Consult the list of plates\n"
                + "4 - Delete a plate\n"
                + "5 - Modify the plate\n"
                + "6 - Show all data\n");
    }

}
