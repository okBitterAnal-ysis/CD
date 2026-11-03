import java.io.*;
import java.util.*;

public class bully {
    static Map<Integer, Boolean> alive = new TreeMap<>();

    static void election(int n) {
        System.out.println("\nNode " + n + " starts ELECTION");

        ArrayList<Integer> active = new ArrayList<>();

        for (int x : alive.keySet()) {
            if (x > n) {
                System.out.println(n + " sends ELECTION to " + x);

                if (alive.get(x)) {
                    System.out.println("Node " + x + " replies OK");
                    active.add(x);
                } else {
                    System.out.println("Node " + x + " is DOWN");
                }
            }
        }

        if (!active.isEmpty()) {
            election(active.get(0));
        } else {
            System.out.println("\nNode " + n + " wins the election");

            for (int x : alive.keySet()) {
                if (alive.get(x) && x != n)
                    System.out.println("COORDINATOR message sent to " + x);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new FileReader("bully_cluster_2.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            alive.put(Integer.parseInt(a[0]),
                a[1].equalsIgnoreCase("TRUE"));
        }
        br.close();

        election(2);
    }
}