package models;

public class Order {
    private int id;
    private Pizza pizza;
    private int qty;
    private String status;
    private User user;
    private boolean isDelivery;

    public Order(int id, Pizza pizza, int qty, User user, boolean isDelivery) {
        this.id = id;
        this.pizza = pizza;
        this.user = user;
        this.status = "placed";
        this.qty = qty;
        this.isDelivery = isDelivery;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }
}
