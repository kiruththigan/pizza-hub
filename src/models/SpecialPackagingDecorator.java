package models;

public class SpecialPackagingDecorator extends ToppingDecorator {
    public SpecialPackagingDecorator(PizzaComponent decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Special Packaging";
    }

    @Override
    public double getCost() {
        return super.getCost() + 200; 
    }
}
