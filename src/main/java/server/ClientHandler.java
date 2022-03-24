package server;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private final Socket socket;
    private final CommandHandler commandHandler;

    public ClientHandler(Socket socket, CommandHandler commandHandler) {
        this.socket = socket;
        this.commandHandler = commandHandler;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        try (
                DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream())
        ) {
            String request = inputStream.readUTF();
            Receive receive;
            try {
                receive = commandHandler.parse(request);
            } catch (UnsupportedOperationException e) {
                receive = new Receive("ERROR", e.getMessage());
            }
            outputStream.writeUTF(gson.toJson(receive));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //disconnect();
        }
    }
}
