package ie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    private static ArrayList<Restaurant> restaurants = new ArrayList<>();
    private static Customer customer;

    public static int getIndexOfRestaurant(String jsonData, int nameOrRestaurantName) throws JsonParseException, JsonMappingException, IOException {
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

    public static void addRestaurant(String jsonData) throws JsonParseException, JsonMappingException, IOException {
        //What should we do if restaurant name is repetitive?
        ObjectMapper mapper = new ObjectMapper();
        Restaurant newRestaurant = mapper.readValue(jsonData, Restaurant.class);
        restaurants.add(newRestaurant);
    }

    public static void addFood(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        Food newFood = mapper.readValue(jsonData, Food.class);
        int index = getIndexOfRestaurant(jsonData, 1);
        if (index >= 0)
            restaurants.get(index).addFood(newFood);
        else
            System.out.println("Invalid restaurant name!");
    }

    public static void getRestaurants() throws JsonParseException, JsonMappingException, IOException{
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println(restaurants.get(i).getName());
        }
    }

    public static void getRestaurant(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        int index = getIndexOfRestaurant(jsonData, 0);
        if (index >= 0)
            restaurants.get(index).printJsonInfo();
        else
            System.out.println("Invalid restaurant name!");
    }

    public static void getFood(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        int index = getIndexOfRestaurant(jsonData, 1);

        ObjectMapper nameMapper = new ObjectMapper();
        Names newName = nameMapper.readValue(jsonData, Names.class);
        String foodName = newName.getFoodName();

        if (index >= 0)
            restaurants.get(index).printJsonFoodInfo(foodName);
        else
            System.out.println("Invalid restaurant name!");
    }

    public static void addToCart(String jsonData) throws JsonParseException, JsonMappingException, IOException{
        
    }

    public static void getCart() throws JsonParseException, JsonMappingException, IOException{

    }

    public static void finalizeOrder() throws JsonParseException, JsonMappingException, IOException{

    }

    public static void getRecommendedRestaurants() throws JsonParseException, JsonMappingException, IOException{

    }


    public static void handleAction(String action, String jsonData) throws IOException {
        switch (action) {
            case "addRestaurant":
                addRestaurant(jsonData);
                break;
            case "addFood":
                addFood(jsonData);
                break;
            case "getRestaurants":
                getRestaurants();
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

    public static void main( String[] args ) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String command = br.readLine();
            String action = "";
            String jsonData = "";
            if(command.contains(" ")){
                action = command.substring(0, command.indexOf(" "));
                jsonData = command.substring(command.indexOf(" ") + 1);
            }
            else
                action = command;

            handleAction(action, jsonData);

        }
    }
}
