import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MultiThreading {
    static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new FileReader("tasks_4.csv"));

        String line;
        br.readLine();

        ExecutorService pool = Executors.newFixedThreadPool(3);

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            String task = a[0];
            int time = Integer.parseInt(a[2]);

            pool.submit(() -> {
                System.out.println(task + " is processing");

                try {
                    Thread.sleep(time);
                } catch (Exception e) {}

                synchronized (lock) {
                    System.out.println(task + " completed");
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        br.close();
    }
}