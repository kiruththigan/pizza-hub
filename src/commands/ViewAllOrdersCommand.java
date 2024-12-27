package commands;

import controllers.OrderController;

public class ViewAllOrdersCommand implements Command {
    final OrderController orderController = OrderController.getInstance();

    @Override
    public void execute() {
        orderController.viewAllOrders();
    }
}
