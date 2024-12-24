package models;

public class OutForDeliveryState implements OrderState {

    @Override
    public void updateState(Order order) {
        System.out.println("Order is now in the 'Out for Delivery' state.");
        order.setState(new DeliveredState());
    }

}
