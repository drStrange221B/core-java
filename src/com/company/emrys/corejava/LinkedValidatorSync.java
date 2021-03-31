package com.company.emrys.corejava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class LinkedValidatorSync {

   private static HttpClient httpClient;

    public static void main(String[] args) throws Exception {
        Stream<String> lines = Files.lines(Path.of(".//resources//urls.txt"));

            lines.map(LinkedValidatorSync::ValidateLink).forEach(System.out::println);

    }


    private static String ValidateLink(String link){
        httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(link)).GET().build();

        try {
            HttpResponse<Void> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.discarding());
            return responseToString(response);
        }catch(IOException | InterruptedException e)
        {
            return String.format("%s -> %s", link,false);
        }
    }

    private static String responseToString(HttpResponse<Void> response){

        int status = response.statusCode();
        boolean success = status>=200 && status<=299;

        return String.format("%s -> %s (status: %s)", response.uri(), success, status);
    }
}
