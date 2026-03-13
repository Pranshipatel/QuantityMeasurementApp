package com.apps.quantitymeasurement.unit;

public enum LengthUnit implements IMeasurable{
	FEET(1.0),
	INCHES(1.0/12.0),
	YARD(3.0),
	CENTIMETERS(0.0328084);
	
	private double conversion;
	
	LengthUnit(double conversion){
		this.conversion = conversion;
	}
	
	@Override
	public double getConversionFactor() {
		return conversion;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return ((value*this.getConversionFactor())*100.0)/100.0;
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		return baseValue/this.getConversionFactor();
	}

	@Override
	public String getUnitName() {
		return LengthUnit.this.toString();
	}
}