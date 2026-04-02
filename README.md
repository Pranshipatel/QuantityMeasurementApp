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

---
#  📏 UC6: Addition of Two Length Units

## Description
Extends UC5 by adding two `QuantityLength` objects (potentially different units). Result is expressed in the unit of the first operand. Both operands are normalized to base unit before summing.

## Flow
1. Validate both operands (non-null, finite, valid units).
2. Convert both to base unit (feet).
3. Sum converted values.
4. Convert sum → first operand's unit.
5. Return new `QuantityLength` (immutability preserved).

## Key Concepts
- Immutability: addition returns new instance
- Commutativity: `add(A, B)` = `add(B, A)`
- Method overloading for flexible API

---
# 📏 UC7: Addition with Explicit Target Unit

## Description
Extends UC6 by allowing the caller to specify any supported unit as the result unit, regardless of the operands' units. Uses a private utility method to avoid code duplication across overloaded `add()` methods.

## Flow
1. Validate operands and target unit (non-null, finite).
2. Convert both to base unit → sum.
3. Convert sum → explicitly specified `targetUnit`.
4. Return new `QuantityLength` in target unit.

## Key Concepts
- Method overloading: `add(l1, l2)` implicit vs `add(l1, l2, targetUnit)` explicit
- Private utility method eliminates DRY violation between overloads
- Commutativity holds for any target unit

---
# 📏 UC8: Refactoring LengthUnit to Standalone Enum

## Description
Extracts `LengthUnit` from inside `QuantityLength` into a standalone top-level class. Assigns conversion responsibility to the enum itself. `QuantityLength` is simplified to delegate all conversions to unit methods. All UC1–UC7 functionality preserved.

## Flow
1. `LengthUnit` enum handles `convertToBaseUnit()` and `convertFromBaseUnit()`.
2. `QuantityLength` delegates all conversions to unit methods.
3. Public API remains unchanged → backward compatible.

## Key Concepts
- Single Responsibility: `LengthUnit` converts, `QuantityLength` compares/adds
- Eliminates circular dependency for multi-category scaling
- Pattern template for future `WeightUnit`, `VolumeUnit`, etc.

---

# 📏 UC9: Weight Measurement (Equality, Conversion & Addition)

## Description
Introduces a new `WeightUnit` enum and `QuantityWeight` class mirroring the UC8 length pattern. Supports equality, conversion, and addition for KILOGRAM, GRAM, and POUND. Weight and length are incompatible categories.

## Conversion Factors (base: KILOGRAM)
| Unit | Factor |
|------|--------|
| KILOGRAM | 1.0 |
| GRAM | 0.001 |
| POUND | 0.453592 |

## Key Concepts
- `WeightUnit` standalone enum with `convertToBaseUnit()` / `convertFromBaseUnit()`
- Category type safety: `Quantity(1.0, KG).equals(Quantity(1.0, FOOT))` → `false`
- Overloaded `add()`: implicit (first operand unit) and explicit (target unit)
- `hashCode()` overridden consistently with `equals()`

---

# 📏  UC10: Generic Quantity Class with IMeasurable Interface

## Description
Refactors `QuantityLength` and `QuantityWeight` into a single generic `Quantity<U extends IMeasurable>` class. Eliminates code duplication across categories using a common interface. All UC1–UC9 functionality preserved.

## Architecture
| Component | Responsibility |
|-----------|---------------|
| `IMeasurable` | Defines unit conversion contract |
| `LengthUnit` / `WeightUnit` | Implement `IMeasurable` with conversion factors |
| `Quantity<U>` | Handles equality, conversion, addition for any unit |
| `QuantityMeasurementApp` | Generic demonstration only |

## Key Concepts
- Bounded type parameter `<U extends IMeasurable>` for compile-time type safety
- Cross-category prevention via `unit.getClass()` comparison
- `equals()`, `convertTo()`, `add()` implemented once — reused for all categories
- Adding new categories requires ONLY a new enum implementing `IMeasurable`

