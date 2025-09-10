package com.example.HostelManagement.service;

import com.example.HostelManagement.model.Room;
import com.example.HostelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room add(Room room){
        return roomRepository.save(room);
    }

    public void remove(int roomNumber){
        roomRepository.deleteByRoomNumber(roomNumber);
    }

    public List<Room> all(){
        return roomRepository.findAll();
    }
}