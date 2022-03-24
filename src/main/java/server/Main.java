package server;

import client.SentClient;
import com.google.gson.Gson;
import server.database.Database;

import java.net.SocketTimeoutException;

public class Main {

    public static void main(String... args) throws Exception {

        Database database = new Database();
        Server server = new Server(database);

        while (!Server.isShutdown()) {
            try {
                server.newConnect();
            } catch (SocketTimeoutException e) {
                //System.out.println(e.getMessage());
            }
        }
        server.shuttingDown();
    }

}
