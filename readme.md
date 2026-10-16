### Taller sobre Multi-threading en Java

#### Objetivo
El objetivo de este taller es aprender los conceptos fundamentales de multi-threading en Java, implementando ejemplos prácticos para entender cómo manejar múltiples hilos en una aplicación Java.

#### Pre-requisitos
- Conocimientos básicos de Java.
- Familiaridad con conceptos de concurrencia y threading.

### Paso 1: Configuración del Proyecto

#### Configuración del `pom.xml`
El `pom.xml` del repositorio ya viene configurado (Java 17, `exec-maven-plugin` para correr cada ejemplo, JUnit 5 para pruebas) — no hace falta crear uno nuevo. Los cinco ejemplos de abajo ya existen como clases reales en `src/main/java/edu/unisabana/dyas/multithreading/basics/`, listas para ejecutar.

### Paso 2: Ejemplos de Multi-threading en Java

#### 1. Creación de Hilos con `Thread`

**Código:**

```java
public class ThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread: " + i);
                try {
                    Thread.sleep(1000); // Pausa el hilo por 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // Inicia el hilo
    }
}
```

**Explicación:**
- Se crea un nuevo hilo utilizando la clase `Thread`.
- La lógica del hilo se define dentro de un bloque `Runnable` proporcionado al constructor de `Thread`.
- El hilo imprime un contador de 0 a 4, pausando por 1 segundo entre cada impresión.
- `thread.start()` inicia la ejecución del hilo.

#### 2. Creación de Hilos con `Runnable`

**Código:**

```java
public class RunnableExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Runnable: " + i);
                try {
                    Thread.sleep(1000); // Pausa el hilo por 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(task); // Crea un hilo con el Runnable
        thread.start(); // Inicia el hilo
    }
}
```

**Explicación:**
- Se define una tarea `Runnable` que imprime un contador de 0 a 4, pausando por 1 segundo entre cada impresión.
- Se crea un nuevo hilo utilizando el `Runnable` como argumento para el constructor de `Thread`.
- `thread.start()` inicia la ejecución del hilo.

#### 3. Sincronización de Hilos

**Código:**

```java
public class SynchronizedExample {
    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                increment(); // Incrementa el contador de manera sincronizada
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // Espera a que thread1 termine
            thread2.join(); // Espera a que thread2 termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter); // Imprime el valor final del contador
    }
}
```

**Explicación:**
- Se define un método `increment()` que incrementa un contador de manera sincronizada.
- Dos hilos ejecutan una tarea que llama al método `increment()` 1000 veces.
- `thread1.join()` y `thread2.join()` aseguran que el programa principal espere a que ambos hilos terminen antes de continuar.
- El valor final del contador se imprime al final.

#### 4. Uso de `ExecutorService`

**Código:**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Crea un pool de 2 hilos

        Runnable task1 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Task 1: " + i);
                try {
                    Thread.sleep(1000); // Pausa el hilo por 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Task 2: " + i);
                try {
                    Thread.sleep(1000); // Pausa el hilo por 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.submit(task1); // Envía la tarea 1 al pool de hilos
        executor.submit(task2); // Envía la tarea 2 al pool de hilos

        executor.shutdown(); // Finaliza el pool de hilos una vez completadas las tareas
    }
}
```

**Explicación:**
- Se crea un `ExecutorService` con un pool de 2 hilos.
- Se definen dos tareas `Runnable` que imprimen un contador de 0 a 4, pausando por 1 segundo entre cada impresión.
- Las tareas se envían al pool de hilos utilizando `executor.submit()`.
- `executor.shutdown()` finaliza el pool de hilos una vez que todas las tareas han sido completadas.

#### 5. Uso de `Callable` y `Future`

**Código:**

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Crea un pool de 2 hilos

        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += i;
                try {
                    Thread.sleep(1000); // Pausa el hilo por 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return sum; // Retorna la suma de los números
        };

        Future<Integer> future = executor.submit(task); // Envía la tarea y recibe un Future

        try {
            Integer result = future.get(); // Obtiene el resultado de la tarea
            System.out.println("Sum: " + result); // Imprime el resultado
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown(); // Finaliza el pool de hilos
    }
}
```

