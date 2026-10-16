# Prueba guía de maestría — Concurrencia en Java

## Objetivo

El taller base cubre la sintaxis (`Thread`, `Runnable`, `synchronized`, `ExecutorService`, `Callable`/`Future`). Esta prueba evalúa si sabes **diagnosticar** problemas de concurrencia reales (condiciones de carrera, deadlocks) y **elegir** la herramienta correcta entre varias válidas — la habilidad que distingue a quien "usa hilos" de quien diseña sistemas concurrentes confiables.

## Prerrequisitos

- Haber ejecutado los 5 ejemplos base, ahora materializados como código real en [`src/main/java/edu/unisabana/dyas/multithreading/basics/`](../src/main/java/edu/unisabana/dyas/multithreading/basics/).

## Formato

6 estaciones. En cada una se entrega código funcional y una justificación escrita por pregunta. Tiempo sugerido: 3-4 horas.

---

### Estación 1 — Diagnóstico de condición de carrera: tres soluciones, comparadas

**Escenario:** se te entrega una versión de `SynchronizedExample` **sin** protección (`unsafeIncrement`), ya lista para reproducir el bug.

**Requisitos:**
- Ejecutar la versión rota al menos 5 veces y documentar que el resultado varía (no siempre da 2000) — esa es la evidencia de la condición de carrera, no basta con decir "sé que está mal".
- Arreglarla de **tres** formas distintas: `synchronized`, `AtomicInteger`, `ReentrantLock`.
- **Justificación:** ¿en qué escenario elegirías cada una de las tres? (pista: no es lo mismo proteger un contador simple que proteger una operación compuesta de varios pasos).

**Archivo de partida:** [`src/main/java/edu/unisabana/dyas/multithreading/retos/RaceConditionChallenge.java`](../src/main/java/edu/unisabana/dyas/multithreading/retos/RaceConditionChallenge.java)

---

### Estación 2 — Reproducir y resolver un deadlock

**Escenario:** dos hilos necesitan dos recursos compartidos (cuenta A y cuenta B) para hacer una transferencia; un hilo transfiere de A→B y otro de B→A al mismo tiempo.

**Requisitos:**
- Implementar la versión que adquiere los locks en orden inconsistente y demostrar que se cuelga (evidencia: por ejemplo una captura de `jstack <pid>` mientras está colgado).
- Arreglarla con **orden consistente de adquisición de locks**.
- **Justificación:** ¿por qué el orden de adquisición es la causa raíz, y no la cantidad de locks en sí?

**Archivo de partida:** [`src/main/java/edu/unisabana/dyas/multithreading/retos/DeadlockChallenge.java`](../src/main/java/edu/unisabana/dyas/multithreading/retos/DeadlockChallenge.java)

---

### Estación 3 — Coordinación: `CyclicBarrier` y `Semaphore`

**Escenario A:** 5 workers deben completar una "fase de carga de datos" antes de que cualquiera empiece la "fase de procesamiento" — ninguno avanza hasta que TODOS terminen la fase 1.

**Escenario B:** un pool de 20 hilos necesita acceder a un servicio externo que solo soporta 4 conexiones simultáneas.

**Requisitos:**
- Resolver el escenario A con `CyclicBarrier`.
- Resolver el escenario B con `Semaphore`.
- **Justificación:** ¿por qué `CyclicBarrier` no serviría para el escenario B, y por qué `Semaphore` no serviría para el escenario A?

**Archivo de partida:** [`src/main/java/edu/unisabana/dyas/multithreading/retos/CoordinationChallenge.java`](../src/main/java/edu/unisabana/dyas/multithreading/retos/CoordinationChallenge.java)

---

### Estación 4 — Composición asíncrona con `CompletableFuture`

**Escenario:** para autenticar a un usuario hay que (1) buscarlo, luego (2) buscar sus pedidos, y en paralelo (3) buscar sus preferencias — y combinar (2) y (3) al final.

**Requisitos:**
- Implementar la cadena con `thenCompose` (paso dependiente 1→2), `thenCombine` (combinar 2 y 3 en paralelo) y manejo de errores con `exceptionally` o `handle`.
- **Justificación:** compara explícitamente con el enfoque bloqueante ya existente en `CallableExample` (vía `Future.get()`). ¿Qué gana esta versión, y en qué caso el enfoque bloqueante seguiría siendo aceptable?

**Archivo de partida:** [`src/main/java/edu/unisabana/dyas/multithreading/retos/CompletableFutureChallenge.java`](../src/main/java/edu/unisabana/dyas/multithreading/retos/CompletableFutureChallenge.java) (los tres servicios simulados ya están dados)

---

### Estación 5 — `ThreadPoolExecutor` a la medida

**Escenario:** `Executors.newFixedThreadPool(n)` usa una cola ilimitada por defecto — en producción eso puede consumir toda la memoria disponible bajo una ráfaga de tráfico.

**Requisitos:**
- Construir un `ThreadPoolExecutor` explícito: core pool size, max pool size, keep-alive time, una `ArrayBlockingQueue` **acotada**, y un `RejectedExecutionHandler`.
- Forzar al menos un rechazo (enviar más tareas de las que caben en pool + cola) y comparar `CallerRunsPolicy` vs `AbortPolicy`.
- **Justificación:** ¿qué valores elegiste y por qué, para un escenario de tráfico "en ráfagas" (picos cortos, luego calma) frente a uno de "carga sostenida"?

**Archivo de partida:** [`src/main/java/edu/unisabana/dyas/multithreading/retos/CustomThreadPoolChallenge.java`](../src/main/java/edu/unisabana/dyas/multithreading/retos/CustomThreadPoolChallenge.java)

---

### Estación 6 — Colecciones concurrentes: de `HashMap` corrupto a `ConcurrentHashMap` correcto

**Escenario:** un `HashMap<String, Integer>` compartido, accedido por múltiples hilos vía "leer, incrementar, escribir" (ya dado, roto a propósito).

**Requisitos:**
- Reproducir la corrupción (valores perdidos frente al total esperado).
- Resolver usando `ConcurrentHashMap` **con una operación atómica compuesta** (`merge` o `compute`) — no basta con cambiar la clase y dejar el mismo patrón "leer, luego escribir".
- **Justificación:** ¿por qué cambiar solo la clase (de `HashMap` a `ConcurrentHashMap`) sin cambiar el patrón de acceso NO resuelve el problema por completo?

**Archivo de partida:** [`src/main/java/edu/unisabana/dyas/multithreading/retos/ConcurrentCollectionChallenge.java`](../src/main/java/edu/unisabana/dyas/multithreading/retos/ConcurrentCollectionChallenge.java)

---

## Rúbrica

| Criterio | Peso |
|---|---|
| Correctitud funcional de las 6 estaciones | 35% |
| Evidencia real del problema antes de la solución (reproducir el bug, no solo describirlo) | 20% |
| Elección justificada de la herramienta de concurrencia | 25% |
| Claridad de la justificación escrita | 20% |
