import java.io.*;
import java.util.*;

public class VectorClock {
    public static void main(String[] args) throws Exception {
        String[] p = {"P1", "P2", "P3"};
        int[][] clock = new int[3][3];
        Map<String, int[]> messages = new HashMap<>();

        BufferedReader br = new BufferedReader(
            new FileReader("vector_events_6.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            String id = a[0], process = a[1];
            String action = a[2], target = a[3];

            int i = Arrays.asList(p).indexOf(process);

            if (action.equals("SEND")) {
                clock[i][i]++;
                messages.put(process + target, clock[i].clone());
            } else if (action.equals("RECEIVE")) {
                int[] msg = messages.get(target + process);

                for (int j = 0; j < 3; j++)
                    clock[i][j] = Math.max(clock[i][j], msg[j]);

                clock[i][i]++;
            } else {
                clock[i][i]++;
            }

            System.out.println(id + " " +
                Arrays.toString(clock[i]));
        }
        br.close();
    }
}