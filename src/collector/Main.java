package collector;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Path shakespearsPath = Paths.get("./Resources/scrabble_numberStream/shakespeare.txt");
        Path ospdPath = Paths.get("./Resources/scrabble_numberStream/ospd.txt");

        System.out.println("Hello");

        try(Stream<String> ospd = Files.lines(ospdPath);
            Stream<String> shakespear = Files.lines(shakespearsPath)){

            Set<String> scrableWords = ospd.collect(Collectors.toSet());
            Set<String> shakespeareWords = shakespear.collect(Collectors.toSet());

            System.out.println("Scrabble : " + scrableWords.size());
            System.out.println("Shakespeare : " + shakespeareWords.size());

            final int[] scrabbleENScore = {
//                  a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
                    1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,1, 1, 1, 1, 4, 4, 8, 4, 10};

            Function<String, Integer> score = word->word.toLowerCase().chars()
                                                .map(letter -> scrabbleENScore[letter - 'a']).sum();

            Map<Integer, List<String>> listOfWordsByScore = shakespeareWords.stream()
                                                            .filter(word -> scrableWords.contains(word))
                                                            .collect(Collectors.groupingBy(score));

            System.out.println(listOfWordsByScore.size());

            listOfWordsByScore.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(word -> -word.getKey()))
                    .limit(3).forEach(entry->System.out.println(entry.getKey() + " - " + entry.getValue()));



        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
