package ru.netology;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Request {

    private final RequestLine requestLine;
    private final List<String> headers;
    private String body;
    private List<NameValuePair> queryParams;
    private List<NameValuePair> postParams;

    public Request(RequestLine requestLine, List<String> headers) {
        this.requestLine = requestLine;
        this.headers = headers;
    }

    public Request(RequestLine requestLine, List<String> headers, String body) {
        this.requestLine = requestLine;
        this.headers = headers;
        this.body = body;
    }

    public Optional<String> getHeader(String header) {
        return headers.stream()
                .filter(o -> o.startsWith(header))
                .map(o -> o.substring(o.indexOf(" ")))
                .map(String::trim)
                .findFirst();
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private List<String> getParam(List<NameValuePair> params, String name) {
        return params.stream()
                .filter(o -> o.getName().startsWith(name))
                .map(NameValuePair::getValue)
                .collect(Collectors.toList());
    }

    public List<String> getQueryParam(String name) {
        return getParam(queryParams, name);
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public void setPostParams(List<NameValuePair> postParams) {
        this.postParams = postParams;
    }

    public List<String> getPostParam(String name) {
        return getParam(postParams, name);
    }

    public List<NameValuePair> getPostParams() {
        return postParams;
    }

    public void setQueryParams(List<NameValuePair> queryParams) {
        this.queryParams = queryParams;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestLine=" + requestLine +
                ", headers='" + headers + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
