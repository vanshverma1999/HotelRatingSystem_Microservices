package com.vansh.hotel.controller;

import com.vansh.hotel.entities.Hotel;
import com.vansh.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //Create
    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.create(hotel));
    }
    //GetSingle
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.get(hotelId));
    }
    //GetAll
    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return ResponseEntity.ok(this.hotelService.getAll());
    }
}
