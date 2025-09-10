package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    void deleteByRoomNo(String roomNo);

    boolean existsByRoomNo(String roomNo);

    Room findByRoomNo(String roomNo);
}