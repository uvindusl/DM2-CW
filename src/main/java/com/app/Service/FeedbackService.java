package com.app.Service;

import com.app.Entity.Feedback;
import com.app.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getFeedbackByProductId(int productId) {
        return feedbackRepository.getFeedbackByProductId(productId);
    }


    public List<Feedback> getFeedbackByCustomerId(int customerId) {
        return feedbackRepository.getFeedbackByCustomerId(customerId);
    }

    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
