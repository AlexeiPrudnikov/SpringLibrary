package ru.geekbrains.Library.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Issue {
    public static long sequence = 1L;

    private final long id;
    private final long bookId;
    private final long readerId;
    private final LocalDate issued_at;
    @Setter
    private LocalDate returned_at;

    public Issue(long bookId, long readerId, LocalDate issued_at) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = issued_at;
        id = sequence++;
    }
}
