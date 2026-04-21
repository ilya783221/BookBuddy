package com.bookbuddy.controller;

import com.bookbuddy.dto.BookCreateDto;
import com.bookbuddy.dto.BookDto;
import com.bookbuddy.dto.BookUpdateDto;
import com.bookbuddy.dto.ErrorResponse;
import com.bookbuddy.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Books", description = "API для работы с книгами")
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // ================= GET ALL =================
    @Operation(
        summary = "Получить список книг",
        description = "Возвращает список книг с пагинацией и сортировкой"
    )
    @ApiResponse(responseCode = "200", description = "Список книг получен")
    @GetMapping
    public Page<BookDto> getAllBooks(
        @Parameter(description = "Номер страницы") @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Размер страницы") @RequestParam(defaultValue = "5") int size,
        @Parameter(description = "Поле сортировки") @RequestParam(defaultValue = "id") String sortBy
    ){
        return bookService.getAll(page, size, sortBy);
    }

    // ================= GET BY ID =================
    @Operation(summary = "Получить книгу по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга найдена"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    public BookDto getBookById(
            @Parameter(description = "UUID книги") @PathVariable UUID id
    ){
        return bookService.getById(id);
    }

    // ================= CREATE =================
    @Operation(summary = "Создать книгу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Книга создана"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт данных",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid BookCreateDto dto){
        return bookService.create(dto);
    }

    // ================= UPDATE =================
    @Operation(summary = "Обновить книгу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга обновлена"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/{id}")
    public BookDto updateBook(
            @Parameter(description = "UUID книги") @PathVariable UUID id,
            @RequestBody @Valid BookUpdateDto dto
    ){
        return bookService.update(id, dto);
    }

    // ================= DELETE =================
    @Operation(summary = "Удалить книгу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Книга удалена"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "UUID книги") @PathVariable UUID id
    ){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
