package commands;

import controllers.UserController;

public class ViewUserByIdCommand implements Command {
    final UserController userController = UserController.getInstance();

    @Override
    public void execute() {
        userController.viewUserById();
    }
}
