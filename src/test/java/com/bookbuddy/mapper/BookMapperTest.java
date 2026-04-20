package com.bookbuddy.mapper;

import com.bookbuddy.dto.BookCreateDto;
import com.bookbuddy.dto.BookDto;
import com.bookbuddy.model.Book;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    void shouldMapToDto() {
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("Test Book");

        BookDto dto = mapper.toDto(book);

        assertNotNull(dto);
        assertEquals(book.getId(), dto.getId());
        assertEquals(book.getTitle(), dto.getTitle());
    }

    @Test
    void shouldMapToEntity() {
        BookCreateDto dto = new BookCreateDto();
        dto.setTitle("Another Book");
        dto.setAuthor("Author");
        dto.setPublishedYear(2020);
        dto.setGenre("Drama");

        Book book = mapper.toEntity(dto);

        assertNotNull(book);
        assertEquals(dto.getTitle(), book.getTitle());
    }
}
