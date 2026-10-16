package edu.unisabana.dyas.multithreading.retos;

/**
 * Prueba guía de maestría — Estación 2: reproducir y resolver un deadlock.
 *
 * Escenario: dos cuentas comparten dos recursos (locks); un hilo transfiere
 * de A→B y otro de B→A al mismo tiempo.
 */
public class DeadlockChallenge {

    private static final Object accountA = new Object();
    private static final Object accountB = new Object();

    public static void main(String[] args) {
        // TODO: descomenta para reproducir el deadlock (el programa se
        // quedará colgado). Documenta la evidencia (por ejemplo con
        // `jstack <pid>` mientras está colgado) antes de detener el proceso.
        // reproduceDeadlock();

        // TODO: implementa y ejecuta la versión con orden consistente.
        // fixWithConsistentOrdering();
    }

    /**
     * TODO: crea dos hilos.
     *  - Hilo 1 (transferencia A→B): sincroniza sobre accountA, duerme unos
     *    ms, luego intenta sincronizar sobre accountB.
     *  - Hilo 2 (transferencia B→A): sincroniza sobre accountB, duerme unos
     *    ms, luego intenta sincronizar sobre accountA.
     * El pequeño sleep entre ambas adquisiciones es a propósito, para forzar
     * el entrelazado que produce el deadlock.
     */
    private static void reproduceDeadlock() {
        // TODO
    }

    /**
     * TODO: misma transferencia (A→B y B→A), pero ambos hilos adquieren los
     * locks siempre en el mismo orden fijo (por ejemplo, ordenando por el
     * identityHashCode o un id fijo de cuenta), sin importar la dirección de
     * la transferencia.
     */
    private static void fixWithConsistentOrdering() {
        // TODO
    }
}
