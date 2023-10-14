package br.com.marketupdate.configuration;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class ResponseObject {
    private String authorization;
    private final String message;
    private boolean success;
    public ResponseObject(String authorization, String message) {
        this.authorization = authorization;
        this.message = message;
    }

    public ResponseObject(boolean success, String authorization, String message) {
        this.success = success;
        this.authorization = authorization;
        this.message = message;
    }
    public ResponseObject(String message) {
        this.message = message;
    }
    public String getAuthorization() {
        return authorization;
    }
    public String getMessage() {
        return message;
    }


    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
