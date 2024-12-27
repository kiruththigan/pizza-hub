package models;

import services.OrderService;

public class PlaceOrderCommand implements Command {
    private final OrderService orderService;

    public PlaceOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        orderService.placeOrder();
    }
}