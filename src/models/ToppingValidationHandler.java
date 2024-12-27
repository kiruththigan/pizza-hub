package models;

import java.util.List;

public class ToppingValidationHandler extends OrderHandler {
    private static final List<String> VALID_TOPPINGS = List.of("pepperoni", "mushrooms", "onions", "bacon");

    @Override
    public void handle(Order order) {
        boolean isValid = order.getPizza().getToppings().stream().allMatch(VALID_TOPPINGS::contains);
        if (isValid) {
            System.out.println("Toppings are valid.");
            if (nextHandler != null) {
                nextHandler.handle(order);
            }
        } else {
            System.out.println("Invalid toppings found. Order cannot be processed.");
        }
    }
}
