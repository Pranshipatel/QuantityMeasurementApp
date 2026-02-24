# QuantityMeasurementApp

A Java application that demonstrates equality comparison of length measurements 
using object-oriented principles and progressive refactoring (UC1 → UC3).

---

# 📏 UC1 – Feet Equality

## Overview
This use case checks the equality of two numerical values measured in **feet**.  
It ensures proper floating-point comparison, null safety, and type safety.

## Features
- Compare two Feet values
- Uses `Double.compare()` for accurate comparison
- Null and type checking
- Follows equality contract rules

## Example
```
Input: 1.0 ft and 1.0 ft  
Output: Equal (true)
```

## Test Cases
- testEquality_SameValue()
- testEquality_DifferentValue()
- testEquality_NullComparison()
- testEquality_NonNumericInput()
- testEquality_SameReference()

---

# 📏 UC2 – Inches Equality

## Overview
This use case extends UC1 by adding **Inches equality** along with **Feet equality**.  
Feet and Inches are treated as separate classes.

## Features
- Compare Feet to Feet
- Compare Inches to Inches
- Compare Feet to Inches (1 ft = 12 inches)
- Null and type safety
- Floating-point comparison using `Double.compare()`

## Example
```
Input: 1.0 inch and 1.0 inch  
Output: Equal (true)
```

## Test Cases
- testEquality_SameValue()
- testEquality_DifferentValue()
- testEquality_NullComparison()
- testEquality_NonNumericInput()
- testEquality_SameReference()

---

# 📏 UC3 – Generic Quantity Class (DRY Principle)

## Overview
UC3 refactors the separate Feet and Inches classes into a single 
**QuantityLength** class using a **LengthUnit enum**.

This eliminates code duplication and follows the  
**DRY (Don't Repeat Yourself) principle** while preserving all UC1 and UC2 functionality.

## Features
- Single `QuantityLength` class for all length units
- `LengthUnit` enum for type-safe unit handling
- Cross-unit comparison (1 ft = 12 inches)
- Floating-point comparison using `Double.compare()`
- Null and type safety
- Backward compatible with UC1 & UC2

## Example
```
Input: Quantity(1.0, "feet") and Quantity(12.0, "inches")  
Output: Equal (true)

Input: Quantity(1.0, "inch") and Quantity(1.0, "inch")  
Output: Equal (true)
```

---
# 📏 Yard Equality – UC4

## Extended Unit Support (Yards & Centimeters)

### Overview
UC4 extends the Quantity Measurement system by adding support for:

- **YARDS** (1 yard = 3 feet)
- **CENTIMETERS** (1 cm = 0.393701 inches)

The generic `QuantityLength` design allows new units to be added by modifying only the `LengthUnit` enum. No changes are required in the core comparison logic.



## Supported Units

- FEET
- INCHES
- YARDS
- CENTIMETERS

All cross-unit comparisons are supported (yard ↔ feet ↔ inches ↔ cm).



---

# 📏  Unit-to-Unit Conversion – UC5  



## 📌 Overview

UC5 extends the Quantity Measurement system by introducing explicit **unit-to-unit conversion** between supported length units.

Supported Units:
- FEET
- INCHES
- YARDS
- CENTIMETERS

The API provides a static `convert()` method that converts a numeric value from a source unit to a target unit using centralized enum-based conversion factors.


## Example Usage
- convert(1.0, FEET, INCHES)       → 12.0
- convert(3.0, YARDS, FEET)       → 9.0
- convert(36.0, INCHES, YARDS)    → 1.0
- convert(1.0, CENTIMETERS, INCHES) → 0.393701
- convert(0.0, FEET, INCHES)      → 0.0
