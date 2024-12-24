package services;

import java.util.List;
import java.util.Scanner;

import data.OrderRepository;
import data.UserRepository;
import models.CreditCardPayment;
import models.DigitalWalletPayment;
import models.Order;
import models.PaymentStrategy;
import models.Pizza;
import models.PromotionStrategy;
import models.SeasonalPromotion;
import models.User;
import models.OrderStatusNotifier;

public class OrderService {
    private OrderRepository orderRepository = OrderRepository.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();
    private OrderStatusNotifier notifier = new OrderStatusNotifier();

    double price = 1000.0;

    private static volatile OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderService.class) {
                if (instance == null) {
                    instance = new OrderService();
                }
            }
        }
        return instance;
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);

        // Display all users
        List<User> users = userRepository.getAllUsers();
        System.out.println("Available users:");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getUsername());
        }

        System.out.println("Enter your user ID.");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userRepository.findUserById(userId);
        if (user == null) {
            System.out.println("User not found. Please try again.");
            return;
        }

        Pizza pizza = null;

        if (user.getFavoritePizzas().size() > 0) {
            System.out.println("Do you want to order your favorite pizzas? (yes/no)");
            String seeFavorites = scanner.nextLine();
            if (seeFavorites.equalsIgnoreCase("yes")) {
                List<Pizza> favoritePizzas = user.getFavoritePizzas();
                System.out.println("Favorite pizzas:");
                for (Pizza favoritePizza : favoritePizzas) {
                    System.out.println(favoritePizza.toString());
                }
                System.out.println("Enter your Pizza ID.");
                int pizzaId = scanner.nextInt();
                pizza = user.getFavoritePizzas().stream().filter(p -> p.getId() == pizzaId).findFirst().orElse(null);
                scanner.nextLine();
            }
        } else {
            System.out.println("\n******** Build your own pizza. ********");

            System.out.println("Enter the pizza name.");
            String name = scanner.nextLine();

            System.out.println("Enter the pizza crust.");
            String crust = scanner.nextLine();

            System.out.println("Enter the pizza sauce.");
            String sauce = scanner.nextLine();

            System.out.println("Enter the pizza toppings.");
            System.out.println("Ex :-  pepperoni,pepperoni");
            String toppings = scanner.nextLine();

            System.out.println("Enter the pizza cheese.");
            String cheese = scanner.nextLine();

            pizza = new Pizza.Builder()
                    .setId(orderRepository.getAllOrders().size() + 1)
                    .setName(name)
                    .setCrust(crust)
                    .setSauce(sauce)
                    .addToppings(toppings)
                    .setCheese(cheese)
                    .build();

        }

        System.out.println("Enter the Quantity.");
        int qty = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the delevery option(pickup/delevery).");
        String deleveryOption = scanner.nextLine();

        boolean isDelevery = deleveryOption.equalsIgnoreCase("delevery");

        System.out.println("Do you want add favorite pizza? (yes/no)");
        String favorite = scanner.nextLine();

        if (favorite.equalsIgnoreCase("yes")) {
            user.addFavoritePizza(pizza);
            user.setLoyaltyPoints(qty);
            userRepository.updateUser(user);
        }

        Order order = new Order(orderRepository.getAllOrders().size() + 1, pizza, qty, user, isDelevery);

        orderRepository.addOrder(order);

        System.out.println("\n############# Your order review #############");
        System.out.println("Id       : " + order.getId());
        System.out.println("Pizza    : " + order.getPizza().toString());
        System.out.println("Quantity : " + order.getQty());
        System.out.println("Delevery : " + (order.isDelivery() ? "Delevery" : "Pickup"));
        System.out.println("Bill     : " + order.getQty() * price);
        System.out.println("User     : " + order.getUser().getUsername());

        System.out.println("\nPay the bill : " + order.getQty() * price);
        System.out.println("1 : Credit Card");
        System.out.println("2 : Digital Wallets");
        System.out.println("Select your payment method");
        int paymentMethod = scanner.nextInt();

        PromotionStrategy promotion = new SeasonalPromotion();
        double discountedAmount = promotion.applyDiscount(order.getQty() * price);

        if (paymentMethod == 1) {
            PaymentStrategy payment = new CreditCardPayment();
            payment.pay(discountedAmount);
        } else {
            PaymentStrategy payment = new DigitalWalletPayment();
            payment.pay(discountedAmount);
        }

        notifier.registerObserver(user);

        order.updateState();
        notifier.setStatus("Order Received");

        order.updateState();
        notifier.setStatus("Preparing");

        if (isDelevery) {
            order.updateState();
            notifier.setStatus("Out for Delivery");
        } else {
            notifier.setStatus("Ready for Pickup");
            order.updateState();
        }
        order.updateState();
        notifier.setStatus("Delivered");

    }
}
