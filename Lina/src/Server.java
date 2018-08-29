import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

    int power = 5;
    static final int port = 4444;
    int number = 50;
    ServerSocket myServer;
    Socket clientSocket;
    ArrayList<ServerThread> clients = new ArrayList<ServerThread>();
    int blueTeam = 0;
    int whiteTeam = 0;

    Server() {
        try {
            myServer = new ServerSocket(port);
        }catch (IOException e){e.printStackTrace();}
    }
    void init(){
        char team;
        try {
            while (true) {
                if (blueTeam <= whiteTeam){
                    team = 'b';
                    blueTeam++;
                }else {
                    team = 'w';
                    whiteTeam++;
                }

                clientSocket = myServer.accept();
                ServerThread serverThread = new ServerThread(this, clientSocket, String.valueOf(number), team);
                serverThread.start();
                clients.add(serverThread);
            }

        }catch (IOException e){e.printStackTrace();}

    }
    synchronized public void progressChanged(char team){
        if (team == 'w' && number != 0)
            number -= power;
        else if (team == 'b' && number != 100)
            number += power;
        sendNumber();
    }
    void sendNumber(){
        for (ServerThread client : clients){
            client.sendNumber(String.valueOf(number));
        }
    }
    public void connectionClosed(ServerThread serverThread, char team){
        if (team == 'b')
            blueTeam--;
        else
            whiteTeam--;
        clients.remove(serverThread);
    }

}
