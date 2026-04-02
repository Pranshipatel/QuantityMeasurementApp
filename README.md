# QuantityMeasurementApp

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
