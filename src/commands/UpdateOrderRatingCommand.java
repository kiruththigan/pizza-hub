package commands;

import controllers.OrderController;

public class UpdateOrderRatingCommand implements Command {
    final OrderController orderController = OrderController.getInstance();

    @Override
    public void execute() {
        orderController.updateOrderRating();
    }
}
