package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.File;

public class Main {


    public static void main(String... args) throws Exception {
        Client client = new Client();
        client.getjCommander().parse(args);
        String json;
        if (client.getCommands().getPath() != null) {
          ReadFromFile readFromFile = new ReadFromFile();
          json = readFromFile.read(client.getCommands().getPath());
          //System.out.println(str);
          if (json == null) {
              client.disconnect();
              return;
          }
        } else {
            Gson gson = new Gson();
            json = gson.toJson(client.getCommands());
        }
        client.getOutput().writeUTF(json);
        System.out.println("Sent: " + json);
        System.out.println("Received: " + client.getInput().readUTF());
        client.disconnect();
    }
}
