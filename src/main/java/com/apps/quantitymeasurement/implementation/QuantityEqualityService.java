package com.apps.quantitymeasurement.implementation;

import com.apps.quantitymeasurement.features.Feet;
import com.apps.quantitymeasurement.features.Inches;

public class QuantityEqualityService {

    public static boolean compareFeet(double value1, double value2) {
        Feet f1 = new Feet(value1);
        Feet f2 = new Feet(value2);
        return f1.equals(f2);
    }

    public static boolean compareInches(double value1, double value2) {
        Inches i1 = new Inches(value1);
        Inches i2 = new Inches(value2);
        return i1.equals(i2);
    }
}