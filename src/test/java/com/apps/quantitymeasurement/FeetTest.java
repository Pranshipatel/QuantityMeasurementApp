package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.features.Feet;
import com.apps.quantitymeasurement.features.Inches;

import static org.junit.jupiter.api.Assertions.*;

class FeetTest {

    @Test
    void testEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void testEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void testEquality_SameReference() {
        Feet f1 = new Feet(5.0);

        assertTrue(f1.equals(f1));
    }

    @Test
    void testEquality_NullComparison() {
        Feet f1 = new Feet(5.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void testEquality_TypeMismatch() {
        Feet f1 = new Feet(1.0);
        Inches i1 = new Inches(1.0);

        assertFalse(f1.equals(i1));
    }

    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new Feet(Double.NaN));

        assertThrows(IllegalArgumentException.class,
                () -> new Feet(Double.POSITIVE_INFINITY));
    }
}