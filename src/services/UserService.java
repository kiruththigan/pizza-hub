package services;

import java.util.List;
import java.util.Scanner;

import data.UserRepository;
import models.ScannerInstance;
import models.User;

public class UserService {
    private UserRepository userRepository = UserRepository.getInstance();

    private static volatile UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public void registerUser() {
        Scanner scanner = ScannerInstance.getInstance();
        System.out.println("Enter your username : ");

        String username = scanner.nextLine();

        if (username == null) {
            System.out.println("Username not valid.");
        } else if (userRepository.findUserByUsername(username) != null) {
            System.out.println("Username already exists.");
        } else {
            List<User> users = userRepository.getAllUsers();
            User user = new User(users.size() + 1, username);
            userRepository.addUser(user);
        }
    }

    public void viewAllUsers() {
        List<User> users = userRepository.getAllUsers();
        if (users.size() == 0) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users : ");
            for (User user : users) {
                System.out.println(user.getId() + " : " + user.getUsername() + " - " + user.getLoyaltyPoints());
            }
        }
    }

    public void viewUserById() {
        Scanner scanner = ScannerInstance.getInstance();
        System.out.println("Enter user id : ");
        int id = scanner.nextInt();
        User user = userRepository.findUserById(id);
        if (user == null) {
            System.out.println("User not found.");
        } else {
            System.out.println("Id              : " + user.getId());
            System.out.println("Username        : " + user.getUsername());
            System.out.println("Loyalty Points  : " + user.getLoyaltyPoints());
            System.out.println("Favorite Pizzas : ");
            if (user.getFavoritePizzas().size() == 0) {
                System.out.println("No favorite pizzas found.");
            } else {
                user.getFavoritePizzas().forEach(pizza -> System.out.println(pizza.toString()));
            }
        }
    }

    public void updateUsername() {
        Scanner scanner = ScannerInstance.getInstance();
        System.out.println("Enter user id : ");
        int id = scanner.nextInt();
        User user = userRepository.findUserById(id);
        if (user == null) {
            System.out.println("User not found.");
        } else {
            System.out.println("Enter new username : ");
            String username = scanner.next();
            user.setUsername(username);
            userRepository.updateUser(user);
        }
    }
}