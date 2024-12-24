package models;

public class PlacedState implements OrderState {

    @Override
    public void updateState(Order order) {
        System.out.println("Order is now in the 'Placed' state.");
        order.setState(new PreparingState());
    }

}
