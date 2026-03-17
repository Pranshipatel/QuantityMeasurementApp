package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.unit.IMeasurable;

public class QuantityModel<U extends IMeasurable> {
	public double value;
	public U unit;

	// Parameterized constructor
	public QuantityModel(double value, U unit) {
		this.value = value;
		this.unit = unit;
	}

	// Method to get value
	public double getValue() {
		return value;
	}

	// Method to get unit
	public U getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return "QuantityModel [value=" + value + ", unit=" + unit + "]";
	}
}