package com.example.HostelManagement.controller;

import com.example.HostelManagement.config.JwtUtil;
import com.example.HostelManagement.model.Hostel;
import com.example.HostelManagement.repository.HostelRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private HostelRepository repo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Hostel body) {
        if (repo.findByEmail(body.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        body.setPassword(encoder.encode(body.getPassword()));
        repo.save(body);
        return ResponseEntity.ok("Owner registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var owner = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!encoder.matches(req.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(owner.getEmail());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "name", owner.getHostelName()
        ));
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
