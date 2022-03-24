package client;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    String address = "127.0.0.1";
    int port = 23454;
    DataInputStream input;
    DataOutputStream output;
    Socket socket;
    JCommander jCommander;
    SentClient commands;

    public Client() throws Exception {
        socket = new Socket(InetAddress.getByName(address), port);
        System.out.println("Client started!");
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        commands = new SentClient();
        jCommander = JCommander.newBuilder()
                .addObject(commands)
                .build();
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void disconnect() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

    public SentClient getCommands() {
        return commands;
    }

    public void setCommands(SentClient commands) {
        this.commands = commands;
    }

    public JCommander getjCommander() {
        return jCommander;
    }
}
