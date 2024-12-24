package models;

public class Order {
    private int id;
    private Pizza pizza;
    private int qty;
    private String status;
    private OrderState state;
    private User user;
    private boolean isDelivery;
    private int ratings;
    private String review;

    public Order(int id, Pizza pizza, int qty, User user, boolean isDelivery) {
        this.id = id;
        this.pizza = pizza;
        this.user = user;
        this.state = new PlacedState();
        this.qty = qty;
        this.isDelivery = isDelivery;
    }

    public Order(int id, Pizza pizza, int qty, User user, boolean isDelivery, int ratings, String review) {
        this.id = id;
        this.pizza = pizza;
        this.user = user;
        this.state = new PlacedState();
        this.qty = qty;
        this.isDelivery = isDelivery;
    }

    public Order(int id, Pizza pizza, int qty, User user, OrderState orderState, boolean isDelivery, int ratings,
            String review) {
        this.id = id;
        this.pizza = pizza;
        this.user = user;
        this.state = orderState;
        this.qty = qty;
        this.isDelivery = isDelivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState orderState) {
        this.state = orderState;
    }

    public void updateState() {
        state.updateState(this);
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
