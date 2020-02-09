package ie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interface {
    private static App app;
    public static void handleAction(String action, String jsonData) throws IOException {
        switch (action) {
            case "addRestaurant":
                app.addRestaurant(jsonData);
                break;
            case "addFood":
                app.addFood(jsonData);
                break;
            case "getRestaurants":
                app.printRestaurants();
                break;
            case "getRestaurant":
                app.getRestaurant(jsonData);
                break;
            case "getFood":
                app.getFood(jsonData);
                break;
            case "addToCart":
                app.addToCart(jsonData);
                break;
            case "getCart":
                app.getCart();
                break;
            case "finalizeOrder":
                app.finalizeOrder();
                break;
            case "getRecommendedRestaurants":
                app.getRecommendedRestaurants();
                break;
            default:
                System.out.println("wrong command!");
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        app = App.getInstance();
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
