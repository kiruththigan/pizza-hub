package services;

import java.util.List;
import java.util.Scanner;

import data.UserRepository;
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
        Scanner scanner = new Scanner(System.in);
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
}