**Explicación:**
- Se crea un `ExecutorService` con un pool de 2 hilos.
- Se define una tarea `Callable` que calcula la suma de los números del 0 al 4, pausando por 1 segundo entre cada iteración.
- La tarea se envía al pool de hilos utilizando `executor.submit()`, y se recibe un `Future` que representará el resultado de la tarea.
- `future.get()` se utiliza para obtener el resultado de la tarea una vez que se ha completado.
- `executor.shutdown()` finaliza el pool de hilos una vez que todas las tareas han sido completadas.

### Paso 3: Crear un Ejemplo Propio

**Instrucciones para los Estudiantes:**

1. **Definir un Caso de Uso:**
   - Piensa en un escenario donde el multi-threading pueda ser útil. Por ejemplo, procesamiento paralelo de datos, tareas de I/O concurrentes, etc.
2. **Seleccionar la Estrategia de Multi-threading:**
   - Elige la estrategia de multi-threading adecuada para tu caso de uso (`Thread`, `Runnable`, `ExecutorService`, etc.).
3. **Implementar el Ejemplo:**
   - Implementa tu ejemplo en Java utilizando la estrategia seleccionada.
   - Asegúrate de comentar tu código para explicar qué hace cada parte y cómo se gestionan los hilos.
4. **Probar y Depurar:**
   - Ejecuta tu código y verifica que funcione como esperas. Asegúrate de manejar cualquier posible error.
5. **Compartir y Discutir:**
   - Comparte tu código con los demás estudiantes del taller. Discutan sobre las diferentes implementaciones y enfoques utilizados.

**Ejemplo de Código para los Estudiantes:**

Aquí tienes un ejemplo sencillo para inspirarte. Supongamos que queremos procesar una lista de tareas en paralelo y sumar los resultados.

```java
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomThreadExample {
    public static void main(String[] args) {
        List<Callable<Integer>> tasks = Arrays.asList(
                () -> { Thread.sleep(1000); return 10; },
                () -> { Thread.sleep(1000); return 20; },
                () -> { Thread.sleep(1000); return 30; }
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            List<Future<Integer>> results = executor.invokeAll(tasks);
            int sum = 0;
            for (Future<Integer> result : results) {
                sum += result.get();
            }
            System.out.println("Sum: " + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```

### Paso 4: Resumen y Conclusión

#### Resumen del Taller
- Aprendimos a utilizar diversas estrategias de multi-threading en Java: `Thread`, `Runnable`, `synchronized`, `ExecutorService`, `Callable`, y `Future`.
- Implementamos ejemplos prácticos para entender cómo manejar y sincronizar múltiples hilos en una aplicación Java.
- Los estudiantes crearon sus propios ejemplos para aplicar lo aprendido.

#### Conclusión
El multi-threading es una poderosa herramienta para mejorar el rendimiento y la capacidad de respuesta de las aplicaciones. Las estrategias y técnicas que hemos explorado en este taller son fundamentales para construir aplicaciones concurrentes y escalables. Continuar practicando con estos conceptos y explorando otros aspectos de la concurrencia te permitirá construir aplicaciones más robustas y eficientes.

### Ejecución del Taller

```bash
mvn compile

mvn exec:java -Dexec.mainClass=edu.unisabana.dyas.multithreading.basics.ThreadExample
mvn exec:java -Dexec.mainClass=edu.unisabana.dyas.multithreading.basics.RunnableExample
mvn exec:java -Dexec.mainClass=edu.unisabana.dyas.multithreading.basics.SynchronizedExample
mvn exec:java -Dexec.mainClass=edu.unisabana.dyas.multithreading.basics.ExecutorServiceExample
mvn exec:java -Dexec.mainClass=edu.unisabana.dyas.multithreading.basics.CallableExample
```

1. Ejecuta cada uno de los cinco ejemplos anteriores y observa su salida (en `SynchronizedExample`, ejecútalo varias veces — el resultado siempre debe ser `2000`).
2. Crea `CustomThreadExample` (o el nombre que prefieras) en el mismo paquete, aplicando la estrategia que mejor se ajuste a tu caso de uso.
3. Comparte tu ejemplo y discute con los demás estudiantes las diferentes implementaciones y enfoques utilizados.
4. Experimenta con otras técnicas de concurrencia y casos de uso para profundizar en tu comprensión del multi-threading.

> Para la evaluación de nivel de maestría sobre estos mismos componentes, ver [`docs/prueba-guia-maestria.md`](docs/prueba-guia-maestria.md).

Este taller proporciona una base sólida para comenzar a trabajar con multi-threading en Java y fomenta la creación de ejemplos propios para reforzar el aprendizaje.