package models;

public class PickupState implements OrderState {

    @Override
    public void updateState(Order order) {
        System.out.println("Order is now in the 'Ready for pickup' state.");
        order.setState(new DeliveredState());
    }

}
