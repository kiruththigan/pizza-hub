package data;

import java.util.ArrayList;
import java.util.List;

import models.Pizza;
import models.User;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    private static volatile UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User : " + user.getUsername() + " id : " + user.getId() + " added successfully.");
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void updateUser(User user) {
        List<Pizza> pizzas = user.getFavoritePizzas();
        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setUsername(user.getUsername());
                u.setFavoritePizzas(pizzas);
                System.out.println("User updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

}
