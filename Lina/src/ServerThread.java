import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    Server server;
    Socket clientSocket;

    String number;
    BufferedReader in;
    PrintWriter out;
    char team;

    ServerThread(Server server, Socket clientSocket, String number, char team){
        this.server = server;
        this.clientSocket = clientSocket;
        this.number = number;
        this.team = team;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        }catch(IOException e){e.printStackTrace();}
    }
    @Override
    public void run() {

        try {
            out.println(team);
            sendNumber(number);
            System.out.println(number);
            while (true) {
                number = in.readLine();
                System.out.println(number);
                if (number != null) {
                    if (number.equals("5")) {
                        server.progressChanged(team);
                    }
                }else{
                    server.connectionClosed(this, team);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendNumber(String number) {
            out.println(number);
    }
}
