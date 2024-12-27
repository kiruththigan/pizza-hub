package controllers;

import services.UserService;

public class UserController {
    private UserService userService = UserService.getInstance();

    private static volatile UserController instance;

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            synchronized (UserController.class) {
                if (instance == null) {
                    instance = new UserController();
                }
            }
        }
        return instance;
    }

    public void registerUser() {
        userService.registerUser();
    }

    public void viewAllUsers() {
        userService.viewAllUsers();
    }

    public void viewUserById() {
        userService.viewUserById();
    }

    public void updateUsername() {
        userService.updateUsername();
    }

}
