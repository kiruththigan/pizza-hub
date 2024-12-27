import java.util.Scanner;

import controllers.OrderController;
import controllers.UserController;
import models.ScannerInstance;

public class App {
    public static void main(String[] args) throws Exception {
        final UserController userController = UserController.getInstance();
        final OrderController orderController = OrderController.getInstance();
        Scanner scanner = ScannerInstance.getInstance();

        scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n=== Pizza Hub ===");
            System.out.println("1   : New User");
            System.out.println("2   : View All Users");
            System.out.println("3   : View User by ID");
            System.out.println("4   : Update Username");
            System.out.println("5   : New Order");
            System.out.println("6   : View All Orders");
            System.out.println("7   : View Order by ID");
            System.out.println("8   : Update Order Rating ");
            System.out.println("9   : Update Order Feedback ");
            System.out.println("0   : Quit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userController.registerUser();
                    break;

                case "2":
                    userController.viewAllUsers();
                    break;

                case "3":
                    userController.viewUserById();
                    break;

                case "4":
                    userController.updateUsername();
                    break;

                case "5":
                    orderController.placeOrder();
                    break;

                case "6":
                    orderController.viewAllOrders();
                    break;

                case "7":
                    orderController.viewOrderById();
                    break;

                case "8":
                    orderController.updateOrderRating();
                    break;

                case "9":
                    orderController.updateOrderFeedback();
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
