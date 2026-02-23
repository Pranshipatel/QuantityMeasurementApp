package com.apps.quantitymeasurement.features;

public class Feet {

    private final double value;

    public Feet(double value) {
        validate(value);
        this.value = value;
    }

    private void validate(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value for Feet");
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Feet other = (Feet) obj;

        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}