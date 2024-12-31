package models;

public class LoyaltyPromotion implements PromotionStrategy {

    @Override
    public double applyDiscount(double amount, User user) {
        if (user.getLoyaltyPoints() < 1000) {
            return amount;
        } else if (user.getLoyaltyPoints() < 5000) {
            System.out.println("Applying 5% discount for loyal customers.");
            return amount * 0.95;
        } else {
            System.out.println("Applying 10% discount for loyal customers.");
            return amount * 0.9;
        }
    }

}
