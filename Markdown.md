# Documento de Arquitectura y Decisiones de Diseño (ADR)
**Proyecto:** LogiSmart - Plataforma de Optimización Logística
**Equipo de Desarrollo:**

- Santino Tanus Linares
- Ayrton Ferreira Nieto
- Renata Agüera

**Enfoque:** Proceso Unificado (RUP) y Clean Architecture

---

## 1. Visión General de la Arquitectura
El sistema LogiSmart fue concebido bajo el paradigma de **Arquitectura de 4 Capas** (Clean Architecture), asegurando que el diseño sea flexible, mantenible y escalable. Esta separación de responsabilidades garantiza que la capa de Dominio (reglas de negocio centrales) sea completamente agnóstica a la infraestructura, la interfaz de usuario y los motores de persistencia subyacentes.

## 2. Catálogo de Patrones GoF Implementados (14 Patrones)
Para construir una arquitectura robusta, transaccional y modular, se integró el siguiente ecosistema de **14 patrones de diseño clásicos (superando los 13 exigidos por la rúbrica)**, justificando el valor técnico aportado por cada uno:

### Patrones de Comportamiento (11)
1. **State:** *Gestión del ciclo de vida.* Se implementó para controlar las transiciones del paquete (`Confirmado` -> `En Tránsito` -> `Entregado`). **Justificación:** Elimina condicionales masivos (`switch-case`) en la clase central y bloquea por polimorfismo operaciones ilegales (ej. intentar entregar un paquete retenido).
2. **Strategy:** *Motor de tarifas.* Aísla las diferentes fórmulas de cobro (por peso, urgencia, volumen). **Justificación:** Cumple estrictamente con el Principio Open/Closed (OCP), permitiendo agregar nuevas lógicas comerciales dinámicamente sin modificar la entidad central.
3. **Template Method:** *Estandarización de flujos.* Define el esqueleto inmutable para envíos nacionales e internacionales. **Justificación:** Fuerza a que todos los envíos pasen por controles obligatorios (validación, cobro, asignación) en un orden jerárquico estricto, previniendo errores de omisión.
4. **Visitor:** *Análisis no invasivo.* Extrae las métricas complejas y costos operativos de la red de distribución. **Justificación:** Permite agregar nuevos algoritmos de reportería sin "ensuciar" las clases de infraestructura con lógica analítica ajena a su responsabilidad.
5. **Observer:** *Sistema reactivo.* Desacopla las notificaciones (SMS, Dashboard). **Justificación:** Permite que los módulos de infraestructura reaccionen automáticamente a los cambios de estado del dominio sin que el dominio se acople a librerías de red o UI.
6. **Memento:** *Seguridad de estado.* Captura instantáneas inmutables del envío. **Justificación:** Esencial en logística para posibilitar *Rollbacks* seguros y consistentes ante contingencias operativas, garantizando la recuperación de datos.
7. **Mediator:** *Orquestación central.* Maneja los eventos en la capa de Aplicación. **Justificación:** Evita el acoplamiento caótico ("código espagueti") entre múltiples subsistemas, concentrando el flujo transaccional en un solo director.
8. **Command:** *Operaciones reificadas.* Encapsula solicitudes de negocio como objetos independientes. **Justificación:** Facilita el encolamiento de operaciones, el registro de auditoría y la ejecución del método `undo()` junto con Memento.
9. **Chain of Responsibility:** *Tubería de control.* Aplica un filtro secuencial de validaciones previas (fail-fast). **Justificación:** Corta la ejecución tempranamente si el paquete excede el peso o tiene un destino inválido, optimizando el uso de recursos antes de iniciar procesos pesados.
10. **Iterator:** *Recorrido seguro.* Navega colecciones de objetos en memoria. **Justificación:** Expone un recorrido seguro sobre la bodega de paquetes (estructura LIFO manual) ocultando su representación interna de nodos enlazados.
11. **Interpreter:** *Evaluación de reglas.* Implementado mediante expresiones (`AndExpression`, `UrgenciaExpression`). **Justificación:** Permite evaluar dinámicamente combinaciones de condiciones logísticas para determinar estados o prioridades, sin hardcodear reglas complejas en la entidad.

