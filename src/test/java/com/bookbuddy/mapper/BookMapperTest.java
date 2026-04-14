package com.bookbuddy.mapper;

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

        System.out.println(dto);

        assertNotNull(dto);
        assertEquals(book.getId(), dto.getId());
        assertEquals(book.getTitle(), dto.getTitle());
    }

    @Test
    void shouldMapToEntity() {
        BookDto dto = new BookDto();
        dto.setId(UUID.randomUUID());
        dto.setTitle("Another Book");

        Book book = mapper.toEntity(dto);

        System.out.println(book);

        assertNotNull(book);
        assertEquals(dto.getId(), book.getId());
        assertEquals(dto.getTitle(), book.getTitle());
    }
}
