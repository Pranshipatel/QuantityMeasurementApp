package com.app.quantitymeasurement.model;

public enum OperationType {
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	COMPARE,
	CONVERT;
	
	//Optional : Add display names
	public String getDisplayName() {
		return this.name().toLowerCase();
	}
}