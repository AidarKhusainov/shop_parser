package ru.aidarkhusainov.httpclient;

import org.apache.http.StatusLine;

import java.util.Map;

public class CustomHttpResponse {
    private final Map<String, String> responseHeaders;
    private final byte[] responseBody;
    private final int responseStatusCode;
    private final StatusLine responseStatusLine;

    public CustomHttpResponse(Map<String, String> responseHeaders,
                              byte[] responseBody,
                              int responseStatusCode,
                              StatusLine responseStatusLine) {
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.responseStatusCode = responseStatusCode;
        this.responseStatusLine = responseStatusLine;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public byte[] getResponseBody() {
        return responseBody;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public StatusLine getResponseStatusLine() {
        return responseStatusLine;
    }
}
