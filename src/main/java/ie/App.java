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

    public static App getInstance()
    {
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
            float currentPopularity = restaurants.get(i).getPopularity();
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

    public void addFood(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        Food newFood = mapper.readValue(jsonData, Food.class);
        int index = getIndexOfRestaurant(jsonData, 1);
        if (index >= 0)
            restaurants.get(index).addFood(newFood);
        else
            System.out.println("Invalid restaurant name!");
    }

    public void printRestaurants() throws JsonParseException, JsonMappingException, IOException {
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println(restaurants.get(i).getName());
        }
    }

    public void getRestaurant(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        int index = getIndexOfRestaurant(jsonData, 0);
        if (index >= 0)
            restaurants.get(index).printJsonInfo();
        else
            System.out.println("Invalid restaurant name!");
    }

    public void getFood(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        int index = getIndexOfRestaurant(jsonData, 1);

        ObjectMapper nameMapper = new ObjectMapper();
        Names newName = nameMapper.readValue(jsonData, Names.class);
        String foodName = newName.getFoodName();

        if (index >= 0)
            restaurants.get(index).printJsonFoodInfo(foodName);
        else
            System.out.println("Invalid restaurant name!");
    }

    public void addToCart(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        boolean allowToAdd = false;
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
                    System.out.println("Invalid food name!");
            }
            else
                System.out.println("Invalid restaurant name!");
        }
        else {
            System.out.println("Your cart is from another restaurant!");
        }
    }

    public Map<String, Integer> getCart() throws JsonParseException, JsonMappingException, IOException{
        customer.printFoodCart();
        return customer.getFoodCart();
    }

    public String finalizeOrder() throws JsonParseException, JsonMappingException, IOException{
        String jsonFoodCart = customer.printFoodCart();
        System.out.println(jsonFoodCart);
        System.out.println("Your order was submitted.");
        customer.freeCart();
        return jsonFoodCart;
    }

    public String getRecommendedRestaurants() throws JsonParseException, JsonMappingException, IOException{
        int numOfBests = 3;
        if (restaurants.size() < numOfBests)
            numOfBests = restaurants.size();
        Map<String, Float> bestRestaurants = getBestRestaurants(numOfBests);
        ObjectMapper mapperObj = new ObjectMapper();
        String bestRestaurantsJson = mapperObj.writeValueAsString(bestRestaurants);
        System.out.println(bestRestaurantsJson);
        //todo : should make interface to print stuff
        return bestRestaurantsJson;
    }

    public void handleAction(String action, String jsonData) throws IOException {
        switch (action) {
            case "addRestaurant":
                addRestaurant(jsonData);
                break;
            case "addFood":
                addFood(jsonData);
                break;
            case "getRestaurants":
                printRestaurants();
                break;
            case "getRestaurant":
                getRestaurant(jsonData);
                break;
            case "getFood":
                getFood(jsonData);
                break;
            case "addToCart":
                addToCart(jsonData);
                break;
            case "getCart":
                getCart();
                break;
            case "finalizeOrder":
                finalizeOrder();
                break;
            case "getRecommendedRestaurants":
                getRecommendedRestaurants();
                break;
            default:
                System.out.println("wrong command!");
                break;
        }
    }

    public void main(String[] args) throws IOException
    {

    }
}
