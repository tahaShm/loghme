package ie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private boolean restaurantIsSet = false;
    private String restaurantName;
    private Map<String, Integer> footCart = new HashMap<String, Integer>();

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
        for (Map.Entry<String, Integer> entry : footCart.entrySet()) {
            if (entry.getKey().equals(foodName)){
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        footCart.put(foodName, 1);
    }

    public void printFoodCart() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapperObj = new ObjectMapper();
        String foodCartJson = mapperObj.writeValueAsString(footCart);
        System.out.println(foodCartJson);
//        for (Map.Entry<String, Integer> entry : footCart.entrySet()) {
//            System.out.println("food: " + entry.getKey() + " value: " + entry.getValue());
//        }
    }
}
