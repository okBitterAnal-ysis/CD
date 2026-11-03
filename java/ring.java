import java.io.*;
import java.util.*;

public class ring {
    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> next = new HashMap<>();
        Map<Integer, Boolean> active = new HashMap<>();

        BufferedReader br = new BufferedReader(
            new FileReader("ring_nodes_15.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            int id = Integer.parseInt(a[0]);

            next.put(id, Integer.parseInt(a[1]));
            active.put(id, a[2].equalsIgnoreCase("TRUE"));
        }
        br.close();

        int start = 2, current = start;
        ArrayList<Integer> token = new ArrayList<>();

        System.out.println("Node 2 starts ELECTION");

        while (true) {
            token.add(current);
            int n = next.get(current);

            while (!active.get(n)) {
                System.out.println("Node " + n + " is DOWN, skipped");
                n = next.get(n);
            }

            if (n == start)
                break;

            System.out.println("Token " + token +
                " passed to node " + n);
            current = n;
        }

        int leader = Collections.max(token);

        System.out.println("Token returned with IDs: " + token);
        System.out.println("Node " + leader +
            " elected COORDINATOR");
        System.out.println("COORDINATOR message: " +
            token.toString().replace("[", "").replace("]", "")
                .replace(", ", " -> "));
    }
}