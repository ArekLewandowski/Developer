package com.capgemini.exceptions;

public class OverReservationLimitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4475597739938561075L;

	public OverReservationLimitException() {

	}

	public OverReservationLimitException(String message) {
		super(message);
	}
}
