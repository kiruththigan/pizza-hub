package commands;

import controllers.UserController;

public class RegisterUserCommand implements Command {
    final UserController userController = UserController.getInstance();

    @Override
    public void execute() {
        userController.registerUser();
    }
}