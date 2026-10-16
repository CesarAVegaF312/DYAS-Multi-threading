package edu.unisabana.dyas.multithreading.retos;

import java.util.HashMap;
import java.util.Map;

/**
 * Prueba guía de maestría — Estación 6: de HashMap corrupto a
 * ConcurrentHashMap correcto.
 */
public class ConcurrentCollectionChallenge {

    /** Versión rota: se te da así, a propósito. */
    private static final Map<String, Integer> unsafeMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        reproduceCorruption();
        // TODO: descomenta una vez implementado.
        // fixWithConcurrentHashMap();
    }

    /**
     * Varios hilos hacen "leer, incrementar, escribir" sobre la misma clave
     * de un HashMap sin sincronizar. Ejecuta esto varias veces y documenta
     * que el valor final para "hits" no siempre es el esperado.
     */
    private static void reproduceCorruption() throws InterruptedException {
        unsafeMap.put("hits", 0);
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                int current = unsafeMap.get("hits"); // leer
                unsafeMap.put("hits", current + 1);  // luego escribir: no es atómico
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("hits (esperado 2000): " + unsafeMap.get("hits"));
    }

    /**
     * TODO: resuelve con java.util.concurrent.ConcurrentHashMap usando una
     * operación ATÓMICA COMPUESTA (merge o compute), no el mismo patrón
     * "leer, luego escribir" sobre un ConcurrentHashMap — eso seguiría
     * siendo una condición de carrera aunque la clase sea "concurrente".
     */
    private static void fixWithConcurrentHashMap() throws InterruptedException {
        // TODO
    }
}
