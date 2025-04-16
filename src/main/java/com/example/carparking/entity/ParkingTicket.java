package com.example.carparking.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_tickets")
public class ParkingTicket  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	 @Column
    private String licensePlate;
	 @Column
    private LocalDateTime entryTime;
	 @Column
    private LocalDateTime exitTime;

	 @Column
    private BigDecimal feeCharged;
	 @Column
    private String slotNumber;
    public ParkingTicket() {
    	
    }
    
	public ParkingTicket(Long id, String licensePlate, LocalDateTime entryTime, LocalDateTime exitTime,
			BigDecimal feeCharged, String slotNumber) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.feeCharged = feeCharged;
		this.slotNumber = slotNumber;
	}
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
	@Override
	public String toString() {
		return "ParkingTicket [id=" + id + ", licensePlate=" + licensePlate + ", entryTime=" + entryTime + ", exitTime="
				+ exitTime + ", feeCharged=" + feeCharged + ", slotNumber=" + slotNumber + "]";
	}
    
}
