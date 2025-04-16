package com.example.carparking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingTicketDTO {
	private Long id;
	private String licensePlate;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private BigDecimal feeCharged;
	private String slotNumber;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public BigDecimal getFeeCharged() {
		return feeCharged;
	}

	public void setFeeCharged(BigDecimal feeCharged) {
		this.feeCharged = feeCharged;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}
}
