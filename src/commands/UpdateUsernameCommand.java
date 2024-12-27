package commands;

import controllers.UserController;

public class UpdateUsernameCommand implements Command {
    final UserController userController = UserController.getInstance();

    @Override
    public void execute() {
        userController.updateUsername();
    }
}