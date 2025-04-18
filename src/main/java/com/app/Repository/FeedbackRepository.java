package com.app.Repository;

import com.app.Entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    @Query(value = "{'productId': ?0}")
    List<Feedback> getFeedbackByProductId(int productId);


    @Query(value = "{'customerId': ?0}")
    List<Feedback> getFeedbackByCustomerId(int customerId);
}
