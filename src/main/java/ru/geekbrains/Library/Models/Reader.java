package ru.geekbrains.Library.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Reader {
    public static long sequence = 1L;

    private final long id;
    private final String name;

    public Reader(String name) {
        this.name = name;
        id = sequence++;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Reader) {
            Reader currentReader = (Reader) obj;
            if (this.id == currentReader.getId()) return true;
        }
        return false;
    }
}
