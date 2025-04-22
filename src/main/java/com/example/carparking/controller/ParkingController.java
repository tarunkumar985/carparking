package com.example.carparking.controller;



import com.example.carparking.dto.ParkingSlotDTO;
import com.example.carparking.dto.ParkingTicketDTO;
import com.example.carparking.service.ParkingService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
//@Tag(name = "Smart Parking API", description = "Vehicle entry, exit, reservation and history APIs")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    //  Vehicle Entry → POST /api/parking/entry
    @PostMapping("/entry")
    public ParkingTicketDTO vehicleEntry(@RequestBody ParkingTicketDTO ticketDTO) {
        return parkingService.handleVehicleEntry(ticketDTO);
    }

    //  Vehicle Exit & Fee Calculation → POST /api/parking/exit/{ticketId}
    @PostMapping("/exit/{ticketId}")
    public ParkingTicketDTO vehicleExit(@PathVariable Long ticketId) {
        return parkingService.handleVehicleExit(ticketId);
    }

    //  Get Available Parking Slots → GET /api/parking/available
    @GetMapping("/available")
    public List<ParkingSlotDTO> getAvailableSlots() {
        return parkingService.getAvailableSlots();
    }

    //  Reserve a Parking Slot → POST /api/parking/reserve
    @PostMapping("/reserve")
    public ParkingSlotDTO reserveSlot(@RequestBody ParkingSlotDTO slotDTO) {
        return parkingService.reserveSlot(slotDTO);
    }

    //  Cancel a Reservation → DELETE /api/parking/reserve/{id}
    @DeleteMapping("/reserve/{id}")
    public void cancelReservation(@PathVariable Long id) {
        parkingService.cancelReservation(id);
    }

    //  Search Vehicles by License Plate → GET /api/parking/search?query={licensePlate}
    @GetMapping("/search")
    public List<ParkingTicketDTO> searchByLicensePlate(@RequestParam String query) {
        return parkingService.searchByLicensePlate(query);
    }

    //  Get Parking History for a Vehicle → GET /api/parking/history/{licensePlate}
    @GetMapping("/history/{licensePlate}")
    public List<ParkingTicketDTO> getParkingHistory(@PathVariable String licensePlate) {
        return parkingService.getParkingHistory(licensePlate);
    }
    
    @GetMapping("/history")
    public Page<ParkingTicketDTO> getAllParkingHistory(
            @PageableDefault(size = 1, sort = "entryTime", direction = Direction.DESC) Pageable pageable) {
        return parkingService.getAllParkingHistory(pageable);
    }
}
