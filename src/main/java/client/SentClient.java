package client;

import com.beust.jcommander.Parameter;

public class SentClient {
    @Parameter(names={"--type", "-t"})
    private String type;
    @Parameter(names={"--key", "-k"})
    private String key;
    @Parameter(names={"--value", "-v"})
    private String value;
    @Parameter(names={"--in", "-in"})
    private String path;

    public SentClient() {
        this(null,null,null);
    }

    public SentClient(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "client.SentClient{" +
                "type='" + type + '\'' +
                ", key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
