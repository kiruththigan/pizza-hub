package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private List<Pizza> favoritePizzas;

    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.favoritePizzas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFavoritePizza(Pizza pizza) {
        this.favoritePizzas.add(pizza);
    }

    public void removeFavoritePizza(Pizza pizza) {
        if (favoritePizzas.contains(pizza)) {
            favoritePizzas.remove(pizza);
            System.out.println(pizza.getName() + " removed from favorites");
        } else {
            System.out.println(pizza.getName() + " is not in favorites");
        }
    }

    public void displayFavoritePizzas() {
        System.out.println("Favorites pizzas for " + username + " : ");
        for (Pizza pizza : favoritePizzas) {
            pizza.displayPizza();
        }
    }
}
