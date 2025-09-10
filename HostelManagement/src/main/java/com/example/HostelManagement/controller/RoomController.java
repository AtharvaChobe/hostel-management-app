package com.example.HostelManagement.controller;

import com.example.HostelManagement.model.Room;
import com.example.HostelManagement.repository.RoomRepository;
import com.example.HostelManagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService service;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody Room room) {
        try {
            Room savedRoom = service.add(room);
            return ResponseEntity.status(HttpStatus.OK).body(savedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<String> deleteByRoomNumber(@PathVariable String roomNumber) {
        if (roomRepository.existsByRoomNo(roomNumber)) {
            roomRepository.deleteByRoomNo(roomNumber);
            return ResponseEntity.ok("Room deleted successfully");
        }
        return ResponseEntity.status(404).body("Room not found");
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    @GetMapping("/vacancy/{roomNo}")
    public ResponseEntity<Integer> checkVacancy(@PathVariable String roomNo){
        return new ResponseEntity<>(service.findVacancy(roomNo), HttpStatus.OK);
    }
}