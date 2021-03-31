package com.company.emrys.corejava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedValidatorAsync {

    private static HttpClient httpClient;

    public static void main(String[] args) throws IOException
    {
       Stream<String> links =Files.lines(Path.of(".//resources//AsyncUrl.txt")).filter(s->!s.isEmpty());



        List<CompletableFuture<String>> collect = links.map(LinkedValidatorAsync::validateLink)
//                .filter(s->!( s.equals("")&& s!=null))
                .collect(Collectors.toList());

        System.out.println("size: " + collect.size());


        collect.stream().map(CompletableFuture::join)
                .forEach(System.out::println);
    }

    private static CompletableFuture<String> validateLink(String link){
//        httpClient = HttpClient.newHttpClient();
//        adding Robust HttpClient Configuration
          httpClient = HttpClient.newBuilder()
                  .connectTimeout(Duration.ofSeconds(3))
                  .followRedirects(HttpClient.Redirect.NORMAL).build();
//        HttpRequest request = HttpRequest.newBuilder(URI.create(link)).GET().build();

        HttpRequest request = HttpRequest.newBuilder(URI.create(link))
                .timeout(Duration.ofSeconds(2))
                .GET()
                .build();

        return httpClient.sendAsync(request,HttpResponse.BodyHandlers.discarding())
                .thenApply(LinkedValidatorAsync::responseToString)
                .exceptionally(e-> String.format("%s -> %s",link,false));


    }

    private static String responseToString(HttpResponse<Void> response){
        int status = response.statusCode();
        boolean success = status>=200 && status<=299;

        return String.format("%s -> %s (status: %s)",response.uri(),success,status);
    }
}
