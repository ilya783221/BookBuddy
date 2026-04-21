package com.bookbuddy.controller;

import com.bookbuddy.dto.ErrorResponse;
import com.bookbuddy.dto.ReviewCreateDto;
import com.bookbuddy.dto.ReviewDto;
import com.bookbuddy.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Reviews", description = "API для работы с отзывами")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // ================= GET BY BOOK ID =================
    @Operation(
            summary = "Получить отзывы по книге",
            description = "Возвращает список отзывов для конкретной книги"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзывы получены"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/book/{bookId}")
    public List<ReviewDto> getReviewsByBookId(
            @Parameter(description = "UUID книги")
            @PathVariable UUID bookId
    ){
        return reviewService.getByBookId(bookId);
    }

    // ================= CREATE REVIEW =================
    @Operation(summary = "Создать отзыв")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Отзыв создан"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User или Book не найдены",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto createReview(@RequestBody @Valid ReviewCreateDto dto){
        return reviewService.create(dto);
    }
}
