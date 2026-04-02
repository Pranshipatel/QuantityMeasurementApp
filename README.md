# QuantityMeasurementApp

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
