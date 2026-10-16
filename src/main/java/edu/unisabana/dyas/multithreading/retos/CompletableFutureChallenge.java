package edu.unisabana.dyas.multithreading.retos;

import java.util.concurrent.CompletableFuture;

/**
 * Prueba guía de maestría — Estación 4: composición asíncrona con
 * CompletableFuture.
 *
 * Escenario: para autenticar a un usuario hay que (1) buscarlo, luego (2)
 * buscar sus pedidos, y en paralelo (3) buscar sus preferencias — y combinar
 * (2) y (3) al final.
 */
public class CompletableFutureChallenge {

    public static void main(String[] args) {
        // TODO: descomenta una vez implementado.
        // System.out.println(authenticateAndLoad("user-1").join());
    }

    /**
     * TODO: encadena findUser (paso 1) -> findOrders (paso 2, depende del
     * usuario) combinado con findPreferences (paso 3, depende del usuario
     * pero es independiente de findOrders, así que puede correr en
     * paralelo). Usa thenCompose para 1->2, thenCombine para combinar 2 y 3,
     * y exceptionally o handle para manejar errores de cualquiera de los
     * tres pasos.
     */
    private static CompletableFuture<String> authenticateAndLoad(String userId) {
        // TODO
        return CompletableFuture.completedFuture(null);
    }

    // --- Servicios simulados, ya dados ---

    private static CompletableFuture<String> findUser(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "user:" + userId;
        });
    }

    private static CompletableFuture<String> findOrders(String user) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "orders-de-" + user;
        });
    }

    private static CompletableFuture<String> findPreferences(String user) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(150);
            return "preferencias-de-" + user;
        });
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
