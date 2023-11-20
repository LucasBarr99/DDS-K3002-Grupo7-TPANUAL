package main.ApiClientePesado.dto;

public class ValidacionRequest {

    String sessionId;
    String url;

    public ValidacionRequest(){}

    public ValidacionRequest(String sessionId, String url) {
        this.sessionId = sessionId;
        this.url = url;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
