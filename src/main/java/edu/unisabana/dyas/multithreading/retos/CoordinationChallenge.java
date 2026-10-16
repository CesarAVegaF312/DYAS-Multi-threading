package edu.unisabana.dyas.multithreading.retos;

/**
 * Prueba guía de maestría — Estación 3: coordinación entre hilos.
 */
public class CoordinationChallenge {

    public static void main(String[] args) throws InterruptedException {
        // TODO: descomenta cada escenario una vez implementado.
        // scenarioA_cyclicBarrier();
        // scenarioB_semaphore();
    }

    /**
     * Escenario A: 5 workers deben completar la "fase de carga de datos"
     * antes de que cualquiera pueda empezar la "fase de procesamiento" —
     * ninguno avanza hasta que TODOS terminen la fase 1.
     *
     * TODO: implementa esto con java.util.concurrent.CyclicBarrier(5, ...).
     */
    private static void scenarioA_cyclicBarrier() throws InterruptedException {
        // TODO
    }

    /**
     * Escenario B: 20 hilos compiten por acceder a un recurso externo que
     * solo admite 4 accesos simultáneos.
     *
     * TODO: implementa esto con java.util.concurrent.Semaphore(4).
     */
    private static void scenarioB_semaphore() throws InterruptedException {
        // TODO
    }
}