### Patrones Estructurales (3)
12. **Proxy (Virtual):** *Optimización de memoria.* Implementado para aplicar *Lazy Load* en el historial de envíos. **Justificación:** Evita la saturación de memoria RAM al diferir la carga masiva de datos hasta que el operador realmente solicita ver el detalle operativo.
13. **Facade:** *Punto de entrada unificado.* Simplifica el acceso a la capa de aplicación. **Justificación:** Oculta la complejidad de la orquestación interna (validadores, repositorios y comandos) ofreciendo una interfaz limpia y amigable para el cliente.
14. **Composite:** *Jerarquía física.* Modela la red de centros de distribución. **Justificación:** Permite que el sistema (y el patrón Visitor) operen de forma totalmente uniforme tanto sobre un andén individual (hoja) como sobre una región entera (nodo compuesto).

---

## 3. Patrones de Arquitectura Empresarial (Persistencia)
Para garantizar el aislamiento total del dominio frente a la base de datos (SQL Server), se complementaron los patrones GoF con arquitecturas de persistencia de Martin Fowler:
* **Repository & Data Mapper:** Traducen las filas relacionales de SQL Server a objetos puros de Java.
* **Unit of Work:** Agrupa múltiples transacciones garantizando atomicidad; si una inserción falla, se revierte toda la operación (evitando bases de datos inconsistentes).

---

## 4. Patrones Evaluados y Descartados (Trade-offs)
Demostrar dominio arquitectónico implica saber qué **no** utilizar. Se evaluaron y descartaron conscientemente los siguientes patrones para evitar sobreingeniería:

1. **Decorator (Descartado):** Se evaluó para agregar servicios adicionales al envío (ej. seguro, embalaje especial). *Trade-off:* Se descartó porque añadir responsabilidades financieras a la entidad logística base mediante envoltorios violaba el Principio de Responsabilidad Única (SRP). Se optó por usar *Strategy* para esta resolución.
2. **Singleton (Descartado):** Se consideró para gestionar el acceso a la base de datos. *Trade-off:* Introduce un estado global oculto y acoplamiento rígido, lo que imposibilita la creación de *Mocks* efectivos durante las pruebas unitarias. Se prefirió instanciar y pasar las dependencias mediante Inyección.
3. **Adapter (Descartado):** Se analizó para la integración de notificaciones. *Trade-off:* LogiSmart controla todo su dominio interno y, en esta iteración, no se integra con APIs legadas de terceros. Su implementación habría sido una violación directa del principio YAGNI (*You Aren't Gonna Need It*).

---

## 5. Evidencia de Ejecución (Suite de Pruebas Integradas)
El sistema ha sido verificado mediante una suite automatizada (`SuitePruebasIntegracion.java`) que evalúa **42 escenarios (asserts estrictos)** cubriendo la cadena de validación, cálculos financieros, control de estados prohibidos y rollbacks transaccionales.

**Salida exitosa de consola:**
```text
=== Iniciando Suite Exhaustiva LogiSmart (40+ Tests) ===

[Chain] Error: Envío demasiado pesado.
[Chain] Error: Destino no válido.
[Chain] Error: Destino no válido.
[Envio] Guardando estado actual: CONFIRMADO
[Command] Iniciando viaje para envío: ST-08
[Command] Deshaciendo inicio de viaje: ST-08
[Command] Cancelando envío: ST-08
[Envio] Estado restaurado a: CONFIRMADO
-> Ejecutando Matriz de Transiciones de Estado...
Error: No se puede repartir sin validar.
Error: No entregable.
Error: Ya validado.
Error: No cancelable en ruta.
Error: Requiere reparto previo.

=======================================================
=== SUITE FINALIZADA CON ÉXITO: 42 ASSERTS PASADOS ===
=======================================================
