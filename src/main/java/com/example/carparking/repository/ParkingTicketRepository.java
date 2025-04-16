package com.example.carparking.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carparking.entity.ParkingTicket;

import java.util.List;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    List<ParkingTicket> findByLicensePlateContainingIgnoreCase(String licensePlate);
    List<ParkingTicket> findByLicensePlateOrderByEntryTimeDesc(String licensePlate);
}
