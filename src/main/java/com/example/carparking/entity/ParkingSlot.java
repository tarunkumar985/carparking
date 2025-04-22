package com.example.carparking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String slotNumber;

	@Column
	@Enumerated(EnumType.STRING)
	private SlotType slotType; // COMPACT, REGULAR, LARGE, ELECTRIC
	@Column
	private boolean isOccupied;
	@Column
	private String occupiedByLicensePlate;
	@Column
	private LocalDateTime occupiedAt;
	@Column
	private LocalDateTime reservedUntil;

	public ParkingSlot() {
	}

	public ParkingSlot(Long id, String slotNumber, SlotType slotType, boolean isOccupied, String occupiedByLicensePlate,
			LocalDateTime occupiedAt, LocalDateTime reservedUntil) {
		super();
		this.id = id;
		this.slotNumber = slotNumber;
		this.slotType = slotType;
		this.isOccupied = isOccupied;
		this.occupiedByLicensePlate = occupiedByLicensePlate;
		this.occupiedAt = occupiedAt;
		this.reservedUntil = reservedUntil;
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
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
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

	@Override
	public String toString() {
		return "ParkingSlot [id=" + id + ", slotNumber=" + slotNumber + ", slotType=" + slotType + ", isOccupied="
				+ isOccupied + ", occupiedByLicensePlate=" + occupiedByLicensePlate + ", occupiedAt=" + occupiedAt
				+ ", reservedUntil=" + reservedUntil + "]";
	}

}
