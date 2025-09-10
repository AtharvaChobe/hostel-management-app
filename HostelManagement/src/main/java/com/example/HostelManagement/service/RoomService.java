package com.example.HostelManagement.service;

import com.example.HostelManagement.model.Room;
import com.example.HostelManagement.repository.HostelRepository;
import com.example.HostelManagement.repository.HostelerRepository;
import com.example.HostelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HostelerRepository hostelerRepository;

    public Room add(Room room){
        if(roomRepository.existsByRoomNo(room.getRoomNo())){
            throw new IllegalArgumentException("Room with number already exists");
        }
        return roomRepository.save(new Room(room.getRoomNo(), room.getCapacity(), 0));
    }

    public void remove(String roomNumber){
        roomRepository.deleteByRoomNo(roomNumber);
    }

    public int findVacancy(String roomNo){
        Room room = roomRepository.findByRoomNo(roomNo);
        return room.getCapacity() - room.getOccupancy();
    }

    public List<Room> all(){
//        System.out.println(roomRepository.findAll());
        return roomRepository.findAll();
    }
}