---

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

# 📏 UC12: Subtraction and Division Operations

## Description
Extends `Quantity<U>` with subtraction (returns `Quantity<U>`) and division (returns dimensionless `double`). Both operations support cross-unit arithmetic within the same category and maintain immutability.

## Operations
| Method | Returns | Notes |
|--------|---------|-------|
| `subtract(other)` | `Quantity<U>` | Result in first operand's unit |
| `subtract(other, targetUnit)` | `Quantity<U>` | Result in explicit unit |
| `divide(other)` | `double` | Dimensionless ratio |

## Key Concepts
- Subtraction is **non-commutative**: `A - B ≠ B - A`
- Division is **non-commutative**: `A ÷ B ≠ B ÷ A`
- Division by zero throws `ArithmeticException`
- Cross-category operations throw `IllegalArgumentException`

---

# 📏 UC13: Centralized Arithmetic Logic (DRY Refactoring)

## Description
Refactors UC12's `add()`, `subtract()`, and `divide()` to eliminate duplicated validation and conversion logic by introducing a centralized private helper method and an `ArithmeticOperation` enum. Public API is unchanged; all UC12 behavior preserved.

## Internal Architecture
| Component | Role |
|-----------|------|
| `ArithmeticOperation` enum | Dispatches ADD, SUBTRACT, DIVIDE via `compute(a, b)` |
| `validateArithmeticOperands()` | Centralized null, category, finiteness checks |
| `performBaseArithmetic()` | Converts to base unit → executes operation → returns result |


## Key Concepts
- All validation defined once → consistent errors across all operations

---
# 📏 UC14: Temperature with Selective Arithmetic Support  

## Description  
Introduces **Temperature (Celsius, Fahrenheit, Kelvin)** support and refactors `IMeasurable` to enable **optional arithmetic operations**.  
Ensures that measurement categories with different constraints (like temperature) are handled correctly without breaking existing functionality.

## Operations  
| Method | Returns | Notes |
|--------|---------|-------|
| `equals(other)` | `boolean` | Supports comparison across temperature units |
| `convertTo(targetUnit)` | `Quantity<U>` | Uses non-linear conversion formulas |
| `add(other)` | ❌ Unsupported | Throws `UnsupportedOperationException` |
| `subtract(other)` | ❌ Unsupported | Throws `UnsupportedOperationException` |
| `divide(other)` | ❌ Unsupported | Throws `UnsupportedOperationException` |

## Key Concepts  
- Temperature does **not support arithmetic operations**  
- Introduced **default methods** in `IMeasurable` for optional behavior  
- Added validation to **restrict unsupported operations**  
- Ensures **fail-fast execution** using clear exceptions  
- Maintains **backward compatibility**  
- Follows **Interface Segregation Principle (ISP)**  

---

# 📏 UC15: N-Tier Architecture Refactoring  

## Description  
Refactors the Quantity Measurement Application into a **clean N-Tier architecture** for better scalability, maintainability, and testability while preserving all existing functionality (UC1–UC14).

## Layers  
| Layer | Responsibility |
|------|----------------|
| Application | Entry point (`QuantityMeasurementApp`) |
| Controller | Handles requests (`QuantityMeasurementController`) |
| Service | Business logic (`QuantityMeasurementServiceImpl`) |
| Repository | Data handling (`IQuantityMeasurementRepository`, Cache Singleton) |
| Model/Entity | Data transfer & persistence (`QuantityDTO`, Model, Entity) |

## Key Concepts  
- **Separation of concerns** across layers  
- Business logic reusable for **CLI / REST / GUI**  
- Improved **testability & maintainability**  
- Supports **Dependency Injection**  
- Uses design patterns:  
  - **Singleton** (Repository)  
  - **Factory** (Object creation)  
  - **Facade** (Controller layer)  
  - **Dependency Injection** (Loose coupling)  

---
