package com.apps.quantitymeasurement.implementation;

import java.util.Objects;


public class QuantityMeasurementApp {

    public static void main(String[] args) {

        boolean inchResult =
                QuantityEqualityService.compareInches(1.0, 1.0);

        boolean feetResult =
                QuantityEqualityService.compareFeet(1.0, 1.0);

        System.out.println("1.0 inch and 1.0 inch Equal? " + inchResult);
        System.out.println("1.0 ft and 1.0 ft Equal? " + feetResult);
    }
}