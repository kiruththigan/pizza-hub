package models;

public class Pizza {
    private int id;
    private String name;
    private String crust;
    private String sauce;
    private String toppings;
    private String cheese;

    Pizza(int id, String name, String crust, String sauce, String toppings, String cheese) {
        this.id = id;
        this.name = name;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
        this.cheese = cheese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void displayPizza() {
        System.out.println("pizza : " + name);
    }

}