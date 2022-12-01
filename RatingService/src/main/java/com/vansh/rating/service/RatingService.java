package com.vansh.rating.service;

import com.vansh.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //Create
    Rating create(Rating rating);


    //Get all ratings
    List<Rating> getAllRatings();

    //User-wise  ratings
    List<Rating> getRatingByUserId(String userId);


    //Hotel-wise ratings
    List<Rating> getRatingByHotelId(String hotelId);

}
