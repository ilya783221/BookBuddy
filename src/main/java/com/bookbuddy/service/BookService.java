package com.bookbuddy.service;

import com.bookbuddy.dto.BookCreateDto;
import com.bookbuddy.dto.BookDto;
import com.bookbuddy.dto.BookUpdateDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BookService {

    BookDto create(BookCreateDto dto);

    BookDto getById(UUID id);

    Page<BookDto> getAll(int page, int size, String sortBy);

    BookDto update(UUID id, BookUpdateDto dto);

    void delete(UUID id);
}
