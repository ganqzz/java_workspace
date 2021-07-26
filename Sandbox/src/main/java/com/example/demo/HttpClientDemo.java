package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        httpClientSync();
        System.out.println();
        httpClientAsync();
    }

    public static void httpClientSync() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(URI.create("https://www.yahoo.co.jp"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,
                                                        HttpResponse.BodyHandlers.ofString());

        System.out.println(response.headers().map());
        System.out.println();
        //System.out.println(response.body());
    }

    public static void httpClientAsync() {
        HttpClient httpClient = HttpClient.newBuilder()
                                          .version(HttpClient.Version.HTTP_2)
                                          .build();

        HttpRequest request = HttpRequest
                .newBuilder(URI.create("https://www.google.com"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = httpClient
                .sendAsync(request,
                           HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res.version()));
        response.join();
    }
}
