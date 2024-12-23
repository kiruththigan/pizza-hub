package models;

public class SeasonalPromotion implements PromotionStrategy {

    @Override
    public double applyDiscount(double amount) {
        System.out.println("Original Price: $" + amount + ", Discounted Price: $" + (amount * 0.8));
        return amount * 0.8;
    }

}
