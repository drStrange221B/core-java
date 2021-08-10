package flatmapstreamofnumbers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
 * merge the lines of two files into one stream
 * split those lines into words, and merge then into one stream of words
 * function that splits a line into words
 * */
public class Main {

    public static void main(String[] args) {

        Path path1 = Paths.get("Resources/file1.txt");
        Path path2 = Paths.get("Resources/file2.txt");
        Path path3 = Paths.get("Resources/file3.txt");

        Function<String, Stream<String>> converLinestoWords = line -> Stream.of(line.split(" "));

        try (Stream<String> lineStream1 = Files.lines(path1);
             Stream<String> lineStream2 = Files.lines(path2);
             Stream<String> lineStream3 = Files.lines(path3)) {

            Stream<String> stringStream = lineStream1.flatMap(converLinestoWords);
            Stream<String> stringStream1 = lineStream2.flatMap(converLinestoWords);
            Stream<String> stringStream2 = lineStream3.flatMap(converLinestoWords);
            Stream<String> rStream = Stream.of(stringStream, stringStream1, stringStream2).flatMap(Function.identity());
            rStream.forEach(System.out::println);

//            Stream<String> words = Stream.of(lineStream1, lineStream2, lineStream3)
//                    .flatMap(Function.identity()).flatMap(converLinestoWords);

//            words.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();

//
        }

    }
}
