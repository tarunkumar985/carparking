package com.example.carparking.exception;


public class ParkingSlotNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParkingSlotNotFoundException(String message) {
        super(message);
    }
}
