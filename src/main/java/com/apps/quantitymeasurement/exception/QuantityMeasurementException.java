package com.apps.quantitymeasurement.exception;

public class QuantityMeasurementException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1194031611121239932L;

	public QuantityMeasurementException(String msg) {
		super(msg);
	}
	
	public QuantityMeasurementException(String msg, Throwable cause) {
		super(msg,cause);
	}
}