package services;

import java.util.List;
import java.util.Scanner;

import data.OrderRepository;
import data.UserRepository;
import models.BasicPizza;
import models.CreditCardPayment;
import models.DeliveryFeeHandler;
import models.DigitalWalletPayment;
import models.ExtraCheeseDecorator;
import models.Order;
import models.OrderHandler;
import models.PaymentStrategy;
import models.Pizza;
import models.PizzaComponent;
import models.PromotionHandler;
import models.PromotionStrategy;
import models.ScannerInstance;
import models.SeasonalPromotion;
import models.SpecialPackagingDecorator;
import models.ToppingValidationHandler;
import models.User;
import models.OrderStatusNotifier;

public class OrderService {
    private OrderRepository orderRepository = OrderRepository.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();
    private OrderStatusNotifier notifier = new OrderStatusNotifier();

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
        Scanner scanner = ScannerInstance.getInstance();

        User user = selectUser(scanner);
        if (user == null) {
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
            } else {
                pizza = buildPizza(scanner);
            }
        } else {
            pizza = buildPizza(scanner);
        }

        PizzaComponent basicPizza = new BasicPizza();

        // Add enhancements using decorators
        System.out.println("Do you want to add extra cheese? (yes/no)");
        String extraCheese = scanner.nextLine();
        if (extraCheese.equalsIgnoreCase("yes")) {
            basicPizza = new ExtraCheeseDecorator(basicPizza);
        }

        System.out.println("Do you want special packaging? (yes/no)");
        String specialPackaging = scanner.nextLine();
        if (specialPackaging.equalsIgnoreCase("yes")) {
            basicPizza = new SpecialPackagingDecorator(basicPizza);
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
            userRepository.updateUser(user);
        }

        Order order = new Order(orderRepository.getAllOrders().size() + 1, pizza, qty, user, isDelevery);

        orderRepository.addOrder(order);

        OrderHandler toppingValidation = new ToppingValidationHandler();
        toppingValidation.handle(order);

        System.out.println("\n############# Your order review #############");
        System.out.println("Id       : " + order.getId());
        System.out.println("Pizza    : " + order.getPizza().toString());
        System.out.println("Quantity : " + order.getQty());
        System.out.println("Delevery : " + (order.isDelivery() ? "Delevery" : "Pickup"));
        System.out.println("Bill     : " + order.getQty() * basicPizza.getCost());
        System.out.println("User     : " + order.getUser().getUsername());

        System.out.println("\nPay the bill : " + ((order.getQty() * +basicPizza.getCost())));
        System.out.println("1 : Credit Card");
        System.out.println("2 : Digital Wallets");
        System.out.println("Select your payment method");
        int paymentMethod = scanner.nextInt();

        System.out.println(basicPizza.getDescription() + " costs $" + basicPizza.getCost());

        PromotionStrategy promotion = new SeasonalPromotion();
        double discountedAmount = promotion.applyDiscount(order.getQty() * basicPizza.getCost(), user);

        if (paymentMethod == 1) {
            PaymentStrategy payment = new CreditCardPayment();
            payment.pay(discountedAmount);
        } else {
            PaymentStrategy payment = new DigitalWalletPayment();
            payment.pay(discountedAmount);
        }
        order.setTotalBill(discountedAmount);
        orderRepository.updateOrder(order);
        
        user.setLoyaltyPoints(discountedAmount / 100);

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
        notifier.clearObservers();
    }

    public User selectUser(Scanner scanner) {

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
            return null;
        }

        return user;
    }

    private Pizza buildPizza(Scanner scanner) {
        System.out.println("\n******** Build your own pizza. ********\n");
        System.out.println("Enter the pizza name.");
        String name = scanner.nextLine();

        System.out.println("Enter the pizza crust.");
        String crust = scanner.nextLine();

        System.out.println("Enter the pizza sauce.");
        String sauce = scanner.nextLine();

        System.out.println("Enter the pizza toppings.");
        System.out.println("Ex :-  pepperoni, mushrooms, onions, bacon");
        String toppings = scanner.nextLine();

        System.out.println("Enter the pizza cheese.");
        String cheese = scanner.nextLine();

        return new Pizza.Builder()
                .setId(orderRepository.getAllOrders().size() + 1)
                .setName(name)
                .setCrust(crust)
                .setSauce(sauce)
                .addToppings(toppings)
                .setCheese(cheese)
                .build();
    }

    public void viewAllOrders() {
        List<Order> orders = orderRepository.getAllOrders();
        if (orders.size() == 0) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Orders : ");
            for (Order order : orders) {
                System.out.println(order.getId() + " : " + order.getPizza().toString() + " - " + order.getQty());
            }
        }
    }

    public void viewOrderById() {
        Scanner scanner = ScannerInstance.getInstance();
        System.out.println("Enter order id : ");
        int id = scanner.nextInt();
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println("Id       : " + order.getId());
            System.out.println("Pizza    : " + order.getPizza().toString());
            System.out.println("Quantity : " + order.getQty());
            System.out.println("Delevery : " + (order.isDelivery() ? "Delevery" : "Pickup"));
            System.out.println("User     : " + order.getUser().getUsername());
            System.out.println("Bill     : " + order.getTotalBill());
            System.out.println("Rating   : " + order.getRatings());
            System.out.println("Review   : " + order.getReview());
        }
    }

    public void updateOrderRating() {
        Scanner scanner = ScannerInstance.getInstance();
        System.out.println("Enter order id : ");
        int id = scanner.nextInt();
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println("Enter new rating(1-low, 5-high) : ");
            int rating = scanner.nextInt();
            order.setRatings(rating);
            orderRepository.updateOrder(order);
        }
    }

    public void updateOrderFeedback() {
        Scanner scanner = ScannerInstance.getInstance();
        System.out.println("Enter order id : ");
        int id = scanner.nextInt();
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println("Enter feedback : ");
            scanner.nextLine();
            String feedback = scanner.nextLine();
            order.setReview(feedback);
            orderRepository.updateOrder(order);
        }
    }
}
