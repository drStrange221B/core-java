package spliterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    public static void  main(String... strs)  {

//        System.out.println(System.getProperty("user.home"));

        Path path = Paths.get("Resources/people.txt");


        try(Stream<String> lines = Files.lines(path)){

            Spliterator<String> lineSpliterator = lines.spliterator();

            Spliterator<Person> personSpliterator = new PersonSpliterator(lineSpliterator);

            Stream<Person> personStream = StreamSupport.stream(personSpliterator, false);

            personStream.forEach(System.out:: println);

        }catch (IOException e){
            e.printStackTrace();
        }




    }


}
