package com.app.Entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Feedbacks")
public class Feedback {

    @Id
    private String id;


    private int customerId;
    private int productId;
    private String feedback;
    private double rating;

    public Feedback(String id, int customerId, int productId, String feedback, double rating) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
