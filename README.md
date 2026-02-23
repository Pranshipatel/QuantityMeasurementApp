# Quantity Measurement App – UC2

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

Input: 1.0 ft and 1.0 ft
Output: true
```

## Test Cases
- testEquality_SameValue()
- testEquality_DifferentValue()
- testEquality_NullComparison()
- testEquality_NonNumericInput()
- testEquality_SameReference()

## Limitation
Code duplication exists in Feet and Inches classes (violates DRY principle).

## Improvement
Use a single `Quantity` class with a `Unit` enum to reduce redundancy.
