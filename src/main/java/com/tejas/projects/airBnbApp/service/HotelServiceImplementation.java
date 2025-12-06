package com.tejas.projects.airBnbApp.service;

import com.tejas.projects.airBnbApp.dto.HotelDto;
import com.tejas.projects.airBnbApp.entity.Hotel;
import com.tejas.projects.airBnbApp.exception.ResourceNotFoundException;
import com.tejas.projects.airBnbApp.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImplementation implements HotelService{

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        log.info("Creating New Hotel With name :{}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel =  hotelRepository.save(hotel);
        log.info("creating Hotel With Id : {}",hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting Hotel By Id :- ",id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found With Id :- " +id));
        log.info("Hotel Fetched Sucessfully");
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found By Id :- " + id));
        modelMapper.map(hotelDto,hotel);
        hotel.setId(id);
        hotel.setAmenities(hotelDto.getAmenities());
        hotel.setPhotos(hotelDto.getPhotos());
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
       Boolean exists = hotelRepository.existsById(id);
       if(!exists) throw new ResourceNotFoundException("Hotel Not Found With Id :- " + id);

       hotelRepository.deleteById(id);
    }


}
