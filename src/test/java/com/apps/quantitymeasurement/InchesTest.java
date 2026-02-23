package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.features.Feet;
import com.apps.quantitymeasurement.features.Inches;

import static org.junit.jupiter.api.Assertions.*;

class InchesTest {

    @Test
    void testEquality_SameValue() {
        Inches i1 = new Inches(2.0);
        Inches i2 = new Inches(2.0);

        assertTrue(i1.equals(i2));
    }

    @Test
    void testEquality_DifferentValue() {
        Inches i1 = new Inches(3.0);
        Inches i2 = new Inches(4.0);

        assertFalse(i1.equals(i2));
    }

    @Test
    void testEquality_SameReference() {
        Inches i1 = new Inches(5.0);

        assertTrue(i1.equals(i1));
    }

    @Test
    void testEquality_NullComparison() {
        Inches i1 = new Inches(5.0);

        assertFalse(i1.equals(null));
    }

    @Test
    void testEquality_TypeMismatch() {
        Inches i1 = new Inches(1.0);
        Feet f1 = new Feet(1.0);

        assertFalse(i1.equals(f1));
    }

    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new Inches(Double.NaN));

        assertThrows(IllegalArgumentException.class,
                () -> new Inches(Double.NEGATIVE_INFINITY));
    }
}