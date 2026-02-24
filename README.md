# 📏 Quantity Measurement Application – UC4

## Extended Unit Support (Yards & Centimeters)

### Overview
UC4 extends the Quantity Measurement system by adding support for:

- **YARDS** (1 yard = 3 feet)
- **CENTIMETERS** (1 cm = 0.393701 inches)

The generic `QuantityLength` design allows new units to be added by modifying only the `LengthUnit` enum. No changes are required in the core comparison logic.

---

## Supported Units

- FEET
- INCHES
- YARDS
- CENTIMETERS

All cross-unit comparisons are supported (yard ↔ feet ↔ inches ↔ cm).

---

## Example Usage

```java
QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

System.out.println(q1.equals(q2)); // true
