package services;

import java.util.List;
import java.util.Scanner;

import data.OrderRepository;
import data.UserRepository;
import models.Order;
import models.Pizza;
import models.User;

public class OrderService {
    private OrderRepository orderRepository = OrderRepository.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();

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
            }
        } else {
            System.out.println("******** Build your own pizza. ********");

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

            System.out.println("Do you want add favorite pizza? (yes/no)");
            String favorite = scanner.nextLine();

            pizza = new Pizza.Builder()
                    .setId(orderRepository.getAllOrders().size() + 1)
                    .setName(name)
                    .setCrust(crust)
                    .setSauce(sauce)
                    .addToppings(toppings)
                    .setCheese(cheese)
                    .build();

            if (favorite.equalsIgnoreCase("yes")) {
                user.addFavoritePizza(pizza);
                userRepository.updateUser(user);
            }

        }

        Order order = new Order(orderRepository.getAllOrders().size() + 1, pizza, user);

        orderRepository.addOrder(order);
    }
}
