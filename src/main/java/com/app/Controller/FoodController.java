package com.app.Controller;

import com.app.Entity.Food;
import com.app.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/foods")
    public String addFood(
            @RequestParam("foodName") String foodName,
            @RequestParam("foodDescription") String foodDescription,
            @RequestParam("foodPic") MultipartFile foodPic,
            @RequestParam("foodPrice") Double foodPrice,
            @RequestParam("foodCategory") String foodCategory,
            @RequestParam("foodSupplierId") int foodSupplierId) throws IOException {
        try
        {
            foodService.addFood(foodName, foodDescription, foodPic, foodPrice, foodCategory, foodSupplierId);
            return "Food added successfully";
        }
        catch (Exception e)
        {
            return "Error adding food: " + e.getMessage();
        }
    }
    //http://localhost:8080/urban-food/foods (use body, form in postman to test)

    @PutMapping("/foods/{foodId}")
    public String updateFood(
            @PathVariable int foodId,
            @RequestParam("foodName") String foodName,
            @RequestParam("foodDescription") String foodDescription,
            @RequestParam("foodPic") MultipartFile foodPic,
            @RequestParam("foodPrice") Double foodPrice,
            @RequestParam("foodCategory") String foodCategory,
            @RequestParam("foodSupplierId") int foodSupplierId) throws IOException {
        try
        {
            foodService.updateFood(foodId, foodName, foodDescription, foodPic, foodPrice, foodCategory, foodSupplierId);
            return "Food updated successfully";
        }
        catch (Exception e)
        {
            return "Error updating food: " + e.getMessage();
        }
    }
    //http://localhost:8080/urban-food/foods/1 (put any id u like)

    @DeleteMapping("/foods/{id}")
    public String deleteFood(@PathVariable("id") int id) {
        try
        {
            foodService.deleteFood(id);
            return "Food deleted successfully";
        }
        catch (Exception e)
        {
            return "Error deleting food: " + e.getMessage();
        }
    }
    //http://localhost:8080/urban-food/foods/2 (delete any id u like)

    @GetMapping("/foods")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }
    //http://localhost:8080/urban-food/foods

    @GetMapping("foods/{id}")
    public Food getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id);
    }
    //http://localhost:8080/urban-food/foods/1 (get any id u like)

    @GetMapping("foods/search")
    public List<Food> searchByName(@RequestParam String name) {
        return foodService.searchFoodByName(name);
    }
    //http://localhost:8080/urban-food/foods/search?name=meat (this doesnt work, only one that doesnt work)

    @GetMapping("foods/filter")
    public List<Food> filterByPrice(@RequestParam Double min, @RequestParam Double max) {
        return foodService.filterByPrice(min, max);
    }
    //http://localhost:8080/urban-food/foods/filter?min=100&max=5000 (put anything u like)

    @GetMapping("foods/supplier/{supplierId}")
    public List<Food> getFoodsBySupplierId(@PathVariable int supplierId) {
        return foodService.getFoodBySupplierId(supplierId);
    }

    //http://localhost:8080/urban-food/foods/supplier/4 (put any id u like)
}
