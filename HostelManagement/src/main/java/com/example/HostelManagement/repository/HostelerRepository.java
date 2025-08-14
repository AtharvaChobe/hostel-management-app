package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Hosteler;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelerRepository extends MongoRepository<Hosteler, String> {
}
