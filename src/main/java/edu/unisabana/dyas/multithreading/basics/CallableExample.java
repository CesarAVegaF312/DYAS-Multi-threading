package edu.unisabana.dyas.multithreading.basics;

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
