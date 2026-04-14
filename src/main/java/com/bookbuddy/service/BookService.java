package com.bookbuddy.service;

import com.bookbuddy.dto.BookDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BookService {

    BookDto create(BookDto dto);

    BookDto getById(UUID id);

    Page<BookDto> getAll(int page, int size, String sortBy);

    BookDto update(UUID id, BookDto dto);

    void delete(UUID id);
}
