package models;

public class PromotionHandler extends OrderHandler {
    @Override
    public void handle(Order order) {
        System.out.println("Checking for promotions...");
        if (order.getQty() > 2) {
            System.out.println("Promotion applied: $5 discount.");
        } else {
            System.out.println("No promotions applied.");
        }
        if (nextHandler != null) {
            nextHandler.handle(order);
        }
    }
}
