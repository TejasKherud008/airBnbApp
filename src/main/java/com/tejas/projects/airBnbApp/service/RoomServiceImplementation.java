package com.tejas.projects.airBnbApp.service;

import com.tejas.projects.airBnbApp.dto.RoomDto;
import com.tejas.projects.airBnbApp.entity.Hotel;
import com.tejas.projects.airBnbApp.entity.Room;
import com.tejas.projects.airBnbApp.exception.ResourceNotFoundException;
import com.tejas.projects.airBnbApp.repository.HotelRepository;
import com.tejas.projects.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImplementation implements RoomService{

    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    private final InventoryService inventoryService;

    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId,RoomDto roomDto) {
        log.info("creating a room");
        Room room = modelMapper.map(roomDto,Room.class);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found...."));
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializedRoomForYear(room);
        }
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Fetching all rooms inside the hotel");
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel Not found"));

        return hotel.getRooms()
                .stream()
                .filter(Objects::nonNull)
                .map(room-> modelMapper.map(room,RoomDto.class))
                .toList();
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room By Id");
       Room room = roomRepository.findById(roomId)
               .orElseThrow(()-> new ResourceNotFoundException("Room Not Found With Id :- " + roomId));
       return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("deleting room By id");
        Room room = roomRepository.findById(roomId)
                        .orElseThrow(()-> new ResourceNotFoundException("Room Not Found With Id :- " + roomId));
        roomRepository.deleteById(roomId);
        inventoryService.deleteFutureInventories(room);
    }
}
