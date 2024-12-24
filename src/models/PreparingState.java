package models;

public class PreparingState implements OrderState {

    @Override
    public void updateState(Order order) {
        System.out.println("Order is now in the 'Prepairing' state.");
        order.setState(new OutForDeliveryState());
    }

}
