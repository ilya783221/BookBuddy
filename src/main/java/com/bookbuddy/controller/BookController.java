package com.bookbuddy.controller;

import com.bookbuddy.api.BookApi;
import com.bookbuddy.dto.BookCreateDto;
import com.bookbuddy.dto.BookDto;
import com.bookbuddy.dto.BookUpdateDto;
import com.bookbuddy.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService bookService;

    @Override
    @GetMapping
    public Page<BookDto> getAllBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy
    ){
        return bookService.getAll(page, size, sortBy);
    }

    @Override
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable UUID id){
        return bookService.getById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid BookCreateDto dto){
        return bookService.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable UUID id,
            @RequestBody @Valid BookUpdateDto dto
    ){
        return bookService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
