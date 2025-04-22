package com.example.carparking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.carparking.dto.ParkingSlotDTO;
import com.example.carparking.dto.ParkingTicketDTO;

public interface ParkingService {
    ParkingTicketDTO handleVehicleEntry(ParkingTicketDTO ticketDTO);
    ParkingTicketDTO handleVehicleExit(Long ticketId);
    List<ParkingSlotDTO> getAvailableSlots();
    ParkingSlotDTO reserveSlot(ParkingSlotDTO slotDTO);
    void cancelReservation(Long id);
    List<ParkingTicketDTO> searchByLicensePlate(String licensePlate);
    List<ParkingTicketDTO> getParkingHistory(String licensePlate);
	
    
    
    
    Page<ParkingTicketDTO> getAllParkingHistory(Pageable pageable);
}