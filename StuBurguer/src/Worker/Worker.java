package Worker;

import Model.Dish;
import Enum.DishType;
import Model.Feedback;
import Persistence.FileManagement;

import java.io.*;
import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

public class Worker {

    private static final String FILE_DISHES = "dishes";
    private static final String FILE_FEEDBACK = "feedback";

    public static Dish getDishByName(ArrayList<Dish> dishes, String name) {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getName().equals(name)) return dishes.get(i);
        }
        return null;
    }

    public static String askString(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine();
                if (answer.equals("")) {
                    System.out.println("You must write something");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (answer.equals(""));
        return answer;
    }

    public static String askStringToLowerCase(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine().toLowerCase();
                if (answer.equals("")) System.out.println("You must write something");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (answer.equals(""));
        return answer;
    }

    public static String askStringToUpperCase(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine().toUpperCase();
                if (answer.equals("")) System.out.println("You must write something");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (answer.equals(""));
        return answer;
    }

    public static String askString(String message, String a, String b) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            answer = askString(message);
            if (!answer.equalsIgnoreCase(a) && !answer.equalsIgnoreCase(b)) {
                System.out.println("ERROR! Write " + a + " or " + b + " please");
            }
        } while (!answer.equalsIgnoreCase(a) && !answer.equalsIgnoreCase(b));
        return answer;
    }

    public static String askString(String message, ArrayList<String> wordsAccepted) {
        String answer;
        boolean isWordOk;
        do {
            for (String word : wordsAccepted) {
                System.out.println(word);
            }
            answer = Worker.askString(message);
            isWordOk = wordIsOk(answer, wordsAccepted);
            if (!isWordOk) {
                System.out.println("Wrong answer!");
            }
        } while (!isWordOk);
        return answer;
    }

    private static boolean wordIsOk(String word, ArrayList<String> wordsAccepted) {
        for (String w : wordsAccepted) {
            if (w.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public static int askInt(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(message);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error input / output.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please, write integer number.");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int askInt(String message, int min, int max) {
        int num;
        do {
            num = askInt(message);
            if (num < min || num > max) {
                System.out.println("Unavailable option. Choose between STARTER(1), MAIN(2), DESSERT(3)");
            }
        } while (num < min || num > max);
        return num;
    }

    public static double askDouble(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = 0;
        boolean error;
        do {
            try {
                System.out.println(message);
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error input / output.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please, write double number.");
                error = true;
            }
        } while (error);
        return num;
    }

    public static double askDouble(String message, int min, int max) {
        double num;
        do {
            num = askDouble(message);
            if (num < min || num > max) {
                System.out.println("Error, the number must be between " + min + " and " + max);
            }
        } while (num < min || num > max);
        return num;
    }

    public static DishType askEnumDishType(String message) {
        DishType type = null;
        do {
            int option = askInt(message, 1, 3);
            switch (option) {
                case 1:
                    type = DishType.STARTER;
                    break;
                case 2:
                    type = DishType.MAIN;
                    break;
                case 3:
                    type = DishType.DESSERT;
                    break;
                default:
            }
        } while (type == null);
        return type;
    }

    public static ArrayList<Feedback> feedbacks() throws IOException {
        File fileDish = new File(FileManagement.route() + FILE_FEEDBACK + ".txt");
        FileReader frDish = new FileReader(fileDish);
        BufferedReader br = new BufferedReader(frDish);

        String line;
        ArrayList<Feedback> feedback = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0];
            double grade = Double.parseDouble(data[1]);
            String comment = data[2];
            feedback.add(new Feedback(name, grade, comment));
        }
        return feedback;
    }
}
