import java.util.Scanner;

import commands.MenuHandler;
import commands.PlaceOrderCommand;
import commands.RegisterUserCommand;
import commands.UpdateOrderFeedbackCommand;
import commands.UpdateOrderRatingCommand;
import commands.UpdateUsernameCommand;
import commands.ViewAllOrdersCommand;
import commands.ViewAllUsersCommand;
import commands.ViewOrderByIdCommand;
import commands.ViewUserByIdCommand;

import models.ScannerInstance;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = ScannerInstance.getInstance();

        boolean loop = true;

        while (loop) {
            System.out.println("\n===== Pizza Hub =====");
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

            MenuHandler menuHandler = new MenuHandler();

            menuHandler.addCommand(1, new RegisterUserCommand());
            menuHandler.addCommand(2, new ViewAllUsersCommand());
            menuHandler.addCommand(3, new ViewUserByIdCommand());
            menuHandler.addCommand(4, new UpdateUsernameCommand());
            menuHandler.addCommand(5, new PlaceOrderCommand());
            menuHandler.addCommand(6, new ViewAllOrdersCommand());
            menuHandler.addCommand(7, new ViewOrderByIdCommand());
            menuHandler.addCommand(8, new UpdateOrderRatingCommand());
            menuHandler.addCommand(9, new UpdateOrderFeedbackCommand());

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) {
                    loop = false;
                    scanner.close();
                } else {
                    menuHandler.executeCommand(choice);
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }

        }
    }
}
