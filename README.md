# Quantity Measurement App – UC1 (Feet Equality)

## Overview
This application checks the equality of two numerical values measured in **feet**.  
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

## Concepts Covered
- Object Equality (`equals()` method)
- Floating-point comparison
- Null Safety
- Type Safety
- Encapsulation & Immutability
- Equality Contract (Reflexive, Symmetric, Transitive, Consistent)

## Notes
The `Feet` class stores the measurement as a private final field to ensure immutability and value-based equality.
