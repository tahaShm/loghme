package ie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App
{
    private Restaurant[] restaurants;
    private Customer customer;

    public static void handleAction(String action, String jsonData) throws JsonParseException, JsonMappingException, IOException{
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

    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
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
