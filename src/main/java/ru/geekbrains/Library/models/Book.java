package ru.geekbrains.Library.models;

import lombok.Getter;

@Getter
public class Book {
    public static long sequence = 1L;
    private final long id;
    private final String name;

    public Book(String name) {
        this.id = sequence++;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Book) {
            Book currentBook = (Book) obj;
            if (this.id == currentBook.getId()) return true;
        }
        return false;
    }
}
