package com.tejas.projects.airBnbApp.controller;

import com.tejas.projects.airBnbApp.dto.RoomDto;
import com.tejas.projects.airBnbApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@Slf4j
@RequiredArgsConstructor
public class RoomController {

        private final RoomService roomService;

        @PostMapping
        public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto){
            RoomDto room =  roomService.createNewRoom(hotelId,roomDto);
            return new ResponseEntity<>(room, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
            List<RoomDto> roomDtoList = roomService.getAllRoomsInHotel(hotelId);
            return ResponseEntity.ok(roomDtoList);
        }

        @GetMapping("/{roomId}")
        public ResponseEntity<RoomDto> getRoomById(@PathVariable("roomId") Long roomId){
            RoomDto roomDto = roomService.getRoomById(roomId);
            return ResponseEntity.ok(roomDto);
        }

        @DeleteMapping("/{roomId}")
        public ResponseEntity<Void> deleteRoomById(@PathVariable("roomId") Long roomId) {
            roomService.deleteRoomById(roomId);
            return ResponseEntity.noContent().build();
        }

}
