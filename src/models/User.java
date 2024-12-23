package models;

import java.util.ArrayList;
import java.util.List;

public class User implements Observer {
    private int id;
    private String username;
    private List<Pizza> favoritePizzas;
    private double loyaltyPoints;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.favoritePizzas = new ArrayList<>();
    }

    public User(int id, String username, List<Pizza> favoritePizzas, double loyaltyPoints) {
        this.id = id;
        this.username = username;
        this.favoritePizzas = favoritePizzas;
        this.loyaltyPoints = loyaltyPoints;
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

    public List<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public void setFavoritePizzas(List<Pizza> favoritePizzas) {
        this.favoritePizzas = favoritePizzas;
    }

    public void addFavoritePizza(Pizza pizza) {
        this.favoritePizzas.add(pizza);
    }

    public void removeFavoritePizza(Pizza pizza) {
        if (favoritePizzas.contains(pizza)) {
            favoritePizzas.remove(pizza);
            // System.out.println(pizza.displayPizza() + " removed from favorites");
        } else {
            System.out.println("pizza is not in favorites");
        }
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints += loyaltyPoints;
    }

    public void displayFavoritePizzas() {
        System.out.println("Favorites pizzas for " + username + " : ");
        for (Pizza pizza : favoritePizzas) {
            pizza.toString();
        }
    }

    @Override
    public void update(String status) {
        System.out.println("Notification to " + username + ": Your order is now " + status);
    }

}
