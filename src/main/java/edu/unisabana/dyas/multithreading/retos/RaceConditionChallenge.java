package edu.unisabana.dyas.multithreading.retos;

/**
 * Prueba guía de maestría — Estación 1: condición de carrera, tres
 * soluciones comparadas.
 */
public class RaceConditionChallenge {

    /** Versión SIN protección: se te da así, a propósito, para reproducir el bug. */
    private static int unsafeCounter = 0;

    private static void unsafeIncrement() {
        unsafeCounter++; // no es atómico: leer + incrementar + escribir en 3 pasos
    }

    public static void main(String[] args) throws InterruptedException {
        reproduceRaceCondition();
        // TODO: descomenta cada solución una vez implementada.
        // fixWithSynchronized();
        // fixWithAtomicInteger();
        // fixWithReentrantLock();
    }

    /** Ejecuta esto 5+ veces y documenta que el resultado no siempre es 2000. */
    private static void reproduceRaceCondition() throws InterruptedException {
        unsafeCounter = 0;
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeIncrement();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("unsafeCounter (esperado 2000): " + unsafeCounter);
    }

    /**
     * TODO: repite el mismo escenario (2 hilos x 1000 incrementos) pero
     * protegiendo el incremento con "synchronized". Debe imprimir siempre
     * 2000.
     */
    private static void fixWithSynchronized() throws InterruptedException {
        // TODO
    }

    /**
     * TODO: misma prueba, usando java.util.concurrent.atomic.AtomicInteger
     * en vez de synchronized.
     */
    private static void fixWithAtomicInteger() throws InterruptedException {
        // TODO
    }

    /**
     * TODO: misma prueba, usando java.util.concurrent.locks.ReentrantLock
     * (lock()/unlock() explícitos, con unlock() en un finally).
     */
    private static void fixWithReentrantLock() throws InterruptedException {
        // TODO
    }
}
