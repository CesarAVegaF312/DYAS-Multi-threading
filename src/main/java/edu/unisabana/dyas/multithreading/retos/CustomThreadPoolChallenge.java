package edu.unisabana.dyas.multithreading.retos;

/**
 * Prueba guía de maestría — Estación 5: ThreadPoolExecutor a la medida.
 *
 * Escenario: Executors.newFixedThreadPool(n) usa una cola ilimitada por
 * defecto — en producción eso puede consumir toda la memoria disponible
 * bajo una ráfaga de tráfico. Hay que construir el pool a mano.
 */
public class CustomThreadPoolChallenge {

    public static void main(String[] args) {
        // TODO: descomenta una vez implementado.
        // buildAndStressPool();
    }

    /**
     * TODO: construye un java.util.concurrent.ThreadPoolExecutor explícito
     * (NO uses Executors.*) con:
     *  - core pool size y max pool size,
     *  - keep-alive time,
     *  - una ArrayBlockingQueue ACOTADA (no ilimitada),
     *  - un RejectedExecutionHandler.
     * Envía más tareas de las que caben en (pool + cola) para forzar al
     * menos un rechazo. Corre el escenario dos veces — una con
     * CallerRunsPolicy y otra con AbortPolicy — y documenta la diferencia
     * observada.
     */
    private static void buildAndStressPool() {
        // TODO
    }
}
