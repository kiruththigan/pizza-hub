import java.util.Scanner;

import controllers.OrderController;
import controllers.UserController;

public class App {
    public static void main(String[] args) throws Exception {
        final UserController userController = UserController.getInstance();
        final OrderController orderController = OrderController.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n=== Pizza Hub ===");
            System.out.println("1   : New User");
            System.out.println("2   : New Order");
            System.out.println("3   : View Appointments");
            System.out.println("4  : Search Appointment by date");
            System.out.println("5 : Search Appointment by id");
            System.out.println("6  : Search Appointment by name");
            System.out.println("7   : Generate Invoice");
            System.out.println("0   : Quit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userController.registerUser();
                    break;

                case "2":
                    orderController.placeOrder();
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                case "6":
                    break;

                case "7":
                    break;

                case "0":
                    scanner.close();
                    loop = false;
                    break;

                default:
                    System.out.println("Your input is not valid!");
                    break;
            }
        }

    }
}
