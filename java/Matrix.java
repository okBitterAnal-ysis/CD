import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Matrix {
    static int[][] m = new int[4][4];

    static int[] multiply(int[] row) {
        int[] result = new int[4];

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++)
                result[j] += row[k] * m[k][j];
        }

        System.out.println("Row processed: " +
            Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new FileReader("matrix_22.csv"));

        String line;
        br.readLine();
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");

            for (int j = 0; j < 4; j++)
                m[i][j] = Integer.parseInt(a[j + 1]);

            i++;
        }
        br.close();

        System.out.println("Scatter: Assigning rows to workers");

        ExecutorService pool = Executors.newFixedThreadPool(4);
        ArrayList<Future<int[]>> results = new ArrayList<>();

        for (int r = 0; r < 4; r++) {
            final int[] row = m[r].clone();
            results.add(pool.submit(() -> multiply(row)));
        }

        pool.shutdown();

        System.out.println("\nGather: Final Result");
        for (Future<int[]> f : results)
            System.out.println(Arrays.toString(f.get()));
    }
}