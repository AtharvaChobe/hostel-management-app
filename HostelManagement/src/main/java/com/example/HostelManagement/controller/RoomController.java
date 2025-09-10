package com.example.HostelManagement.controller;

import com.example.HostelManagement.model.Room;
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

    @PostMapping
    public ResponseEntity<Room> addRoom(Room room){
        return new ResponseEntity<>(service.add(room), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoom(int roomNumber){
        service.remove(roomNumber);
        return new ResponseEntity<>("Deleted Room", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }
}