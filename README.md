# Proyecto LogiSmart - Sistema Logístico (Arquitectura Clean & GoF)

## 👥 Equipo de Desarrollo
- **Santino Tanus Linares**
- **Ayrton Ferreira Nieto**
- **Renata Agüera**

## 📝 Descripción
Este proyecto representa la entrega final (Hitos 1 a 14) de LogiSmart. Consiste en un sistema backend puro en Java, estructurado bajo una **Arquitectura de 4 Capas**, que implementa exhaustivamente patrones de diseño creacionales, estructurales y de comportamiento para garantizar un acoplamiento mínimo y alta cohesión.

## 📦 Estructura del Proyecto (Clean Architecture)
- `domain/`: Núcleo del negocio. Contiene `Envio`, Estados (`State`) y Cálculos (`Strategy`). 100% independiente.
- `application/`: Casos de uso. Orquesta validaciones (`Chain`), flujos de trabajo (`Template Method`) y transacciones (`Command`).
- `persistence/`: Capa de abstracción de datos (`Repository`, `Data Mapper`, `Unit of Work`).
- `infrastructure/`: Soporte técnico, notificaciones (`Observer`), historial (`Memento`) y testing.

## 🚀 Cómo Ejecutar la Demostración (Live Demo)
Para la defensa del proyecto, se ha preparado un script narrativo que ejecuta los patrones principales en vivo.
1. Navegue hasta `src/com/logismart/infrastructure/main/MainIntegracion.java`.
2. Compile y ejecute el archivo.
3. **Resultado esperado:** La consola mostrará un flujo paso a paso evidenciando el `Template Method`, la captura del estado con `Memento`, la ejecución del viaje mediante `Command` y, finalmente, un *Rollback* simulando una emergencia.

## 🧪 Cómo Ejecutar la Suite de Pruebas Integradas
Se provee una suite exhaustiva diseñada para estresar la máquina de estados, el motor de estrategias y la cadena de validaciones.
1. Navegue hasta `src/com/logismart/infrastructure/test/SuitePruebasIntegracion.java`.
2. Habilite las aserciones en la JVM (flag `-ea`).
3. Compile y ejecute el archivo.
4. **Resultado esperado:** Ejecución de 5 módulos de testing con **42 validaciones (asserts) exitosas**.
