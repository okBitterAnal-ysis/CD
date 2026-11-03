import java.io.*;
import java.util.*;

public class LoadBalancing {
    public static void main(String[] args) throws Exception {
        String[] workers = {"W1", "W2", "W3"};
        int[] load = new int[3];
        int[] tasks = new int[3];

        BufferedReader br = new BufferedReader(
            new FileReader("workload_5.csv"));

        String line;
        br.readLine();
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            int w = i % 3;
            int units = Integer.parseInt(a[1]);

            load[w] += units;
            tasks[w]++;

            System.out.println(a[0] + " assigned to " + workers[w]);
            i++;
        }
        br.close();

        System.out.println("\nWorker Summary:");
        for (int j = 0; j < 3; j++) {
            System.out.println(workers[j] + " Jobs: " +
                tasks[j] + " Load: " + load[j]);
        }
    }
}