package commands;

import java.util.HashMap;
import java.util.Map;

public class MenuHandler {
    private final Map<Integer, Command> commands = new HashMap<>();

    public void addCommand(int option, Command command) {
        commands.put(option, command);
    }

    public void executeCommand(int option) {
        Command command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid option selected!");
        }
    }
}
