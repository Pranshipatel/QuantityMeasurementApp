# Quantity Measurement App – UC3 (Generic Quantity Class)

## Overview
UC3 refactors the separate Feet and Inches classes into a single **QuantityLength** class using a **LengthUnit enum**.  
This eliminates code duplication and follows the **DRY (Don't Repeat Yourself) principle** while preserving all UC1 and UC2 functionality.

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

## Concepts Covered
- DRY Principle
- Enum Usage
- Polymorphism
- Abstraction & Encapsulation
- Equality Contract (Reflexive, Symmetric, Transitive, Consistent)
- Value-Based Equality
- Unit Conversion Logic
- Scalability & Refactoring

## Test Coverage
- Feet to Feet equality
- Inch to Inch equality
- Cross-unit equality (Feet ↔ Inches)
- Different value inequality
- Null handling
- Invalid unit validation
- Same reference equality
