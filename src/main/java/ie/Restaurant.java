package ie;

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
}
