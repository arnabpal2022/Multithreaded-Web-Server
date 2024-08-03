package org.practice.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        normalloop();
        threadloop();
    }

    static void threadloop(){
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        long startTime = System.nanoTime();
        AtomicInteger f= new AtomicInteger(-1);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                f.set(finalI);
            });
        }

        threadPool.shutdown();
        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken by thread loop: " + timeTaken + " nanoseconds");
    }

    static void normalloop(){
        long startTime = System.nanoTime();
        int f=-1;
        for (int i = 0; i < 10; i++) {
            f=i;
        }
        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken by normal loop: " + timeTaken + " nanoseconds");
    }
}
