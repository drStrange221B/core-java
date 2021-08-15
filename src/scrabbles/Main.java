package scrabbles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.*;

public class Main {

    /*
    * reading words form two source, one is legit one is some random words
    * give the score for the words
    * */
    public static void main(String[] args) {

        Path path1 = Paths.get("Resources/scrabble_numberStream/ospd.txt");
        Path path2 = Paths.get("Resources/scrabble_numberStream/shakespeare.txt");
        final int[] scrabbleENScore = {
//         a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
           1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,1, 1, 1, 1, 4, 4, 8, 4, 10};



        try{

            Set<String> realWords = Files.lines(path1).collect(Collectors.toSet());
            Set<String> randomWords = Files.lines(path2).collect(Collectors.toSet());

            Function<String, Integer> sumValueOfWord = word-> word.toLowerCase().chars().map(w->scrabbleENScore[w-'a']).sum();

            ToIntFunction<String> sumLetters= letter-> letter.toLowerCase().chars().map(w->scrabbleENScore[w-'a']).sum();
            System.out.println(sumValueOfWord.apply("hello"));


            String word = randomWords.stream()
                    .filter(x-> realWords.contains(x))
                    .max(comparing(sumValueOfWord)).get();

            System.out.println(word);

            IntSummaryStatistics summaryStatistics = randomWords.stream().filter(x -> realWords.contains(x))
                    .mapToInt(sumLetters).summaryStatistics();

            System.out.println(summaryStatistics);
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
