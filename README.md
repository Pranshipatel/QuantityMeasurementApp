# QuantityMeasurementApp
# 📏 UC16: JDBC Persistence Integration  

## Description  
Adds **JDBC-based database persistence** to the N-Tier architecture, replacing in-memory storage with a scalable solution.

## Enhancements  
- JDBC repository with CRUD support  
- Config via `application.properties`  
- Connection pooling  
- Custom `DatabaseException`  
- Cache + DB switch using DI  
- SLF4J logging, Maven setup  

## Key Concepts  
- Persistent storage with secure SQL (**PreparedStatement**)  
- **Connection pooling** for performance  
- **Dependency Injection** for flexibility  

## Database  
- H2 (dev/testing), schema-based  
- Supports query, filter, count, delete  

## Testing  
- Unit + integration tests (H2)  
- Pool & SQL injection validation  

## Commands  
- `mvn clean compile`  
- `mvn exec:java`  
- `mvn clean test`  

## Improvements  
- In-memory → DB  
- No queries → SQL support  
- No pooling → Optimized performance  

---
