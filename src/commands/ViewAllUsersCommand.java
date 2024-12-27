package commands;

import controllers.UserController;

public class ViewAllUsersCommand implements Command {
    final UserController userController = UserController.getInstance();

    @Override
    public void execute() {
        userController.viewAllUsers();
    }
}
