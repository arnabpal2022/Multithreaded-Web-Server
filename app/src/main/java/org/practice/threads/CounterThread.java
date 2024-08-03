package org.practice.threads;

public class CounterThread extends Thread{
    private static int counter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (CounterThread.class) {
                counter++;
            }
        }
    }

    public static int getCounter() {
        return counter;
    }
}
