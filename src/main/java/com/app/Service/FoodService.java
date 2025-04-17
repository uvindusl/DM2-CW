package com.app.Service;

import com.app.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addFood(String foodName, String foodDescription, MultipartFile foodPic, Double foodPrice, String foodCategory, int foodSupplierId)
            throws IOException {
        jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call add_food(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, foodName);
            cs.setString(2, foodDescription);
            try
            {
                cs.setBlob(3, foodPic.getInputStream());
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            cs.setDouble(4, foodPrice);
            cs.setString(5, foodCategory);
            cs.setInt(6, foodSupplierId);
            cs.execute();
            return null;
        });
    }

    public void updateFood(int foodId, String foodName, String foodDescription, MultipartFile foodPic, Double foodPrice, String foodCategory, int foodSupplierId) throws IOException {
        jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call update_food(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, foodId);
            cs.setString(2, foodName);
            cs.setString(3, foodDescription);
            try
            {
                cs.setBlob(4, foodPic.getInputStream());
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            cs.setDouble(5, foodPrice);
            cs.setString(6, foodCategory);
            cs.setInt(7, foodSupplierId);
            cs.execute();
            return null;
        });
    }

    public void deleteFood(int id)
    {
        jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call delete_food(?)}");
            cs.setInt(1, id);
            cs.execute();
            return null;
        });
    }

    public List<Food> getAllFoods() {
        return jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call get_all_foods(?)}");
            cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);

            List<Food> foodList = new ArrayList<>();
            while (rs.next())
            {
                foodList.add(new Food(
                        rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getString("food_description"),
                        rs.getBytes("food_pic"),
                        rs.getDouble("food_price"),
                        rs.getString("food_category"),
                        rs.getInt("food_supplier_id")
                ));
            }
            return foodList;
        });
    }

    public Food getFoodById(int id)
    {
        return jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call search_by_id(?, ?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(2);
            Food food = null;
            if (rs.next())
            {
                food = new Food(
                        rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getString("food_description"),
                        rs.getBytes("food_pic"),
                        rs.getDouble("food_price"),
                        rs.getString("food_category"),
                        rs.getInt("food_supplier_id")
                );
            }
            return food;
        });
    }

    public List<Food> searchFoodByName(String name) {
        return jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call search_by_name(?, ?)}");
            cs.setString(1, name);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(2);

            List<Food> foodList = new ArrayList<>();
            while (rs.next())
            {
                foodList.add(new Food(
                        rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getString("food_description"),
                        rs.getBytes("food_pic"),
                        rs.getDouble("food_price"),
                        rs.getString("food_category"),
                        rs.getInt("food_supplier_id")
                ));
            }
            return foodList;
        });
    }

    public List<Food> filterByPrice(Double min, Double max)
    {
        return jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call search_by_price(?, ?, ?)}");
            cs.setDouble(1, min);
            cs.setDouble(2, max);
            cs.registerOutParameter(3, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(3);

            List<Food> foodList = new ArrayList<>();
            while (rs.next())
            {
                foodList.add(new Food(
                        rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getString("food_description"),
                        rs.getBytes("food_pic"),
                        rs.getDouble("food_price"),
                        rs.getString("food_category"),
                        rs.getInt("food_supplier_id")
                ));
            }
            return foodList;
        });
    }

    public List<Food> getFoodBySupplierId(int supplierId)
    {
        return jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call get_food_by_supplier_id(?, ?)}");
            cs.setInt(1, supplierId);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(2);

            List<Food> foodList = new ArrayList<>();
            while (rs.next())
            {
                foodList.add(new Food(
                        rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getString("food_description"),
                        rs.getBytes("food_pic"),
                        rs.getDouble("food_price"),
                        rs.getString("food_category"),
                        rs.getInt("food_supplier_id")
                ));
            }
            return foodList;
        });
    }
}