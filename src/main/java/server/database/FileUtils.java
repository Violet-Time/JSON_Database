package server.database;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    private static final String fileName = "db.json";
    private static final String dbFilePath = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            "server" + File.separator +
            "data";
    Gson gson;
    File file;

    FileUtils() {
        this.file = new File(dbFilePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.file = new File(dbFilePath + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        gson = new Gson();
    }

    public void save(JsonElement database) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(gson.toJson(database));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JsonElement load() {
        if (!file.exists()) {
            return null;
        }

        try (Reader reader = Files.newBufferedReader(Paths.get(dbFilePath + File.separator + fileName))) {
            return JsonParser.parseReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }
}
