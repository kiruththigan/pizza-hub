package models;

abstract class ToppingDecorator implements PizzaComponent {
    protected PizzaComponent decoratedPizza;

    public ToppingDecorator(PizzaComponent decoratedPizza) {
        this.decoratedPizza = decoratedPizza;
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost();
    }
}
