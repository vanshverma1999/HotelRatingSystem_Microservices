package com.vansh.hotel.services;

import com.vansh.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //Create
    Hotel create(Hotel hotel);
    //GetALL
    List<Hotel> getAll();
    //GetSingle
    Hotel get(String id);
}
