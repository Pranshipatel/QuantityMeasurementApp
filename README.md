# QuantityMeasurementApp

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


