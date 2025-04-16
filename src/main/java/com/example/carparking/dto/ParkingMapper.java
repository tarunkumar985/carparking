package com.example.carparking.dto;



import java.util.List;

import org.mapstruct.Mapper;

import com.example.carparking.entity.ParkingSlot;
import com.example.carparking.entity.ParkingTicket;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    // ParkingSlot Mapping
    ParkingSlotDTO toSlotDTO(ParkingSlot slot);
    ParkingSlot toSlotEntity(ParkingSlotDTO dto);
    List<ParkingSlotDTO> toSlotDTOList(List<ParkingSlot> slots);

    // ParkingTicket Mapping
    ParkingTicketDTO toTicketDTO(ParkingTicket ticket);
    ParkingTicket toTicketEntity(ParkingTicketDTO dto);
    List<ParkingTicketDTO> toTicketDTOList(List<ParkingTicket> tickets);
}
