import java.io.*;
import java.util.*;

public class Berkeley {
    public static void main(String[] args) throws Exception {
        ArrayList<String> nodes = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();

        BufferedReader br = new BufferedReader(
            new FileReader("node_clocks_3.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");

            if (a[2].equalsIgnoreCase("FALSE")) {
                nodes.add(a[0]);
                times.add(Double.parseDouble(a[1]));
            } else {
                System.out.println(a[0] + " is faulty and ignored");
            }
        }
        br.close();

        double sum = 0;
        for (double t : times)
            sum += t;

        double average = sum / times.size();
        System.out.println("Average Clock Time: " + average);

        for (int i = 0; i < times.size(); i++)
            System.out.println(nodes.get(i) +
                " Adjustment: " + (average - times.get(i)));
    }
}