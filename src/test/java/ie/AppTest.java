package ie;

import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;

import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppTest 
{
    static String path = "src/test/resources/";

//    @BeforeClass
//    public static void init() {
//        String res1 = "", res2 = "", res3 = "", res4 = "", res5 = "";
//        try {
//            res1 = Files.readString(Paths.get(path + "restaurant1.json"));
//            res2 = Files.readString(Paths.get(path + "restaurant2.json"));
//            res3 = Files.readString(Paths.get(path + "restaurant3.json"));
//            res4 = Files.readString(Paths.get(path + "restaurant4.json"));
//            res5 = Files.readString(Paths.get(path + "restaurant5.json"));
//            App.addRestaurant(res1);
//            App.addRestaurant(res2);
//            App.addRestaurant(res3);
//            App.addRestaurant(res4);
//            App.addRestaurant(res5);
//        }
//        catch (IOException e) {
//            fail();
//        }
//    }
//
//    @Test
//    public void testRecommendedRestaurants() {
//        String result = "";
//        HashMap<String, Float> resultRestaurantsNames = null;
//        try {
//            result = App.getRecommendedRestaurants();
//            ObjectMapper mapper = new ObjectMapper();
//            resultRestaurantsNames = mapper.readValue(result, HashMap.class);
//        }
//        catch (IOException e) {
//            fail();
//        }
//        assertTrue(resultRestaurantsNames.containsKey("Restaurant5"));
//        assertTrue(resultRestaurantsNames.containsKey("Restaurant1"));
//        assertTrue(resultRestaurantsNames.containsKey("Restaurant2"));
//    }
//
//    @Test
//    public void testFinalizeOrder() {
//        HashMap<String, Float> resultCart = null;
//        String res1 = "", res2 = "", res3 = "", res4 = "", res5 = "", cartJson;
//        try {
//            res1 = Files.readString(Paths.get(path + "order1.json"));
//            res2 = Files.readString(Paths.get(path + "order2.json"));
//            res3 = Files.readString(Paths.get(path + "order3.json"));
//            res4 = Files.readString(Paths.get(path + "order4.json"));
//            res5 = Files.readString(Paths.get(path + "order5.json"));
//            App.addToCart(res1);
//            App.addToCart(res2);
//            App.addToCart(res3);
//            App.addToCart(res4);
//            App.addToCart(res5);
//
//            assertEquals(2, App.getCart().size());
//
//            cartJson = App.finalizeOrder();
//
//            assertEquals(0, App.getCart().size());
//
//            ObjectMapper mapper = new ObjectMapper();
//            resultCart = mapper.readValue(cartJson, HashMap.class);
//            assertTrue(resultCart.containsKey("Food11"));
//            assertTrue(resultCart.containsKey("Food12"));
//        }
//        catch (IOException e) {
//            fail();
//        }
//    }

//    @Test
//    public void testAddRestaurant() {
//        assertEquals(2, App.getRestaurants().size());
//        assertEquals("Hesturan", App.getRestaurants().get(0).getName());
//        assertEquals("Shila", App.getRestaurants().get(1).getName());
//
//        assertEquals("luxury", App.getRestaurants().get(0).getDescription());
//        assertEquals("fast food", App.getRestaurants().get(1).getDescription());
//
//        assertEquals(1, App.getRestaurants().get(0).getLocation().getX(), 10);
//        assertEquals(2, App.getRestaurants().get(0).getLocation().getY(), 10);
//        assertEquals(2, App.getRestaurants().get(1).getLocation().getX(), 10);
//        assertEquals(5, App.getRestaurants().get(1).getLocation().getY(), 10);
//
//        assertEquals(2, App.getRestaurants().get(0).getMenu().size());
//        assertEquals(2, App.getRestaurants().get(1).getMenu().size());
//
//        assertEquals("Gheime", App.getRestaurants().get(0).getMenu().get(0).getName());
//        assertEquals("Kabab", App.getRestaurants().get(0).getMenu().get(1).getName());
//        assertEquals("Jooje kabab", App.getRestaurants().get(1).getMenu().get(0).getName());
//        assertEquals("Mahi", App.getRestaurants().get(1).getMenu().get(1).getName());
//
//        assertEquals("it's yummy!", App.getRestaurants().get(0).getMenu().get(0).getDescription());
//        assertEquals("it's delicious!", App.getRestaurants().get(0).getMenu().get(1).getDescription());
//        assertEquals("it's not bad!", App.getRestaurants().get(1).getMenu().get(0).getDescription());
//        assertEquals("it's ...!", App.getRestaurants().get(1).getMenu().get(1).getDescription());
//
//        assertEquals(0.8, App.getRestaurants().get(0).getMenu().get(0).getPopularity(), 10);
//        assertEquals(0.6, App.getRestaurants().get(0).getMenu().get(1).getPopularity(), 10);
//        assertEquals(0.5, App.getRestaurants().get(1).getMenu().get(0).getPopularity(), 10);
//        assertEquals(0.65, App.getRestaurants().get(1).getMenu().get(1).getPopularity(), 10);
//
//        assertEquals(20000, App.getRestaurants().get(0).getMenu().get(0).getPrice());
//        assertEquals(30000, App.getRestaurants().get(0).getMenu().get(1).getPrice());
//        assertEquals(25000, App.getRestaurants().get(1).getMenu().get(0).getPrice());
//        assertEquals(40000, App.getRestaurants().get(1).getMenu().get(1).getPrice());
//    }

//    @Test
//    public void testAddFood() {
//        String inp1 = "", inp2 = "", inp3 = "";
//        try {
//            inp1 = Files.readString(Paths.get(path + "food1.json"));
//            inp2 = Files.readString(Paths.get(path + "food2.json"));
//            inp3 = Files.readString(Paths.get(path + "food3.json"));
//            App.addFood(inp1);
//            App.addFood(inp2);
//            App.addFood(inp3);
//        }
//        catch (IOException e) {
//            fail();
//        }
//        assertEquals(3, App.getRestaurants().get(1).getMenu().size());
//        assertEquals(2, App.getRestaurants().get(0).getMenu().size());
//    }

//    @Test
//    public void rand() {
//
//    }
}
