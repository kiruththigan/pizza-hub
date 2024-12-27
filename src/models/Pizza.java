package models;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private int id;
    private String name;
    private String crust;
    private String sauce;
    private List<String> toppings;
    private String cheese;

    private Pizza(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.cheese = builder.cheese;
    }

    public static class Builder {
        private int id;
        private String name;
        private String crust;
        private String sauce;
        private List<String> toppings = new ArrayList<>();
        private String cheese;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public Builder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder addToppings(String toppingsInput) {
            String[] toppings = toppingsInput.split(",");
            for (String topping : toppings) {
                this.toppings.add(topping);
            }
            return this;
        }

        public Builder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    // public String displayPizza() {
    // System.out.println("pizza : " + name);
    // return name;
    // }

    public int getId() {
        return id;
    }

    public List<String> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return "Pizza [id=" + id + ", name=" + name + ", crust=" + crust + ", sauce=" + sauce + ", toppings="
                + toppings + ", cheese=" + cheese + "]";
    }

}