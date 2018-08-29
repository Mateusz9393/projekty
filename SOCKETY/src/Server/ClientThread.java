package Server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread{
    Server server;
    Socket client;
    PrintWriter out;
    BufferedReader in;
    char team;
    int startingProgress;

    public ClientThread(Server server, Socket client, char team, int startingProgress){
        this.server = server;
        this.client = client;
        this.team = team;
        this.startingProgress = startingProgress;
    }

    @Override
    public void run(){
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println(team);
            sendProgress(startingProgress);
            String line;

            while (true){
                line = in.readLine();
                if (line != null){
                    if(line.equals("click"))
                        server.progressChanged(team);
                }else{
                    server.connectionClosed(this, team);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendProgress(int progress){
        out.println(progress);
    }
}
