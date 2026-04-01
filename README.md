# QuantityMeasurementApp

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
