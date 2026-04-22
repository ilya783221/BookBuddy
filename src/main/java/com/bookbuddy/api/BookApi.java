package com.bookbuddy.api;

import com.bookbuddy.dto.BookCreateDto;
import com.bookbuddy.dto.BookDto;
import com.bookbuddy.dto.BookUpdateDto;
import com.bookbuddy.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Books", description = "API для работы с книгами")
public interface BookApi {

    @Operation(
            summary = "Получить список книг",
            description = "Возвращает список книг с пагинацией и сортировкой"
    )
    @ApiResponse(responseCode = "200", description = "Список книг получен")
    Page<BookDto> getAllBooks(
            @Parameter(description = "Номер страницы") int page,
            @Parameter(description = "Размер страницы") int size,
            @Parameter(description = "Поле сортировки") String sortBy
    );

    @Operation(summary = "Получить книгу по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга найдена"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    BookDto getBookById(
            @Parameter(description = "UUID книги") UUID id
    );

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
    BookDto createBook(
            @RequestBody(description = "Данные для создания книги") BookCreateDto dto
    );

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
    BookDto updateBook(
            @Parameter(description = "UUID книги") UUID id,
            @RequestBody(description = "Данные для обновления книги") BookUpdateDto dto
    );

    @Operation(summary = "Удалить книгу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Книга удалена"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<Void> deleteBook(
            @Parameter(description = "UUID книги") UUID id
    );
}
