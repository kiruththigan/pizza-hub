package models;

public class Order {
    private int id;
    private Pizza pizza;
    private String status;

    Order(int id, Pizza pizza) {
        this.id = id;
        this.pizza = pizza;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
