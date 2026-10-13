# TechFix Logistics - Sistema de Gestión de Reparación de Dispositivos

Este proyecto es un repositorio de investigación académica sobre el principio **REP (Reuse/Release Equivalence Principle)** aplicado a un sistema de gestión de taller de reparación de dispositivos electrónicos.

## Descripción del Proyecto

TechFix Logistics es un sistema para gestionar el diagnóstico y cálculo de costos de reparación de dispositivos electrónicos en Guatemala. El proyecto demuestra la aplicación del principio REP mediante dos implementaciones:

1. **Versión "antes"**: Implementación monolítica que viola el principio REP
2. **Versión "después"**: Implementación modular que aplica correctamente el principio REP

## Principio REP (Reuse/Release Equivalence Principle)

**Definición**: Las clases que se reutilizan juntas deben liberarse juntas. Esto significa que los componentes que pertenecen al mismo paquete o módulo deben tener el mismo número de versión y ser liberados como una unidad cohesiva.

**Problema que resuelve**: Evita que cambios en módulos periféricos afecten el núcleo del negocio, permitiendo ciclos de release independientes y control granular de versiones.

## Estructura del Repositorio

```
rep-techfix-java/
├── antes/                    # Versión monolítica (violación de REP)
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/techfix/monolito/
│       └── test/java/com/techfix/monolito/
├── despues/                  # Versión modular (aplicación de REP)
│   ├── pom.xml              # Parent POM
│   ├── repair-core/         # Módulo de lógica central
│   └── price-formatter/     # Módulo de formateo de precios
├── docs/                    # Diapositivas de presentación
│   └── Principio REP.pdf
└── README.md
```

## Requisitos Técnicos

- Java 17 o superior
- Apache Maven 3.6 o superior (opcional)
- JUnit 5 para pruebas unitarias

## Instrucciones de Compilación y Ejecución

### Versión "antes" (Monolítica)

**Sin Maven:**
```bash
cd rep-techfix-java/antes/src/main/java
javac com/techfix/monolito/*.java
java com.techfix.monolito.DemoMain
```

**Con Maven:**
```bash
cd antes
mvn clean compile
mvn clean test
```

**Descripción de la violación**:
En esta versión, la lógica de negocio (`DeviceRepairService`) y el formateo de precios en quetzales (`PriceFormatter`) están en un único módulo sin versionado separado. Esto causa los siguientes problemas:

1. Un cambio en el formato de precios (ej: de GTQ a USD) obliga a recompilar todo el módulo, incluyendo la lógica de cálculo de costos que no depende del formato.
2. No se puede reutilizar `DeviceRepairService` sin arrastrar la dependencia de `PriceFormatter`.
3. Falta de control granular de versiones: todo debe cambiar al mismo tiempo.

### Versión "después" (Modular)

**Sin Maven:**
```bash
cd rep-techfix-java/despues/repair-core/src/main/java
javac com/techfix/core/*.java
java com.techfix.core.DemoMain
```

**Con Maven:**
```bash
cd despues
mvn clean compile
mvn clean test
```

**Descripción de la solución**:
Esta versión divide el sistema en dos módulos Maven independientes, cada uno con su propio versionado:

1. **repair-core (v1.0.0-SNAPSHOT)**: Lógica central de diagnóstico y cálculo de costos. No depende de otros módulos. Puede compilar y ejecutarse de forma aislada.
2. **price-formatter (v1.0.0-SNAPSHOT)**: Formateo de precios en diferentes monedas. Depende de `repair-core` pero puede evolucionar independientemente.

**Beneficios observados**:

1. Un cambio en `price-formatter` (ej: v1.1.0) no afecta `repair-core` v1.0.0.
2. `repair-core` puede reutilizarse en otros proyectos sin dependencias de formateo de precios.
3. Cada módulo tiene su propio ciclo de release y versionado semántico.
4. Cambios en el formato de precios no forzan recompilación del núcleo del negocio.

## Comparación de Versiones

| Aspecto | Versión "antes" | Versión "después" |
|---------|----------------|------------------|
| Estructura | Monolito único | 2 módulos independientes |
| Versionado | Un solo número de versión | Cada módulo con su versión |
| Acoplamiento | Alto | Bajo (unidireccional) |
| Reutilización | Difícil (dependencias innecesarias) | Fácil (módulos aislados) |
| Impacto de cambios | Afecta todo el sistema | Solo afecta el módulo modificado |
| Ciclo de release | Global | Independiente por módulo |

## Módulos del Sistema

### repair-core
Responsabilidad: Lógica central de diagnóstico y cálculo de costos de reparación.

Clases principales:
- `DeviceRepairService`: Servicio de diagnóstico y gestión de reparaciones
- `RepairOrder`: Modelo de orden de reparación
- `Device`: Modelo de dispositivo

### price-formatter
Responsabilidad: Formateo de precios en diferentes monedas y formatos.

Clases principales:
- `PriceFormatter`: Formateador de precios

Dependencias: `repair-core` (para acceder a modelos de dominio)

## Escenario de Demostración

### Cambio en Formato de Precios (Versión "antes")

1. El negocio requiere cambiar de USD a CLP (pesos chilenos).
2. `PriceFormatter` debe modificarse.
3. `DeviceRepairService` debe recompilarse aunque solo calcula costos numéricos.
4. Todo el módulo debe redesplegarse, introduciendo riesgo en la lógica de negocio crítica.

### Cambio en Formato de Precios (Versión "después")

1. El negocio requiere cambiar de USD a CLP.
2. `price-formatter` se actualiza a v1.1.0.
3. `repair-core` v1.0.0 continúa funcionando sin cambios.
4. Solo `price-formatter` necesita redespliegue.


## Autor

Henry Morales
