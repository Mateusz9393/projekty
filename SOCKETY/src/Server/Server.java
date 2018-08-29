package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private Socket client;
    private ArrayList<ClientThread> clientList = new ArrayList<ClientThread>();
    private int leftAmount = 0;
    private int rightAmount = 0;
    private int progress = 50;

    public Server(){
    }

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            char team;
            if (leftAmount <= rightAmount){
                team = 'l';
                leftAmount++;
            }else {
                team = 'p';
                rightAmount++;
            }
            ClientThread clientThread = new ClientThread(this, client, team, progress);
            clientThread.start();
            clientList.add(clientThread);
        }
    }

    public void progressChanged(char team){
        if (team == 'l' && progress != 0)
            progress -= 1;
        else if (team == 'p' && progress != 100)
            progress += 1;
        sendProgress();
    }

    private void sendProgress(){
        for (ClientThread client : clientList){
            client.sendProgress(progress);
        }
    }

    public void connectionClosed(ClientThread client, char team){
        if (team == 'l')
            leftAmount--;
        else
            rightAmount--;
        clientList.remove(client);
    }
}
