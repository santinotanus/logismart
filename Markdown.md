# Documento de Arquitectura y Decisiones de Diseño (ADR)
**Proyecto:** LogiSmart - Plataforma de Optimización Logística
**Equipo de Desarrollo:** - Santino Tanus Linares
- Ayrton Ferreira Nieto
- Renata Agüera
**Enfoque:** Proceso Unificado (RUP) y Clean Architecture

## 1. Visión General de la Arquitectura
El sistema LogiSmart fue concebido bajo el paradigma de **Arquitectura de 4 Capas** (Clean Architecture), asegurando que el diseño sea flexible, mantenible y escalable. Esta separación de responsabilidades garantiza que la capa de Dominio (reglas de negocio) sea completamente agnóstica a la infraestructura, la interfaz de usuario y los motores de persistencia.

## 2. Justificación de Patrones de Diseño por Capa

Se ha priorizado responder al "por qué" detrás de cada patrón, evitando la sobreingeniería y resolviendo problemas específicos de acoplamiento y cohesión.

### A. Capa de Dominio (Reglas de Negocio Core)
El objetivo fue crear un "Dominio Rico", eliminando condicionales anidados y permitiendo la extensibilidad.
* **State (Hito 13):** Utilizado para gestionar el ciclo de vida del `Envio`.
    * *Justificación:* Un envío logístico tiene transiciones estrictas (ej. no puede pasar de "En Tránsito" a "Entregado" sin pasar por "En Reparto"). En lugar de utilizar un `switch-case` masivo en la clase `Envio` que viole el principio Open/Closed, el patrón `State` delega el comportamiento a clases concretas. Si el negocio requiere un nuevo estado aduanero, se crea una nueva clase sin modificar el código existente.
* **Strategy (Hito 13):** Aplicado al cálculo de tarifas.
    * *Justificación:* Las reglas de facturación son volátiles. Extraer los algoritmos a estrategias (`CostoPorPeso`, `CostoHibrido`) permite inyectar el cálculo dinámicamente en tiempo de ejecución, aislando la entidad `Envio` de las fluctuaciones comerciales.

### B. Capa de Aplicación (Orquestación y Casos de Uso)
Esta capa actúa como el cerebro transaccional, coordinando la infraestructura y el dominio sin acoplarlos.
* **Command & Memento (Hitos 10 y 11):** Integrados para la gestión de transacciones.
    * *Justificación:* En logística, los errores humanos o de ruta exigen revertir operaciones críticas. `Command` encapsula la solicitud de cambio de estado, mientras que `Memento` guarda una "foto" de la entidad antes de la operación. Juntos, permiten implementar un sistema de *Rollback* (Undo) seguro.
* **Template Method (Hito 13):** Define el esqueleto de los procesos de envío.
    * *Justificación:* Los envíos Nacionales e Internacionales comparten pasos (validar documentos, calcular aranceles, asignar transporte) pero difieren en la implementación. El `Template Method` en `ProcesoEnvio` impone un orden algorítmico estricto, evitando que un desarrollador olvide cobrar aranceles antes de despachar.
* **Mediator & Observer (Hito 11):** Sistema de eventos y notificaciones.
    * *Justificación:* Evita que el flujo principal instancie servicios de base de datos para auditoría o APIs de correo para notificaciones. El `Mediator` escucha los eventos del dominio y los distribuye silenciosamente a los `Observers` correspondientes, garantizando un acoplamiento nulo (Low Coupling).
* **Chain of Responsibility (Hito 10):** Tubería de validaciones.
    * *Justificación:* En lugar de un bloque de 50 líneas validando peso, volumen y destino, se encadenaron validadores independientes. Si falla una validación, la cadena se corta, optimizando el rendimiento.

### C. Capa de Persistencia (Acceso a Datos)
* **Repository, Data Mapper y Unit of Work (Hito 13/14):** Aislamiento de SQL.
    * *Justificación:* Aislar las sentencias SQL (simuladas aquí para SQL Server) del dominio. El `Data Mapper` traduce las filas relacionales a objetos puros de Java. El `Unit of Work` agrupa múltiples inserciones garantizando que todas pasen o se reviertan de forma atómica (Atomicidad Transaccional), previniendo bases de datos inconsistentes.

## 3. Conclusión
La arquitectura resultante no es simplemente una colección de clases; es un sistema robusto diseñado para absorber el impacto del cambio. Las decisiones tomadas permiten testear la lógica de negocio de forma aislada y facilitan la futura integración de interfaces gráficas o migraciones a la nube sin reescribir el Core del sistema.