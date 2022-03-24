package server;

import com.google.gson.JsonElement;

public class Receive {
    private String response;
    private String reason;
    private JsonElement value;

    public Receive(String response) {
        this(response, null, null);
    }

    public Receive(String response, String reason) {
        this(response, reason, null);
    }

    public Receive(String response, String reason, JsonElement value) {
        this.response = response;
        this.reason = reason;
        this.value = value;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JsonElement getValue() {
        return value;
    }

    public void setValue(JsonElement value) {
        this.value = value;
    }
}
