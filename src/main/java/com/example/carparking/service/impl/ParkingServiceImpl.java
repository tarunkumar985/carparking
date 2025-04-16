package com.example.carparking.service.impl;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.carparking.dto.ParkingMapper;
import com.example.carparking.dto.ParkingSlotDTO;
import com.example.carparking.dto.ParkingTicketDTO;
import com.example.carparking.entity.ParkingSlot;
import com.example.carparking.entity.ParkingTicket;
import com.example.carparking.exception.ParkingSlotNotFoundException;
import com.example.carparking.repository.ParkingSlotRepository;
import com.example.carparking.repository.ParkingTicketRepository;
import com.example.carparking.service.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSlotRepository slotRepository;
    private final ParkingTicketRepository ticketRepository;
    private final ParkingMapper mapper;

    public ParkingServiceImpl(ParkingSlotRepository slotRepository,
                              ParkingTicketRepository ticketRepository,
                              ParkingMapper mapper) {
        this.slotRepository = slotRepository;
        this.ticketRepository = ticketRepository;
        this.mapper = mapper;
    }

    @Override
    public ParkingTicketDTO handleVehicleEntry(ParkingTicketDTO ticketDTO) {
        List<ParkingSlot> availableSlots = slotRepository.findByIsOccupiedFalse();

        ParkingSlot slot = availableSlots.stream()
                .filter(s -> s.getReservedUntil() == null || s.getReservedUntil().isBefore(LocalDateTime.now()))
                .findFirst()
                .orElseThrow(() -> new ParkingSlotNotFoundException("No available parking slots"));

        slot.setOccupied(true);
        slot.setOccupiedAt(LocalDateTime.now());
        slot.setOccupiedByLicensePlate(ticketDTO.getLicensePlate());

        ParkingTicket ticket = new ParkingTicket();
        ticket.setLicensePlate(ticketDTO.getLicensePlate());
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setSlotNumber(slot.getSlotNumber());

        slotRepository.save(slot);
        ParkingTicket saved = ticketRepository.save(ticket);

        return mapper.toTicketDTO(saved);
    }

    @Override
    public ParkingTicketDTO handleVehicleExit(Long ticketId) {
        ParkingTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ParkingSlot slot = slotRepository.findBySlotNumber(ticket.getSlotNumber())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        LocalDateTime exitTime = LocalDateTime.now();
        ticket.setExitTime(exitTime);

        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        BigDecimal fee = calculateFee(duration); // Dynamic pricing

        ticket.setFeeCharged(fee);

        slot.setOccupied(false);
        slot.setOccupiedAt(null);
        slot.setOccupiedByLicensePlate(null);

        slotRepository.save(slot);
        ticketRepository.save(ticket);

        return mapper.toTicketDTO(ticket);
    }

    private BigDecimal calculateFee(Duration duration) {
        long hours = Math.max(1, duration.toHours()); // minimum 1 hour
        return BigDecimal.valueOf(hours * 10); // Flat rate: $10/hr (can vary by slotType)
    }

    @Override
    public List<ParkingSlotDTO> getAvailableSlots() {
        List<ParkingSlot> slots = slotRepository.findByIsOccupiedFalse();
        return mapper.toSlotDTOList(slots.stream()
                .filter(s -> s.getReservedUntil() == null || s.getReservedUntil().isBefore(LocalDateTime.now()))
                .toList());
    }

    @Override
    public ParkingSlotDTO reserveSlot(ParkingSlotDTO slotDTO) {
        ParkingSlot slot = slotRepository.findById(slotDTO.getId())
                .orElseThrow(() -> new ParkingSlotNotFoundException("Slot not found"));

        slot.setReservedUntil(LocalDateTime.now().plusHours(1));
        return mapper.toSlotDTO(slotRepository.save(slot));
    }

    @Override
    public void cancelReservation(Long id) {
        ParkingSlot slot = slotRepository.findById(id)
                .orElseThrow(() -> new ParkingSlotNotFoundException("Slot not found"));
        slot.setReservedUntil(null);
        slotRepository.save(slot);
    }

    @Override
    public List<ParkingTicketDTO> searchByLicensePlate(String licensePlate) {
        return mapper.toTicketDTOList(ticketRepository.findByLicensePlateContainingIgnoreCase(licensePlate));
    }

    @Override
    public List<ParkingTicketDTO> getParkingHistory(String licensePlate) {
        return mapper.toTicketDTOList(ticketRepository.findByLicensePlateOrderByEntryTimeDesc(licensePlate));
    }
}
