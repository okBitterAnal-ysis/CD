import java.io.*;
import java.util.*;

public class RPC{
    static int processData(int x){
        return x * 2;
    }

    static String validateDevice(int x){
        return (x >= 50 && x <=500) ? "Valid" : "Invalid";
    }

    static void server(String method, int value){
        System.out.println("Server Execution" + method);

        if(method.equals("processData")){
            System.out.println(("Return" + processData(value)));
        }else if(method.equals("validateDevice")){
            System.out.println("Return" + processData(value));
        }
    }

    public static void main(String[] Args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("rpc_calls_1.csv"));

        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");
            System.out.println("Dispatch" + a[0] + " " + a[2]);

            String request = a[2] + ":" + a[3];
            String[] data = request.split(":");

            server(data[0], Integer.parseInt(data[1]));
        }
        br.close();
    }
}