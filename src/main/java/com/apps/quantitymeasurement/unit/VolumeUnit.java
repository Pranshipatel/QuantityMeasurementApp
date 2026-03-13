package com.apps.quantitymeasurement.unit;

public enum VolumeUnit implements IMeasurable{
	
	LITRE(1.0),
	MILLILITRE(0.001),
	GALLON(3.78541);
	
	private double conversion;
	
	private VolumeUnit(double conversion) {
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
		return ((baseValue/this.getConversionFactor())*100.0)/100.0;
	}

	@Override
	public String getUnitName() {
		return VolumeUnit.class.getName();
	}
	
}