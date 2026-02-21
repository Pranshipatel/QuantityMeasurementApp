package com.apps.quantitymeasurement;

import java.util.Objects;


public class QuantityMeasurementApp {
	public static class Feet{
		private final double value;
		
		//constructor
		public Feet(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
		
		@Override
		public boolean equals(Object obj) {
			
			// Same reference
			if(this == obj) {
				return true;
			}
			
			// Null check
			if(obj == null) {
				return false;
			}
			
			// Type check
			if(getClass() != obj.getClass()) {
				return false;
			}
			
			Feet other = (Feet)obj;
			
			// Compare double
			return Double.compare(this.value, other.value) == 0;
			
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(value);
		}
	}
	
	public static void main(String[] args) {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);
		
		boolean result = f1.equals(f2);
		
		System.out.println("Input : 1.0 ft and 1.0 ft");
		System.out.println("Output: Equal (" + result + ")");
	}


}
