package ie;

public class Customer {
    private boolean restaurantIsSet;
    private String RestaurantName;
    private Food[] foodCard;

    public boolean isRestaurantSet() {
        return restaurantIsSet;
    }

    public void setRestaurant() {
        restaurantIsSet = true;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }
}
