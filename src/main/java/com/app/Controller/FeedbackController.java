package com.app.Controller;

import com.app.Entity.Feedback;
import com.app.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @GetMapping(path = "/feedbacks/{productId}")
    public List<Feedback> getFeedbackByProductId(@PathVariable int productId){
        return feedbackService.getFeedbackByProductId(productId);
    }

    @GetMapping(path = "/feedbacks/customer/{customerId}")
    public List<Feedback> getFeedbackByCustomerId(@PathVariable int customerId){
        return feedbackService.getFeedbackByCustomerId(customerId);
    }

    @PostMapping(path = "/feedbacks")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return feedbackService.addFeedback(feedback);
    }
}
