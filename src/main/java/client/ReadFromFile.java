package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ReadFromFile {
    private static final String clientDataPath = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            "client" + File.separator +
            "data";

    public ReadFromFile() {

    }

    public String read(String path) {
        File file = new File(clientDataPath + File.separator + path);
        if (!file.exists()) {
            System.out.println("not exists");
            return null;
        }

        try {
            return new String(Files.readAllBytes(Paths.get(clientDataPath + File.separator + path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
