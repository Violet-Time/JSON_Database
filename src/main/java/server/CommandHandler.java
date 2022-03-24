package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import server.database.Database;

import java.util.LinkedList;

public class CommandHandler {

    private final Database database;

    public CommandHandler(Database database) {
        this.database = database;
    }

    public String[] getKeys(JsonElement jsonElement) {
        LinkedList<String> keys = new LinkedList<>();
        if (jsonElement.isJsonArray()) {
            for (JsonElement e : jsonElement.getAsJsonArray()) {
                keys.add(e.getAsString());
            }
        }
        if (jsonElement.isJsonPrimitive()) {
            keys.add(jsonElement.getAsString());
        }
        return keys.toArray(String[]::new);
    }

    public Receive parse(String command) {
        JsonElement jsonElement = JsonParser.parseString(command);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            jsonElement = jsonObject.get("type");
            if (jsonElement.isJsonPrimitive()) {
                switch (jsonElement.getAsJsonPrimitive().getAsString()) {
                    case "get":
                        JsonElement get = database.get(getKeys(jsonObject.get("key")));
                        return new Receive("OK", null, get);
                    case "set":
                        if (!database.set(getKeys(jsonObject.get("key")), jsonObject.get("value"))) {
                            break;
                        }
                        return new Receive("OK");
                    case "delete":
                        if (!database.delete(getKeys(jsonObject.get("key")))) {
                            break;
                        }
                        return new Receive("OK");
                    case "exit":
                        Server.shutdown();
                        return new Receive("OK");
                    default:
                        return new Receive("ERROR", "Invalid command");
                }

            }
        }
        return new Receive("ERROR", "Invalid command");
    }
/*
    public static void main(String... args) {
        CommandHandler commandHandler = new CommandHandler(new Database());
        String command = """
                {"type":"get","key":["person"]}
                """;
        System.out.println(commandHandler.parse(command));
    }*/
}
