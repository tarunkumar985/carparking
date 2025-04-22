package com.example.carparking;
import com.example.carparking.dto.ParkingMapper;
import com.example.carparking.dto.ParkingTicketDTO;
import com.example.carparking.entity.ParkingSlot;
import com.example.carparking.entity.ParkingTicket;
import com.example.carparking.exception.ParkingSlotNotFoundException;
import com.example.carparking.repository.ParkingSlotRepository;
import com.example.carparking.repository.ParkingTicketRepository;
import com.example.carparking.service.impl.ParkingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingServiceImplTest {

    @Mock private ParkingSlotRepository slotRepository;
    @Mock private ParkingTicketRepository ticketRepository;
    @Mock private ParkingMapper mapper;

    @InjectMocks private ParkingServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

   
    @Test
    void testHandleVehicleEntry_Success() {
        ParkingTicketDTO inputDTO = new ParkingTicketDTO();
        inputDTO.setLicensePlate("ABC123");

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber("A1");
        slot.setOccupied(false);

        when(slotRepository.findByIsOccupiedFalse()).thenReturn(List.of(slot));

        ParkingTicket ticket = new ParkingTicket();
        ticket.setLicensePlate("ABC123");
        ticket.setSlotNumber("A1");

        ParkingTicket saved = new ParkingTicket();
        saved.setId(1L);

        when(ticketRepository.save(any())).thenReturn(saved);
        when(slotRepository.save(any())).thenReturn(slot);
        when(mapper.toTicketDTO(saved)).thenReturn(new ParkingTicketDTO());

        ParkingTicketDTO result = service.handleVehicleEntry(inputDTO);

        assertNotNull(result);
        verify(slotRepository).save(slot);
        verify(ticketRepository).save(any(ParkingTicket.class));
    }

    @Test
    void testHandleVehicleEntry_NoAvailableSlot() {
        when(slotRepository.findByIsOccupiedFalse()).thenReturn(Collections.emptyList());

        ParkingTicketDTO inputDTO = new ParkingTicketDTO();
        inputDTO.setLicensePlate("XYZ999");

        assertThrows(ParkingSlotNotFoundException.class, () -> {
            service.handleVehicleEntry(inputDTO);
        });
    }

    @Test
    void testHandleVehicleExit_Success() {
        Long ticketId = 1L;
        ParkingTicket ticket = new ParkingTicket();
        ticket.setId(ticketId);
        ticket.setEntryTime(LocalDateTime.now().minusHours(2));
        ticket.setSlotNumber("A1");

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber("A1");

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(slotRepository.findBySlotNumber("A1")).thenReturn(Optional.of(slot));
        when(ticketRepository.save(any())).thenReturn(ticket);
        when(slotRepository.save(any())).thenReturn(slot);
        when(mapper.toTicketDTO(ticket)).thenReturn(new ParkingTicketDTO());

        ParkingTicketDTO result = service.handleVehicleExit(ticketId);

        assertNotNull(result);
        assertNotNull(ticket.getExitTime());
        assertEquals(BigDecimal.valueOf(20), ticket.getFeeCharged()); // 2 hours * 10
    }

    @Test
    void testHandleVehicleExit_TicketNotFound() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.handleVehicleExit(1L));
    }

    @Test
    void testHandleVehicleExit_SlotNotFound() {
        ParkingTicket ticket = new ParkingTicket();
        ticket.setId(1L);
        ticket.setSlotNumber("B2");
        ticket.setEntryTime(LocalDateTime.now().minusHours(1));

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(slotRepository.findBySlotNumber("B2")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.handleVehicleExit(1L));
    }
}
