package ie;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private boolean restaurantIsSet = false;
    private String restaurantName;
    private Map<String, Integer> foodCard = new HashMap<String, Integer>();

    public boolean isRestaurantSet() {
        return restaurantIsSet;
    }

    public void setRestaurant(boolean restaurantIsSet) {
        this.restaurantIsSet = restaurantIsSet;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        restaurantName = restaurantName;
    }

    public void addFoodToCart(String foodName, String restaurantName) {
        restaurantIsSet = true;
        this.restaurantName = restaurantName;
        for (Map.Entry<String, Integer> entry : foodCard.entrySet()) {
            if (entry.equals(foodName)){
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        foodCard.put(foodName, 1);
    }
}
