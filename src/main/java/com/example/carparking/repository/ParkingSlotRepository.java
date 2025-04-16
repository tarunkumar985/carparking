package com.example.carparking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carparking.entity.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
	
	/*
	 * Spring Data JPA supports derived queries, where it auto-generates the SQL
	 * based on the method name. Since your entity has a field named isOccupied,
	 * Spring will look for isOccupied = false. The is prefix is allowed in the
	 * field name, but when writing the method, just use the field name exactly as
	 * it is (isOccupied).
	 */
	 List<ParkingSlot> findByIsOccupiedFalse();
	 Optional<ParkingSlot> findBySlotNumber(String slotNumber);
}
