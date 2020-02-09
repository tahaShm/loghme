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

    public int foodIdx(String foodName) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().equals(foodName))
                return i;
        }
        return -1;
    }

    public void addFood(Food newFood) {
        if (foodIdx(newFood.getName()) == -1)
            menu.add(newFood);
    }

    public String sendJsonInfo() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(this));
//        return "no";
        return mapper.writeValueAsString(this);
    }

    public String sendJsonFoodInfo(String foodName) throws JsonParseException, JsonMappingException, IOException {
        int index = -1;
        for (int i = 0; i < menu.size(); i++) {
            if (foodName.equals(menu.get(i).getName())) {
                index = i;
                break;
            }
        }
        if (index >= 0)
            return menu.get(index).sendInfo();
        else
            return "Invalid food name!";
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

    public float sendPopularity() {
        float average = sendAverageFoodPopulations();
        float distance = location.getDistance();
        if (distance == 0)
            return Float.MAX_VALUE;
        return average / distance;
    }

    private float sendAverageFoodPopulations() {
        float total = 0;
        for (Food food : menu) {
            total += food.getPopularity();
        }
        return (total / menu.size());
    }
}
