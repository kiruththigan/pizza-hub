package models;

public class NoPromotion implements PromotionStrategy {

    @Override
    public double applyDiscount(double amount, User user) {
        return amount;
    }
}
