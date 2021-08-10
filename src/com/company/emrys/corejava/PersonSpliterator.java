package com.company.emrys.corejava;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {

    private Spliterator<String> lineSpliterator;
    private String name;
    private String address;
    private String age;

    public PersonSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;
    }


    @Override
    public boolean tryAdvance(Consumer<? super Person> consumer) {
       if(lineSpliterator.tryAdvance(line-> this.name = line) &&
        lineSpliterator.tryAdvance(line-> this.age=line) &&
        lineSpliterator.tryAdvance(line-> this.address = line)){

           Person person = new Person(name, age, address);
           consumer.accept(person);
           return true;
       }
        return false;
    }

    @Override
    public Spliterator<Person> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return lineSpliterator.estimateSize()/3;
    }

    @Override
    public int characteristics() {
        return lineSpliterator.characteristics();
    }
}
