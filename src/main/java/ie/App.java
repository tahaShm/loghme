package ie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class App
{
    private ArrayList<Restaurant> restaurants;
    private Customer customer;
    private static App singleApp = null;

    private App() {
        restaurants = new ArrayList<>();
        customer = new Customer();
    }

    public static App getInstance() {
        if (singleApp == null)
            singleApp = new App();

        return singleApp;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getIndexOfRestaurant(String jsonData, int nameOrRestaurantName) throws JsonParseException, JsonMappingException, IOException {
        int index = -1;
        ObjectMapper nameMapper = new ObjectMapper();
        Names newName = nameMapper.readValue(jsonData, Names.class);
        String restaurantName = "";
        if (nameOrRestaurantName == 1)
            restaurantName = newName.getRestaurantName();
        else
            restaurantName = newName.getName();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurantName.equals(restaurants.get(i).getName())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public HashMap<String, Float> sortByValue(HashMap<String, Float> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Float> > list =
                new LinkedList<Map.Entry<String, Float> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private Map<String, Float> getBestRestaurants(int numOfBests) {
        HashMap<String, Float> allRestaurants = new HashMap<String, Float>();
        Map<String, Float> sortedRestaurants;

        for (int i = 0; i < restaurants.size(); i++) {
            String currentRestaurantName = restaurants.get(i).getName();
            float currentPopularity = restaurants.get(i).sendPopularity();
            allRestaurants.put(currentRestaurantName, currentPopularity);
        }

        sortedRestaurants = sortByValue(allRestaurants);
        allRestaurants.clear();
        int counter = 0;
        for (Map.Entry<String, Float> entry : sortedRestaurants.entrySet()) {
            if (counter >= numOfBests)
                break;
            allRestaurants.put(entry.getKey(), entry.getValue());
            counter++;
        }

        return allRestaurants;
    }

    public void addRestaurant(String jsonData) throws JsonParseException, JsonMappingException, IOException {
        //What should we do if restaurant name is repetitive?
        ObjectMapper mapper = new ObjectMapper();
        Restaurant newRestaurant = mapper.readValue(jsonData, Restaurant.class);
        restaurants.add(newRestaurant);
    }

    public boolean addFood(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        Food newFood = mapper.readValue(jsonData, Food.class);
        int index = getIndexOfRestaurant(jsonData, 1);
        if (index >= 0) {
            restaurants.get(index).addFood(newFood);
            return true;
        }
        else
            return false;
    }

    public String getRestaurantsInfo() throws JsonParseException, JsonMappingException, IOException {
        String response = "";
        for (int i = 0; i < restaurants.size(); i++) {
            response += restaurants.get(i).getName() + '\n';
        }
        return response;
    }

    public String getRestaurant(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        String response = "";
        int index = getIndexOfRestaurant(jsonData, 0);
        if (index >= 0)
            response = restaurants.get(index).sendJsonInfo();
        else
            response = "Invalid restaurant name!";
        return response;
    }

    public String getFood(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        int index = getIndexOfRestaurant(jsonData, 1);
        String response = "";

        ObjectMapper nameMapper = new ObjectMapper();
        Names newName = nameMapper.readValue(jsonData, Names.class);
        String foodName = newName.getFoodName();

        if (index >= 0)
            response = restaurants.get(index).sendJsonFoodInfo(foodName);
        else
            response = "Invalid restaurant name!";
        return response;
    }

    public String addToCart(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        boolean allowToAdd = false;
        String response = "";
        ObjectMapper nameMapper = new ObjectMapper();
        Names newName = nameMapper.readValue(jsonData, Names.class);
        String restaurantName = newName.getRestaurantName();

        if (customer.isRestaurantSet() == false)
            allowToAdd = true;
        else {
            String currentRestaurantName = customer.getRestaurantName();
            if (currentRestaurantName.equals(restaurantName))
                allowToAdd = true;
        }

        if (allowToAdd) {
            int index = getIndexOfRestaurant(jsonData, 1);

            String foodName = newName.getFoodName();

            if (index >= 0){
                if (restaurants.get(index).isFoodValid(foodName)){
                    customer.addFoodToCart(foodName, restaurantName);
                }
                else
                    response = "Invalid food name!\n";
            }
            else
                response = "Invalid restaurant name!\n";
        }
        else {
            response = "Your cart is from another restaurant!\n";
        }
        return response;
    }

    public Map<String, Integer> getCart() throws JsonParseException, JsonMappingException, IOException {
        return customer.getFoodCart();
    }

    public String getCartJson() throws JsonParseException, JsonMappingException, IOException {
        return customer.getCartJson();
    }

    public String finalizeOrder() throws JsonParseException, JsonMappingException, IOException{
        String jsonFoodCart = customer.getCartJson();
        customer.freeCart();
        return jsonFoodCart;
    }

    public String getRecommendedRestaurants() throws JsonParseException, JsonMappingException, IOException{
        int numOfBests = 3;
        if (restaurants.size() < numOfBests)
            numOfBests = restaurants.size();
        Map<String, Float> bestRestaurants = getBestRestaurants(numOfBests);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(bestRestaurants);
    }

    public void main(String[] args) throws IOException
    {

    }
}