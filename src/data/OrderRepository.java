package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Order;

public class OrderRepository {
    private HashMap<Integer, List<Order>> orders = new HashMap<>();

    private static volatile OrderRepository instance;

    private OrderRepository() {
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            synchronized (OrderRepository.class) {
                if (instance == null) {
                    instance = new OrderRepository();
                }
            }
        }
        return instance;
    }

    public void addOrder(Order order) {
        List<Order> userOrders = orders.getOrDefault(order.getUser().getId(), new ArrayList<>());
        userOrders.add(order);
        orders.put(order.getUser().getId(), userOrders);
        System.out.println(order.getPizza().toString());
    }

    public Order findOrderById(int orderId) {
        for (List<Order> userOrders : orders.values()) {
            for (Order order : userOrders) {
                if (order.getId() == orderId) {
                    return order;
                }
            }
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        for (List<Order> userOrders : orders.values()) {
            allOrders.addAll(userOrders);
        }
        return allOrders;
    }

    public void updateOrder(Order order) {
        List<Order> userOrders = orders.get(order.getUser().getId());
        for (Order o : userOrders) {
            if (o.getId() == order.getId()) {
                o.setPizza(order.getPizza());
                o.setQty(order.getQty());
                o.setState(order.getState());
                o.setDelivery(order.isDelivery());
                o.setTotalBill(order.getTotalBill());
                o.setRatings(order.getRatings());
                o.setReview(order.getReview());
                System.out.println("Order " + order.getId() + " updated.");
                return;
            }
        }
    }
}
