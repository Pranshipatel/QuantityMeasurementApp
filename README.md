# QuantityMeasurementApp

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
