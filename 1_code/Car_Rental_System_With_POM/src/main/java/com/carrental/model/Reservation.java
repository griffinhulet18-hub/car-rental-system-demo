package com.carrental.model;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String vehicle;
    private String startDate;
    private String endDate;

    public Reservation() {}

    public Reservation(String username, String vehicle, String startDate, String endDate) {
        this.username = username;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // GETTERS (required!)
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getVehicle() { return vehicle; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    // SETTERS (required!)
    public void setUsername(String username) { this.username = username; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
