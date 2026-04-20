package com.bookbuddy.controller;

import com.bookbuddy.dto.BookCreateDto;
import com.bookbuddy.dto.BookDto;
import com.bookbuddy.dto.BookUpdateDto;
import com.bookbuddy.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public Page<BookDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return bookService.getAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable UUID id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody @Valid BookCreateDto dto){
        return bookService.create(dto);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable UUID id,
                              @RequestBody @Valid BookUpdateDto dto){
        return bookService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
