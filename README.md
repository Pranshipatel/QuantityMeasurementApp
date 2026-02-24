# 📏 Quantity Measurement Application – UC5  
## Unit-to-Unit Conversion 

---
## 📌 Overview

UC5 extends the Quantity Measurement system by introducing explicit **unit-to-unit conversion** between supported length units.

Supported Units:
- FEET
- INCHES
- YARDS
- CENTIMETERS

The API provides a static `convert()` method that converts a numeric value from a source unit to a target unit using centralized enum-based conversion factors.

---
## Example Usage
- convert(1.0, FEET, INCHES)       → 12.0
- convert(3.0, YARDS, FEET)       → 9.0
- convert(36.0, INCHES, YARDS)    → 1.0
- convert(1.0, CENTIMETERS, INCHES) → 0.393701
- convert(0.0, FEET, INCHES)      → 0.0
