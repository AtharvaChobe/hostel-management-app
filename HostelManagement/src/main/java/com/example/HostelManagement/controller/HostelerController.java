package com.example.HostelManagement.controller;

import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.service.HostelerService;
import com.example.HostelManagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/hostelers")
public class HostelerController {

    @Autowired
    private HostelerService service;

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<Hosteler> hostelers = service.getAll();
            if (hostelers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hostelers found.");
            }
            return ResponseEntity.ok(hostelers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHostelerById(@PathVariable String id) {
        try {
            Optional<Hosteler> hosteler = service.getById(id);
            if (hosteler.isPresent()) {
                return ResponseEntity.ok(hosteler.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hosteler not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Hosteler h) {
        try {
            Hosteler saved = service.add(h);
            return ResponseEntity.status(HttpStatus.OK).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hosteler> update(@PathVariable String id, @RequestBody Hosteler updatedHosteler) {
        try {
            Hosteler existing = (Hosteler) service.getById(id).orElseThrow(() ->
                    new NoSuchElementException("Hosteler not found with ID: " + id));

            existing.setName(updatedHosteler.getName());
            existing.setRoomNo(updatedHosteler.getRoomNo());
            existing.setContact(updatedHosteler.getContact());

            return new ResponseEntity<>(service.add(existing), HttpStatus.OK);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Hosteler deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/room/{roomNo}")
    public List<Hosteler> getHostelersByRoomNo(@PathVariable String roomNo) {
        return service.findByRoomno(roomNo);
    }
}
