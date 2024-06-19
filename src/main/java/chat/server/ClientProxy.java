package chat.server;

import chat.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientProxy extends Thread {
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ServerController serverController;
    private Message nachricht;

    public ClientProxy(Server server, ServerController serverController, Socket client) throws IOException {
        this.server = server;
        this.serverController = serverController;

        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
        this.start();
    }

    @Override
    public void run() {
        try {
            while ((nachricht = (Message) in.readObject()) != null) {
                System.out.println("Empfangen vom Client: " + nachricht.toString());
                server.verteileNachricht(nachricht);
                //Platform.runLater(() -> serverController.getNachrichten().getItems().add(nachricht));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void schreiben(Message nachricht) throws IOException {
        out.writeObject(nachricht);
        out.flush();
    }
}
