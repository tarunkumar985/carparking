package com.example.carparking.dto;

import java.time.LocalDateTime;

import com.example.carparking.entity.SlotType;

public class ParkingSlotDTO {
	private Long id;
	private String slotNumber;
	private SlotType slotType;
	private boolean occupied;
	private String occupiedByLicensePlate;
	private LocalDateTime occupiedAt;
	private LocalDateTime reservedUntil;
	
	public ParkingSlotDTO() {
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getOccupiedByLicensePlate() {
		return occupiedByLicensePlate;
	}

	public void setOccupiedByLicensePlate(String occupiedByLicensePlate) {
		this.occupiedByLicensePlate = occupiedByLicensePlate;
	}

	public LocalDateTime getOccupiedAt() {
		return occupiedAt;
	}

	public void setOccupiedAt(LocalDateTime occupiedAt) {
		this.occupiedAt = occupiedAt;
	}

	public LocalDateTime getReservedUntil() {
		return reservedUntil;
	}

	public void setReservedUntil(LocalDateTime reservedUntil) {
		this.reservedUntil = reservedUntil;
	}
}
