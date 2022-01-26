package org.jnew.features.j11.httpclient;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.URI;
import java.net.http.HttpClient;


public class SimpleHttpClientExample {

    public static void main(String... args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
                HttpRequest.newBuilder(URI.create("https://www.pluralsight.com"))
                .GET()
                .build();

        HttpResponse<String> response =
          client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200)
            System.out.println(response.headers().map());
    }
}
