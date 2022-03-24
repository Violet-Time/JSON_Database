package server.database;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;

public class Database {
    private final FileUtils fileUtils;

    public Database() {
        this.fileUtils = new FileUtils();
    }

    public JsonElement get(String[] keys) {
        JsonElement get = fileUtils.load();
        JsonElement find = search(get, keys);
        return find;
    }

    public boolean set(String[] keys, JsonElement value) {
        JsonElement set = fileUtils.load();
        if (set.isJsonNull()) {
            set = new JsonObject();
        }
        JsonElement find = set;
        JsonElement prev = find;
        for (int i = 0; i < keys.length - 1; i++) {
            if (find.isJsonObject()) {
                prev = find;
                find = find.getAsJsonObject().get(keys[i]);
            }
            if (find == null || !find.isJsonObject()) {
                find = new JsonObject();
                prev.getAsJsonObject().add(keys[i], find);
            }
        }
        find.getAsJsonObject().add(keys[keys.length - 1], value);
        fileUtils.save(set);
        return true;
    }

    public boolean delete(String[] keys) {
        JsonElement jsonElement = fileUtils.load();
        JsonElement find = search(jsonElement, Arrays.copyOf(keys, keys.length - 1));
        if (find == null) return false;
        find.getAsJsonObject().remove(keys[keys.length - 1]);
        fileUtils.save(jsonElement);
        return true;
    }

    public JsonElement search(JsonElement search, String[] keys) {
        for (String key : keys) {
            if (search == null || !search.isJsonObject()) {
                break;
            }
            search = search.getAsJsonObject().get(key);
        }
        return search;
    }

}
