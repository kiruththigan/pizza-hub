package models;

public interface PromotionStrategy {
    double applyDiscount(double amount, User user);
}
