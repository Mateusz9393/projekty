package Client;

import Client.GUI.View;

public class ClientMain {

    public static void main(String[] args) {
        View view = new View();
        view.init();
        Client client = new Client(view);
        view.setClient(client);
        client.start("127.0.0.1", 1234);
    }
}
