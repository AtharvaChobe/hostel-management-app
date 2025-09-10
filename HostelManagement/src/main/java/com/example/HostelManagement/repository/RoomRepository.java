package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
     Room findByRoomNumber(int number);
     Room deleteByRoomNumber(int number);
}