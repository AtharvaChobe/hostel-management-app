package com.example.HostelManagement.service;

import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.repository.HostelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelerService {
    private final HostelerRepository repo;

    @Autowired
    public HostelerService(HostelerRepository repo) {
        this.repo = repo;
    }

    public List<Hosteler> getAll() {
        return repo.findAll();
    }

    public Hosteler add(Hosteler h){
       return repo.save(h);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public Optional getById(String id) {
        return repo.findById(id);
    }
}

