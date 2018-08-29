package Client;

import Client.GUI.View;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private int ropeProgress;
    private View view;

    public Client(View view){
        this.view = view;
    }

    public void start(String address, int port){
        try {
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            while (true){
                line = in.readLine();
                if (line != null){
                    if (line.equals("l") || line.equals("p")){
                        view.setTeamLabel(line);
                    }else{
                        int progress = Integer.parseInt(line);
                        view.setRope(progress);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendClick(){
        out.println("click");
    }
}
