# QuantityMeasurementApp

# 📏 UC11: Volume Measurement (Litre, Millilitre, Gallon)

## Description
Adds a third measurement category — volume — by creating a `VolumeUnit` enum implementing `IMeasurable`. No changes to `Quantity<U>`, `QuantityMeasurementApp`, or existing tests required. Proves the UC10 architecture scales linearly.

## Conversion Factors (base: LITRE)
| Unit | Factor |
|------|--------|
| LITRE | 1.0 |
| MILLILITRE | 0.001 |
| GALLON | 3.78541 |

## Key Concepts
- Only a new enum needed to add a full measurement category
- Cross-category safety: `1.0 LITRE ≠ 1.0 KILOGRAM` and `1.0 LITRE ≠ 1.0 FOOT`
- All generic `Quantity<U>` operations work automatically

---
