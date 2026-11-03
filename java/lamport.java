import java.io.*;
import java.util.*;

public class lamport {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> clock = new HashMap<>();
        Map<String, Integer> messages = new HashMap<>();

        BufferedReader br = new BufferedReader(
            new FileReader("events_7.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            String id = a[0], p = a[1];
            String type = a[2], other = a[3];

            clock.putIfAbsent(p, 0);

            if (type.equals("INTERNAL")) {
                clock.put(p, clock.get(p) + 1);
            } else if (type.equals("SEND")) {
                clock.put(p, clock.get(p) + 1);
                messages.put(p + other, clock.get(p));
            } else if (type.equals("RECEIVE")) {
                int msg = messages.get(other + p);
                clock.put(p, Math.max(clock.get(p), msg) + 1);
            }

            System.out.println(id + " " + p +
                " Clock = " + clock.get(p));
        }
        br.close();
    }
}