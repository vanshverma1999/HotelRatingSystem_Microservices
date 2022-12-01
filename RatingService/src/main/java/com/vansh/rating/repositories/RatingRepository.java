package com.vansh.rating.repositories;

import com.vansh.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    //Custom Finder Methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
