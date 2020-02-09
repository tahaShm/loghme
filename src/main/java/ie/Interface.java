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
                if (!app.addFood(jsonData))
                    System.out.println("Invalid restaurant name!");;
                break;
            case "getRestaurants":
                System.out.print(app.getRestaurantsInfo());
                break;
            case "getRestaurant":
                System.out.println(app.getRestaurant(jsonData));
                break;
            case "getFood":
                System.out.println(app.getFood(jsonData));
                break;
            case "addToCart":
                System.out.print(app.addToCart(jsonData));
                break;
            case "getCart":
                System.out.println(app.getCartJson());
                break;
            case "finalizeOrder":
                System.out.println(app.finalizeOrder());
                System.out.println("Your order was submitted.");
                break;
            case "getRecommendedRestaurants":
                System.out.println(app.getRecommendedRestaurants());
                break;
            default:
                System.out.println("Wrong command!");
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
