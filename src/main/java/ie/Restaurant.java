package ie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private ArrayList<Food> menu = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public void setMenu (ArrayList<Food> menu) {
        this.menu = menu;
    }

    public void addFood(Food newFood) {
        menu.add(newFood);
    }

    public void printJsonInfo() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        System.out.println(json);
    }

    public void printJsonFoodInfo(String foodName) throws JsonParseException, JsonMappingException, IOException {
        int index = -1;
        for (int i = 0; i < menu.size(); i++) {
            if (foodName.equals(menu.get(i).getName())) {
                index = i;
                break;
            }
        }
        if (index >= 0)
            menu.get(index).printInfo();
        else
            System.out.println("Invalid food name!");
    }

    public boolean isFoodValid(String foodName) {
        int index = -1;
        boolean isValid = false;
        for (int i = 0; i < menu.size(); i++) {
            if (foodName.equals(menu.get(i).getName())) {
                index = i;
                break;
            }
        }
        if (index >= 0)
            isValid = true;
        return isValid;
    }
}
