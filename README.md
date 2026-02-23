# QuantityMeasurementApp

UC1 (Feet Equality)

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

