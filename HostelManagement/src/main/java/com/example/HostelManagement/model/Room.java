package com.example.HostelManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "room")
public class Room {
    @Id
    private String roomId;
    private String roomNo;
    private int capacity;
    private int occupancy;

    public Room(String roomNo, int capacity, int occupancy) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.occupancy = occupancy;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int vacancy) {
        this.occupancy = vacancy;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", capacity=" + capacity +
                ", occupancy=" + occupancy +
                '}';
    }
}
