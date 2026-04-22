package com.bookbuddy.api;

import com.bookbuddy.dto.ErrorResponse;
import com.bookbuddy.dto.ReviewCreateDto;
import com.bookbuddy.dto.ReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Tag(name = "Reviews", description = "API для работы с отзывами")
public interface ReviewApi {

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
    List<ReviewDto> getReviewsByBookId(
            @Parameter(description = "UUID книги")
            UUID bookId
    );

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
    ReviewDto createReview(
            @RequestBody(description = "Данные для создания отзыва") ReviewCreateDto dto
    );
}
