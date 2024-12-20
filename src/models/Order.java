package models;

public class Order {
    private int id;
    private Pizza pizza;
    private String status;
    private User user;

    public Order(int id, Pizza pizza, User user) {
        this.id = id;
        this.pizza = pizza;
        this.user = user;
        this.status = "placed";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
