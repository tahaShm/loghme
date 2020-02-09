package ie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private boolean restaurantIsSet = false;
    private String restaurantName;
    private Map<String, Integer> foodCart = new HashMap<String, Integer>();

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

    public Map<String, Integer> getFoodCart() { return foodCart; }

    public void addFoodToCart(String foodName, String restaurantName) {
        restaurantIsSet = true;
        this.restaurantName = restaurantName;
        for (Map.Entry<String, Integer> entry : foodCart.entrySet()) {
            if (entry.getKey().equals(foodName)){
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        foodCart.put(foodName, 1);
    }

    //todo : should change the name
    public String printFoodCart() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapperObj = new ObjectMapper();
        String foodCartJson = mapperObj.writeValueAsString(foodCart);
        return foodCartJson;
//        for (Map.Entry<String, Integer> entry : footCart.entrySet()) {
//            System.out.println("food: " + entry.getKey() + " value: " + entry.getValue());
//        }
    }

    public void freeCart() {
        restaurantIsSet = false;
        restaurantName = "";
        foodCart.clear();
    }
}
