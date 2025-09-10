package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Hostel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HostelRepository extends MongoRepository <Hostel, String>{
    Optional<Hostel> findByEmail(String email);
}
