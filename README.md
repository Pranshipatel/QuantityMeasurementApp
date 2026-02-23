# QuantityMeasurementApp
---
# UC1 (Feet Equality)

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

---

# UC2 (Inches Equality)

## Overview
This project extends UC1 by adding **Inches equality** along with **Feet equality**.  
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
Output: true
```

## Test Cases
- testEquality_SameValue()
- testEquality_DifferentValue()
- testEquality_NullComparison()
- testEquality_NonNumericInput()
- testEquality_SameReference()
