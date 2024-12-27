package commands;

import controllers.OrderController;

public class ViewOrderByIdCommand implements Command {
    final OrderController orderController = OrderController.getInstance();

    @Override
    public void execute() {
        orderController.viewOrderById();
    }
}
