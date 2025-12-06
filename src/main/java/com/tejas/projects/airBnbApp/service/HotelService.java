package com.tejas.projects.airBnbApp.service;

import com.tejas.projects.airBnbApp.dto.HotelDto;
import com.tejas.projects.airBnbApp.entity.Hotel;

public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id,HotelDto hotelDto);

    void deleteHotelById(Long id);
}
