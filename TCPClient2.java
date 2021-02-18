import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient2 {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Incorrect use of command: java TCPClient <hostname> <port> <resource>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String resource = args[2];
        
        Socket sock = new Socket(hostname, port);
        DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
        BufferedReader inFromServer =new BufferedReader(new InputStreamReader(sock.getInputStream()));

        String HTTPRequest = "HEAD " + resource + " HTTP/1.1\n" + "Host: " + hostname + "\n\n";
        outToServer.writeBytes(HTTPRequest);

        String response = "";
        while (true) {
            response = inFromServer.readLine();
            System.out.println(response);
            if (!inFromServer.ready())
                break;
        }

        sock.close();
    }
}