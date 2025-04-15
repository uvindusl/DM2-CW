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
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam("price") Double price,
            @RequestParam("category") String category) throws IOException {
        try
        {
            foodService.addFood(name, description, file, price, category);
            return "Food added successfully";
        }
        catch (Exception e)
        {
            return "Error adding food: " + e.getMessage();
        }
    }
    //http://localhost:8084/foods (use body, form in postman to test)

    @PutMapping("/foods/{id}")
    public String updateFood(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam("price") Double price,
            @RequestParam("category") String category) throws IOException {
        try
        {
            foodService.updateFood(id, name, description, file, price, category);
            return "Food updated successfully";
        }
        catch (Exception e)
        {
            return "Error updating food: " + e.getMessage();
        }
    }
    //http://localhost:8084/foods/1 (put any id u like)

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
    //http://localhost:8084/foods/2 (delete any id u like)

    @GetMapping("/foods")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }
    //http://localhost:8084/foods

    @GetMapping("foods/{id}")
    public Food getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id);
    }
    //http://localhost:8084/foods/1 (get any id u like)

    @GetMapping("foods/search")
    public List<Food> searchByName(@RequestParam String name) {
        return foodService.searchFoodByName(name);
    }
    //http://localhost:8084/foods/search?name=meat (this doesnt work, only one that doesnt work)

    @GetMapping("foods/filter")
    public List<Food> filterByPrice(@RequestParam Double min, @RequestParam Double max) {
        return foodService.filterByPrice(min, max);
    }
    //http://localhost:8084/foods/filter?min=100&max=5000 (put anything u like)
}
