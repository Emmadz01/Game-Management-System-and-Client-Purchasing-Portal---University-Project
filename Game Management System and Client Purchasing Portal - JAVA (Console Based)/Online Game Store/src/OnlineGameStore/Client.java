package OnlineGameStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    Socket socket;
    Server server;

    public Client(String data) {
        try {
            socket = new Socket("localhost", 1234);

            if (data.equals("")) {
                    
                while (true) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    System.out.print("\n\t\t" + br.readLine());
                }
            } else {

                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println(data);
            }
        } catch (IOException e) {
        }
    }
}
