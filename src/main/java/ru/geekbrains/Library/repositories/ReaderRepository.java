package ru.geekbrains.Library.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Library.models.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("Иванов"),
                new Reader("Петров"),
                new Reader("Булкин"),
                new Reader("Рогов")
        ));
    }

    public Reader getReaderById(long id) {
        return   readers.stream().filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Reader> getAllReaders(){
        return List.copyOf(readers);
    }
    public boolean deleteReader(long id){
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i).getId() == id){
                readers.remove(i);
                return true;
            }
        }
        return false;
    }
    public Reader addReader(String name){
        Reader newReader = new Reader(name);
        readers.add(newReader);
        return newReader;
    }
}
