package models;

public class BasicPizza implements PizzaComponent {

    @Override
    public String getDescription() {
        return "Basic Pizza";
    }

    @Override
    public double getCost() {
        return 1000;
    }

}
