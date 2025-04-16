package com.app.Entity;

public class Food {
    private int foodId;
    private String foodName;
    private String foodDescription;
    private byte[] foodPic;
    private double foodPrice;
    private String foodCategory;
    private int foodSupplierId;

    public Food(int foodId, String foodName, String foodDescription, byte[] foodPic, double foodPrice, String foodCategory, int foodSupplierId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPic = foodPic;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.foodSupplierId = foodSupplierId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public byte[] getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(byte[] foodPic) {
        this.foodPic = foodPic;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public int getFoodSupId()
    {
        return foodSupplierId;
    }

    public void setFoodSupId(int foodSupplierId)
    {
        this.foodSupplierId = foodSupplierId;
    }
}
