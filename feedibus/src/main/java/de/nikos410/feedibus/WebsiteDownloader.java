package de.nikos410.feedibus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class WebsiteDownloader {

    private final HttpClient httpClient = buildHttpClient();

    public CompletableFuture<String> downloadWebsite(String url) {

        final HttpRequest httpRequest = buildHttpRequest(url);
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    private HttpRequest buildHttpRequest(String url) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
    }

    private HttpClient buildHttpClient() {

        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
    }
}
