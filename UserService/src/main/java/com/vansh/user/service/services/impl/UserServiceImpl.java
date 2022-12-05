package com.vansh.user.service.services.impl;

import com.vansh.user.service.entities.Hotel;
import com.vansh.user.service.entities.Rating;
import com.vansh.user.service.entities.User;
import com.vansh.user.service.exceptions.ResourceNotFoundException;
import com.vansh.user.service.repositories.UserRepository;
import com.vansh.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userID = UUID.randomUUID().toString();
        user.setUserId(userID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //Get user from database with userdata from repo
        User user =  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found in the server! : "+userId));
        //Fetch rating of the above user... from rating service
        //localhost:8083/ratings/users/a8e0fe5d-6315-4753-aa46-893b84780ab7
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());


        List<Rating> ratingList = ratings.stream().map(rating -> {
            //Api Calll to hotel service to get the hotel
            //http://localhost:8082/hotels/e1d280b1-a714-43e7-893a-ac764813b95d
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {}",forEntity.getStatusCode());
            //Set hotel to rating
            rating.setHotel(hotel);
            //return the rating
            //return rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
