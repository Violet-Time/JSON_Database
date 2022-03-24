package server;

import server.database.Database;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final static String address = "127.0.0.1";
    private final static int port = 23454;
    private final ServerSocket serverSocket;
    private final ExecutorService executor;
    private final CommandHandler commandHandler;
    private static boolean shutdown = false;

    public Server(Database database) throws Exception {
        this.serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address));
        this.serverSocket.setSoTimeout(100);
        System.out.println("Server started!");
        this.commandHandler = new CommandHandler(database);
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void newConnect() throws IOException {
        executor.submit(new ClientHandler(serverSocket.accept(), commandHandler));
    }

    public static void shutdown() {
        shutdown = true;
    }

    public void shuttingDown() throws IOException {
        executor.shutdown();
        serverSocket.close();
    }

    public static boolean isShutdown() {
        return shutdown;
    }
}
