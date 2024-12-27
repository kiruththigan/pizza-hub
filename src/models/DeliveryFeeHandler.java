package models;

public class DeliveryFeeHandler extends OrderHandler {
    @Override
    public void handle(Order order) {
        if (order.isDelivery()) {
            System.out.println("Delivery option selected. Delivery charge: $10.");
        } else {
            System.out.println("Pickup option selected. No delivery charge.");
        }
        if (nextHandler != null) {
            nextHandler.handle(order);
        }
    }
}
