package models;

public class ExtraCheeseDecorator extends ToppingDecorator {
    public ExtraCheeseDecorator(PizzaComponent decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return super.getCost() + 150; // Extra cost for cheese
    }
}
