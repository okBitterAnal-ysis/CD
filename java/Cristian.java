import java.io.*;

public class Cristian {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new FileReader("cristian_clocks_8.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");

            double t0 = Double.parseDouble(a[1]);
            double ts = Double.parseDouble(a[2]);
            double t1 = Double.parseDouble(a[3]);

            double rtt = t1 - t0;
            double estimated = ts + rtt / 2;
            double offset = estimated - t1;
            double adjusted = t1 + offset;

            System.out.println("Client: " + a[0]);
            System.out.println("RTT: " + rtt);
            System.out.println("Estimated Server Time: " + estimated);
            System.out.println("Clock Offset: " + offset);
            System.out.println("Adjusted Time: " + adjusted);
        }
        br.close();
    }
}