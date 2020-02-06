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

    public static void addRestaurant(String jsonData) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant newRestaurant = mapper.readValue(jsonData, Restaurant.class);
        restaurants.add(newRestaurant);
    }

    public static void addFood(String jsonData) {

    }

    public static void getRestaurants() throws JsonParseException, JsonMappingException, IOException {
        System.out.println(restaurants.size());
    }

    public static void getRestaurant(String jsonData) {

    }

    public static void getFoods(String jsonData) {

    }

    public static void addToCart(String jsonData) {

    }

    public static void getCart() {

    }

    public static void finalizeOrder() {

    }

    public static void getRecommendedRestaurants() {

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
            case "getFoods":
                getFoods(jsonData);
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
