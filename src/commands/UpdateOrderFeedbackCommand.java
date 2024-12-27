package commands;

import controllers.OrderController;

public class UpdateOrderFeedbackCommand implements Command {
    final OrderController orderController = OrderController.getInstance();

    @Override
    public void execute() {
        orderController.updateOrderFeedback();
    }
}