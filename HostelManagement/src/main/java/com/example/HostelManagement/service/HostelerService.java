package com.example.HostelManagement.service;
import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.model.Room;
import com.example.HostelManagement.repository.HostelerRepository;
import com.example.HostelManagement.repository.PaymentRepository;
import com.example.HostelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HostelerService {
    private final HostelerRepository hostelerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    public HostelerService(HostelerRepository hostelerRepository) {
        this.hostelerRepository = hostelerRepository;
    }

    public List<Hosteler> getAll() {
        return hostelerRepository.findAll();
    }

    public Hosteler add(Hosteler h) {
        Room room = roomRepository.findByRoomNo(h.getRoomNo());
        if (room == null) {
            throw new RuntimeException("Room with number " + h.getRoomNo() + " not found");
        }
        if (room.getOccupancy() >= room.getCapacity()) {
            throw new RuntimeException("Room capacity exceeded for Room " + room.getRoomNo());
        }
        room.setOccupancy(room.getOccupancy() + 1);
        roomRepository.save(room);
        h.setRoomNo(room.getRoomNo());
        return hostelerRepository.save(h);
    }

    public String delete(String id) {
        Optional<Hosteler> hosteler = hostelerRepository.findById(id);
        if (hosteler.isEmpty()) return "Hosteler does not exists";
//        System.out.println(hosteler);
        Room room = roomRepository.findByRoomNo(hosteler.get().getRoomNo());
//        System.out.println(room);
        if (hostelerRepository.existsById(id)) {
            hostelerRepository.deleteById(id);
            if (paymentRepository.existsByHostelerId(id)) {
                paymentRepository.deleteByHostelerId(id);
            }
            room.setOccupancy(room.getOccupancy() - 1);
            roomRepository.save(room);
            return "success";
        } else return "fail";
    }

    public Optional getById(String id) {
        return hostelerRepository.findById(id);
    }

    public List<Hosteler> findByRoomno(String roomNo) {
//        System.out.println(repo.findByRoomNo(roomNo));
        return hostelerRepository.findByRoomNo(roomNo);
    }
}

