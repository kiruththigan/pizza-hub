package commands;

import controllers.OrderController;

public class PlaceOrderCommand implements Command {
    final OrderController orderController = OrderController.getInstance();

    @Override
    public void execute() {
        orderController.placeOrder();
    }
}
