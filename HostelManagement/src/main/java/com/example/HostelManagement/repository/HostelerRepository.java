package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Hosteler;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelerRepository extends MongoRepository<Hosteler, String> {
    boolean existsById(String Id);

    List<Hosteler> findByRoomNo(String RoomNo);
}
