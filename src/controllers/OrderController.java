package controllers;

import services.OrderService;

public class OrderController {
    private OrderService orderService = OrderService.getInstance();

    private static volatile OrderController instance;

    private OrderController() {
    }

    public static OrderController getInstance() {
        if (instance == null) {
            synchronized (OrderController.class) {
                if (instance == null) {
                    instance = new OrderController();
                }
            }
        }
        return instance;
    }

    public void placeOrder() {
        orderService.placeOrder();
    }
}
