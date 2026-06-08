# Proyecto LogiSmart: Sistema de Gestión Logística

## Descripción
LogiSmart es un sistema de gestión logística de alto rendimiento diseñado bajo una **Arquitectura de 4 Capas (Clean Architecture)**. El objetivo principal es la gestión de envíos, vehículos y clientes, priorizando el desacoplamiento mediante patrones de diseño avanzados para garantizar que los cambios en las reglas de negocio no impacten en la estabilidad estructural.

## Arquitectura del Proyecto
El código fuente está aislado por responsabilidades:
- `domain/`: Entidades de negocio puras y reglas de dominio (agnósticas a frameworks o bases de datos).
- `application/`: Casos de uso, validaciones y orquestación de operaciones transaccionales.
- `persistence/`: Capa de acceso a datos que aísla las consultas SQL del modelo de objetos.
- `infrastructure/`: Implementaciones de soporte técnico (Notificaciones, Lazy Loading, testing).

## Cómo probar y ejecutar el proyecto

El proyecto no requiere dependencias externas complejas, ya que utiliza Java puro. Para probar su funcionamiento, existen dos puntos de entrada principales:

### 1. Ejecutar el Flujo Principal (Main)
El flujo completo de negocio (creación, validación, costeo y persistencia simulada) se ejecuta desde la clase de integración.
- **Archivo:** `src/com/logismart/infrastructure/main/MainIntegracion.java`
- **Acción:** Compilar y ejecutar este archivo para visualizar la traza de operaciones, eventos del mediador y registros de auditoría en consola.

### 2. Ejecutar la Suite de Pruebas Integradas
Para validar la integridad de la arquitectura y asegurar que las reglas de negocio funcionan correctamente, se provee una suite de pruebas.
- **Archivo:** `src/com/logismart/infrastructure/test/SuitePruebasIntegracion.java`
- **Acción:** Ejecutar para correr los casos de prueba (flujo feliz, errores de validación, etc.) y verificar los `[PASS]` del sistema.
